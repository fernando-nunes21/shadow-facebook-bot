package shadow

import groovy.transform.CompileDynamic
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import shadow.message.FacebookResponse

@Service
@CompileDynamic
class FacebookAdapter {

    @Value('${facebook.send.url}')
    String urlFacebook

    @Value('${facebook.token}')
    String tokenFacebook

    private final RestTemplate restTemplate

    FacebookAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate
    }

    void sendMessageToFacebook(String clientId, String message) {
        FacebookResponse facebookResponse = new FacebookResponse(clientId, message)
        String url = this.urlFacebook << this.tokenFacebook
        HttpHeaders headers = new HttpHeaders()
        headers.add('Content-Type', 'application/json; charset=utf-8')
        headers.add('Accept', 'application/json')
        HttpEntity request = new HttpEntity<>(facebookResponse, headers)
        restTemplate.postForEntity(url, request, String)
    }

}

