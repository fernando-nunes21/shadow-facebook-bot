package shadow.dialogflow

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import shadow.dialogflow.input.DialogInput
import java.security.PrivateKey

@Service
class DialogflowAPI {

    @Value('${shadow.dialogflow.credentialsPath}')
    private String CREDENTIALS_FILE_PATH

    @Value('${shadow.dialogflow.urlApi}')
    private String urlDialogFlow

    private RestTemplate restTemplate

    DialogflowAPI(RestTemplate restTemplate){
        this.restTemplate = restTemplate
    }

    ResponseEntity<String> getDialogFlowResponse(String message, String sessionId) {
        String url = this.urlDialogFlow << sessionId << ":detectIntent"
        String tokenAuthenticated = getAccessToken()
        String authenticationHeader = "Bearer " << tokenAuthenticated
        DialogInput messageInput = new DialogInput(message)
        HttpHeaders headers = new HttpHeaders()
        headers.add("Authorization", authenticationHeader)
        headers.add("Content-Type", "application/json; charset=utf-8")
        headers.add("Accept","application/json")
        HttpEntity request = new HttpEntity<>(messageInput, headers)
        return this.restTemplate.postForEntity(url, request, String)
    }

    private String getAccessToken(){
        GoogleCredential credential =
                GoogleCredential.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH))
        PrivateKey privateKey = credential.getServiceAccountPrivateKey()
        String privateKeyId = credential.getServiceAccountPrivateKeyId()
        Algorithm algorithm = Algorithm.RSA256(null, privateKey)
        String signedJwt = JWT.create()
                .withKeyId(privateKeyId)
                .withIssuer("shadowbot@shadowbotface-sfrn.iam.gserviceaccount.com")
                .withSubject("shadowbot@shadowbotface-sfrn.iam.gserviceaccount.com")
                .withAudience("https://dialogflow.googleapis.com/")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000L))
                .sign(algorithm)
        return signedJwt
    }
}
