package shadow

import groovy.transform.CompileDynamic
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import shadow.message.FacebookReceived

@RestController
@RequestMapping(value='/webhook')
@CompileDynamic
class ApplicationController {

    private final FacebookAdapter facebookAdapter
    private final BotResponse botResponse

    @Value('${facebook.verify.url}')
    String token

    ApplicationController(FacebookAdapter facebookAdapter, BotResponse botResponse) {
        this.facebookAdapter = facebookAdapter
        this.botResponse = botResponse
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<String> getFacebookChallengeValidation(@RequestParam('hub.mode') String facebookMode,
                                      @RequestParam('hub.verify_token') String facebookToken,
                                      @RequestParam('hub.challenge') String facebookChallenge) {
        if (this.token == facebookToken && facebookMode == 'subscribe') {
            return ResponseEntity.ok(facebookChallenge)
        }
        new ResponseEntity<>(HttpStatus.UNAUTHORIZED)
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<String> getFacebookContent(@RequestBody FacebookReceived facebookContent) {
        sendContentToProcessResponse(facebookContent.senderId, facebookContent.textMessage)
        new ResponseEntity<>(HttpStatus.OK)
    }

    void sendContentToProcessResponse(String clientId, String clientMessage) {
        String response = botResponse.getBotResponse(clientId, clientMessage)
        sendResponseToFacebookAdapter(clientId, response)
    }

    void sendResponseToFacebookAdapter(String clientId, String response) {
        facebookAdapter.sendMessageToFacebook(clientId, response)
    }

}
