package com.shadow.shadow

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

class SendMessage {

    private String urlFacebook="https://graph.facebook.com/v10.0/me/messages?access_token="
    private FacebookResponse message
    private HttpHeaders headers = new HttpHeaders()
    private HttpEntity content
    private RestTemplate restTemplate

    SendMessage(FacebookResponse message, RestTemplate restTemplate, String token) {
        this.message = message
        this.restTemplate = restTemplate
        this.urlFacebook = urlFacebook+token
    }

    FacebookResponse getMessage() {
        return message
    }

    void setMessage(FacebookResponse message) {
        this.message = message
    }

    HttpHeaders getHeaders() {
        return headers
    }

    void setHeaders(HttpHeaders headers) {
        this.headers = headers
    }

    HttpEntity getContent() {
        return content
    }

    void setContent(HttpEntity content) {
        this.content = content
    }

    RestTemplate getRestTemplate() {
        return restTemplate
    }

    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate
    }

    void sendMessageToFacebook(){
        headers.setContentType(MediaType.APPLICATION_JSON)
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON))
        content = new HttpEntity<>(message,headers)
        this.restTemplate.postForEntity(urlFacebook, content, String)
    }
}
