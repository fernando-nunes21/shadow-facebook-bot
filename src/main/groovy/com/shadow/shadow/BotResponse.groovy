package com.shadow.shadow

import message.FacebookReceived

class BotResponse {

    private String senderId
    private String botMessage


    String getSenderId() {
        return senderId
    }

    void setSenderId(FacebookReceived message) {
        this.senderId = message.getEntry().get(0).getMessaging().get(0).getSender().getId()
    }

    String getBotMessage() {
        return botMessage
    }

    void selectResponse(FacebookReceived message){
        String clientMessage = message.getEntry().get(0).getMessaging().get(0).getMessage().getText().toLowerCase()

        if(clientMessage.contains("idade") && (clientMessage.contains("sua") ||
                clientMessage.contains("tua")) || (clientMessage.contains("quantos") &&
                clientMessage.contains("anos"))){
            this.botMessage = "Eu não tenho nem 4 semanas de vida :D"
        }

        else if(clientMessage.contains("nome") && (clientMessage.contains("seu") || clientMessage.contains("teu"))){
            this.botMessage = "Opa, o meu nome é Shadow"
        }

        else{
            this.botMessage = "Desculpe, eu to aprendendo ainda as coisas e nao entendi sua mensagem"
        }
    }
}
