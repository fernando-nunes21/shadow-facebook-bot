package com.shadow.shadow

import message.FacebookReceived
import message.FacebookResponse
import org.springframework.stereotype.Service


@Service
class ShadowBot {

    private SendMessage sendMessage
    private BotResponse botResponse

    ShadowBot(SendMessage sendMessage, BotResponse botResponse) {
        this.sendMessage = sendMessage
        this.botResponse = botResponse
    }

    //TODO - nome ruim (metodo faz muito mais do que enviar pro facebook)
    void sendToFacebook(FacebookReceived facebook){
        String message = botResponse.selectResponse(facebook)
        FacebookResponse facebookResponse = new FacebookResponse(facebook.getSenderId(),message)
        sendMessage.sendMessageToFacebook(facebookResponse)
    }

}
