package com.shadow.shadow

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@Slf4j
@RestController
@RequestMapping(value="/webhook")
class ApplicationController {

    @Value('${facebook.verify.url}')
    private String token
    private ShadowBot shadowBot


    ApplicationController(ShadowBot shadowBot) {
        this.shadowBot = shadowBot
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<String> getWebhook(@RequestParam("hub.mode") String facebookMode,
                                             @RequestParam("hub.verify_token") String facebookToken,
                                             @RequestParam("hub.challenge") String facebookChallenge) {

        if (this.token == facebookToken && facebookMode == "subscribe") {
            log.info("Verificado")
            return ResponseEntity.ok(facebookChallenge)
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED)
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<String> postWebhook(@RequestBody FacebookReceived facebookBody) {
        log.info(facebookBody.toString())
        shadowBot.verifyFacebookClientMessage(facebookBody)
        return new ResponseEntity<>(HttpStatus.OK)
    }

}
