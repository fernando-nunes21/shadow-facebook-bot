package com.shadow.shadow

import message.FacebookResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class SendMessage {

    @Value('${facebook.send.url}')
    private String urlFacebook

    @Value('${facebook.token}')
    private String token
    private RestTemplate restTemplate

    SendMessage() {
        this.restTemplate = new RestTemplate()
    }

    void sendMessageToFacebook(FacebookResponse message){
        String sendUrl = this.urlFacebook+this.token
        HttpHeaders headers = new HttpHeaders()
        HttpEntity content

        headers.setContentType(MediaType.APPLICATION_JSON)
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON))
        content = new HttpEntity<>(message,headers)
        this.restTemplate.postForEntity(sendUrl, content, String)
    }
}
