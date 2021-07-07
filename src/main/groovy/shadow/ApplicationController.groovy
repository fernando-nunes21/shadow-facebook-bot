package shadow

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
@RequestMapping(value="/webhook")
class ApplicationController {

    @Value('${facebook.verify.url}')
    private String token
    private FacebookAdapter facebookAdapter
    private BotResponse botResponse

    ApplicationController(FacebookAdapter facebookAdapter, BotResponse botResponse) {
        this.facebookAdapter = facebookAdapter
        this.botResponse = botResponse
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<String> getFacebookChallengeValidation(@RequestParam("hub.mode") String facebookMode,
                                      @RequestParam("hub.verify_token") String facebookToken,
                                      @RequestParam("hub.challenge") String facebookChallenge) {
        if (this.token == facebookToken && facebookMode == "subscribe") {
            return ResponseEntity.ok(facebookChallenge)
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED)
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<String> getFacebookContent(@RequestBody FacebookReceived facebookContent) {
        sendContentToProcessResponse(facebookContent.getSenderId(), facebookContent.getTextMessage())
        return new ResponseEntity<>(HttpStatus.OK)
    }

    @Async
    void sendContentToProcessResponse(String clientId, String clientMessage){
        String response = botResponse.getBotResponse(clientId, clientMessage)
        sendResponseToFacebookAdapter(clientId, response)
    }

    void sendResponseToFacebookAdapter(String clientId, response){
        facebookAdapter.sendMessageToFacebook(clientId, response)
    }

}
