package com.shadow.shadow

import org.springframework.stereotype.Service


@Service
class ShadowBot {

    private SendMessage sendMessage

    ShadowBot(SendMessage sendMessage) {
        this.sendMessage = sendMessage
    }

    void verifyFacebookClientMessage(FacebookReceived message){
        String facebookClientMessage = message.getEntry().get(0).getMessaging().get(0).getMessage().getText().toLowerCase()
        String facebookClientId = message.getEntry().get(0).getMessaging().get(0).getSender().getId().toString()
        String botMessage

        if(verifyBotNameQuestion(facebookClientMessage)) {
            botMessage = "Opa, o meu nome é Shadow"
        }
        else if(verifyBotAgeQuestion(facebookClientMessage)) {
            botMessage = "Eu não tenho nem 2 semanas de vida :D"
        }
        else{
            botMessage = "Desculpe, eu to aprendendo ainda as coisas e nao entendi sua mensagem"
        }

        sendToFacebook(facebookClientId, botMessage)
    }

    private boolean verifyBotAgeQuestion(String facebookClientMessage) {
        (facebookClientMessage.contains("idade") && (facebookClientMessage.contains("sua") ||
                facebookClientMessage.contains("tua"))) || (facebookClientMessage.contains("quantos") &&
                facebookClientMessage.contains("anos"))
    }

    private static boolean verifyBotNameQuestion(String facebookClientMessage) {
        facebookClientMessage.contains("nome") && (facebookClientMessage.contains("seu") || facebookClientMessage.contains("teu"))
    }


    void sendToFacebook(String facebookClientId, String botMessage){
        FacebookResponse facebookResponse = new FacebookResponse(facebookClientId,botMessage)
        sendMessage.sendMessageToFacebook(facebookResponse)
    }

}
