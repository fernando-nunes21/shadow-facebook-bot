package shadow.dialogflow

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import shadow.dialogflow.input.DialogInput

@Slf4j
@Service
class DialogflowAPI {

    @Value('${shadow.dialogflow.urlApi}')
    private String urlDialogFlow

    @Value('${shadow.dialogflow.token}')
    private String token

    private RestTemplate restTemplate

    DialogflowAPI(RestTemplate restTemplate){
        this.restTemplate = restTemplate
    }

    ResponseEntity<String> getDialogFlowResponse(String message, String sessionId) {
        String url = this.urlDialogFlow << sessionId << ":detectIntent"
        String authenticationHeader = "Bearer " << this.token
        DialogInput messageInput = new DialogInput(message)
        HttpHeaders headers = new HttpHeaders()
        headers.add("Authorization", authenticationHeader)
        headers.add("Content-Type", "application/json; charset=utf-8")
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON))
        HttpEntity request = new HttpEntity<>(messageInput, headers)
        return this.restTemplate.postForEntity(url, request, String)
    }
}
