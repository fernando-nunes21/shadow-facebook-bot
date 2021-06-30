package shadow

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import shadow.message.FacebookReceived


@Slf4j
@RestController
@RequestMapping(value="/webhook")
class ApplicationController {

    @Value('${facebook.verify.url}')
    private String token
    private FacebookAdapter facebookAdapter
    private BotResponse botResponse

    @Autowired
    private RestTemplate restTemplate

    ApplicationController(FacebookAdapter facebookAdapter, BotResponse botResponse) {
        this.facebookAdapter = facebookAdapter
        this.botResponse = botResponse
    }

    @Bean
    static RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build()
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
        String clientId = facebookContent.senderId
        String response = botResponse.getBotResponse(clientId, facebookContent.textMessage)
        facebookAdapter.sendMessageToFacebook(clientId, response)
        return new ResponseEntity<>(HttpStatus.OK)
    }

}
