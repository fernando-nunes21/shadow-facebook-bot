package com.shadow.shadow

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.util.JSONPObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import javax.websocket.SendResult
import javax.xml.ws.Response

@RestController
@RequestMapping(value="/webhook")
class ApplicationController {

    @Value('${facebook.token}')
    String token;
    ShadowBot shadowBot


    ApplicationController(ShadowBot shadowBot) {
        this.shadowBot = shadowBot
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<String> getWebhook(@RequestParam("hub.mode") String facebookMode,
                                             @RequestParam("hub.verify_token") String facebookToken,
                                             @RequestParam("hub.challenge") String facebookChallenge) {

        if (this.token == facebookToken && facebookMode == "subscribe") {
            return ResponseEntity.ok(facebookChallenge)
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED)
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<String> postWebhook(@RequestBody FacebookReceived facebookBody) {
        println (facebookBody)
        shadowBot.receivedClientMessage(facebookBody)
        return new ResponseEntity<>(HttpStatus.OK)
    }

}
