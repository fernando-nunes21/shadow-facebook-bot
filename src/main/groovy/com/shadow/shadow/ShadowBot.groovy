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
    String token
    String botMessage
    RestTemplate restTemplate = new RestTemplate()
    ReceivedFacebookMessage receivedMessage

    void receivedClientMessage(LinkedHashMap message){
        receivedMessage = new ReceivedFacebookMessage(message)
    }

    /*void verifyFacebookClientMessage(){
        String facebookClientMessage = receivedMessage.getReceivedMessage().toLowerCase()
        if(facebookClientMessage.contains("seu") || facebookClientMessage.contains("teu") && facebookClientMessage.contains("nome")){
            this.botMessage = "Opa, o meu nome é Shadow"
        }
        else if(facebookClientMessage.contains("sua") || facebookClientMessage.contains("tua") && facebookClientMessage.contains("idade")){
            this.botMessage = "Eu tenho alguns dias de vida"
        }
        else{
            this.botMessage = "Desculpe, eu to aprendendo ainda as coisas e nao entendi sua mensagem"
        }
    }  Recriar optimizado*/


    void sendMessage(){
        String url = "https://graph.facebook.com/v10.0/me/messages?access_token="+this.token
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON))

        FacebookResponse facebookResponse = new FacebookResponse("RESPONSE",new Recipient(receivedMessage.getIdSender()),new Message(botMessage))
        HttpEntity entity = new HttpEntity<>(facebookResponse,headers)
        ResponseEntity<String> response = this.restTemplate.postForEntity(url, entity, String)
    }

}
