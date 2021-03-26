package com.shadow.shadow


import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ShadowBot {

    @Value('${facebook.token}')
    private String token
    private RestTemplate restTemplate

    ShadowBot() {
        this.restTemplate = new RestTemplate()
    }

    void verifyFacebookClientMessage(FacebookReceived message){
        String facebookClientMessage = message.getEntry().get(0).getMessaging().get(0).getMessage().getText().toLowerCase()
        String facebookClientId = message.getEntry().get(0).getMessaging().get(0).getSender().getId().toString()
        String botMessage

        if(facebookClientMessage.contains("nome") && (facebookClientMessage.contains("seu") || facebookClientMessage.contains("teu"))) {
            botMessage = "Opa, o meu nome é Shadow"
        }
        else if((facebookClientMessage.contains("idade") && (facebookClientMessage.contains("sua") || facebookClientMessage.contains("tua"))) || (facebookClientMessage.contains("quantos") && facebookClientMessage.contains("anos"))) {
            botMessage = "Eu não tenho nem 2 semanas de vida :D"
        }
        else{
            botMessage = "Desculpe, eu to aprendendo ainda as coisas e nao entendi sua mensagem"
        }

        sendToFacebook(facebookClientId, botMessage)
    }


    void sendToFacebook(String facebookClientId, String botMessage){
        FacebookResponse facebookResponse = new FacebookResponse(facebookClientId,botMessage)
        SendMessage sendMessage = new SendMessage(facebookResponse,this.restTemplate,this.token)
        sendMessage.sendMessageToFacebook()
    }

}
