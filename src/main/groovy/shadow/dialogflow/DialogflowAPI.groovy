package shadow.dialogflow

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import groovy.transform.CompileDynamic
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import shadow.dialogflow.input.DialogInput
import shadow.dialogflow.input.QueryInput
import shadow.dialogflow.input.Text

import java.security.PrivateKey
import java.sql.Date

@Service
@CompileDynamic
class DialogflowAPI {

    private static final String USER_ACCOUNT = 'shadowbot@shadowbotface-sfrn.iam.gserviceaccount.com'
    private static final Integer MILLISECONDS_TO_COUNT = 3600
    private static final Integer ONE_THOUSAND_MULTI = 1000L

    @Value('${shadow.dialogflow.credentialsPath}')
    String credentialsFilePath

    @Value('${shadow.dialogflow.urlApi}')
    String urlDialogFlow

    private final RestTemplate restTemplate

    DialogflowAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate
    }

    ResponseEntity<String> getDialogFlowResponse(String message, String sessionId) {
        String url = this.urlDialogFlow << sessionId << ':detectIntent'
        String tokenAuthenticated = getAccessToken()
        String authenticationHeader = 'Bearer ' << tokenAuthenticated
        DialogInput messageInput = new DialogInput(queryInput: new QueryInput(text: new Text(text: message)))
        HttpHeaders headers = new HttpHeaders()
        headers.add('Authorization', authenticationHeader)
        headers.add('Content-Type', 'application/json; charset=utf-8')
        headers.add('Accept', 'application/json')
        HttpEntity request = new HttpEntity<>(messageInput, headers)
        this.restTemplate.postForEntity(url, request, String)
    }

    private String getAccessToken() {
        GoogleCredential credential =
                GoogleCredential.fromStream(new FileInputStream(credentialsFilePath))
        PrivateKey privateKey = credential.getServiceAccountPrivateKey()
        String privateKeyId = credential.getServiceAccountPrivateKeyId()
        Algorithm algorithm = Algorithm.RSA256(null, privateKey)
        String signedJwt = JWT.create()
                .withKeyId(privateKeyId)
                .withIssuer(USER_ACCOUNT)
                .withSubject(USER_ACCOUNT)
                .withAudience('https://dialogflow.googleapis.com/')
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + MILLISECONDS_TO_COUNT * ONE_THOUSAND_MULTI))
                .sign(algorithm)
        signedJwt
    }

}

