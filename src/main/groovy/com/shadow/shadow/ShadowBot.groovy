package com.shadow.shadow


import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.http.MediaType


@Service
class ShadowBot {

    @Value('${facebook.token}')
    private String token
    private RestTemplate restTemplate = new RestTemplate()

    void receivedClientMessage(FacebookReceived message){
        String botMessage = verifyFacebookClientMessage(message)
    }

     void verifyFacebookClientMessage(FacebookReceived message){
        String facebookClientMessage = message.getEntry().get(0).getMessaging().get(0).getMessage().getText()
        String botMessage
        if(facebookClientMessage.contains("nome")){
            if(facebookClientMessage.contains("seu") || facebookClientMessage.contains("teu")){
                botMessage = "Opa, o meu nome é Shadow"
            }
            else{
                botMessage = "Desculpe, eu to aprendendo ainda as coisas e nao entendi sua mensagem"
            }
        }
        else if(facebookClientMessage.contains("sua") || facebookClientMessage.contains("tua") && facebookClientMessage.contains("idade")){
            if(facebookClientMessage.contains("sua") || facebookClientMessage.contains("tua")){
                botMessage = "Eu tenho alguns dias de vida"
            }
            else{
                botMessage = "Desculpe, eu to aprendendo ainda as coisas e nao entendi sua mensagem"

            }
        }
        else{
            botMessage = "Desculpe, eu to aprendendo ainda as coisas e nao entendi sua mensagem"
        }
        sendMessage(message, botMessage)
    }


    void sendMessage(FacebookReceived receivedMessage, String botMessage){
        String urlFacebook = "https://graph.facebook.com/v10.0/me/messages?access_token="+this.token
        Recipient recipient = new Recipient()
        recipient.setId(receivedMessage.getEntry().get(0).getMessaging().get(0).getSender().getId().toString())

        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON))

        FacebookResponse facebookResponse = new FacebookResponse("RESPONSE",recipient,new MessageToFacebook(botMessage))
        HttpEntity entity = new HttpEntity<>(facebookResponse,headers)
        ResponseEntity<String> response = this.restTemplate.postForEntity(urlFacebook, entity, String)
    }

}
