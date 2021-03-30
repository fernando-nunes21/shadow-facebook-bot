package com.shadow.shadow

import message.FacebookReceived
import message.FacebookResponse
import org.springframework.stereotype.Service


@Service
class ShadowBot {

    private SendMessage sendMessage

    ShadowBot(SendMessage sendMessage) {
        this.sendMessage = sendMessage
    }

    BotResponse createBotResponse(FacebookReceived message){
        BotResponse botResponse = new BotResponse()
        botResponse.setSenderId(message)
        botResponse.selectResponse(message)
        return botResponse
    }

    void sendToFacebook(BotResponse botResponse){
        FacebookResponse facebookResponse = new FacebookResponse(botResponse.getSenderId(),botResponse.getBotMessage())
        sendMessage.sendMessageToFacebook(facebookResponse)
    }

}
