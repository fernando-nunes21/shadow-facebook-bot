package shadow

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import shadow.dialogflow.DialogflowAPI
import shadow.dialogflow.input.DialogInput
import shadow.dialogflow.input.QueryInput
import shadow.dialogflow.input.Text
import spock.lang.Specification

class DialogflowAPITest extends Specification {

    private final RestTemplate restTemplate = Mock(RestTemplate)
    private DialogflowAPI dialogflowAPI = new DialogflowAPI(restTemplate)

    def "get a dialogflow response when id, session and keys are correclty" () {
        given:
        String message = "test message"
        String sessionId = "12345"
        String url = "https://dialogflow.googleapis.com/v2/projects/shadowbotface-sfrn/agent/sessions/" << sessionId << ':detectIntent'
        HttpHeaders headers = new HttpHeaders()
        DialogInput messageInput = new DialogInput(queryInput: new QueryInput(text: new Text(text: message)))
        HttpEntity request = new HttpEntity<>(messageInput, headers)

        dialogflowAPI.urlDialogFlow = "https://dialogflow.googleapis.com/v2/projects/shadowbotface-sfrn/agent/sessions/"
        dialogflowAPI.credentialsFilePath = "src/main/resources/dialogFlowCredentials.json"

        restTemplate.postForEntity(url, request, String) >> new ResponseEntity<String>(HttpStatus.OK)

        when:
        ResponseEntity<String> result = dialogflowAPI.getDialogFlowResponse(message, sessionId)

        then:
        noExceptionThrown()
    }
}
