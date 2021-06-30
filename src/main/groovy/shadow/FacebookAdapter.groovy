package shadow

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import shadow.message.FacebookResponse


@Service
class FacebookAdapter {

    @Value('${facebook.send.url}')
    private String urlFacebook

    @Value('${facebook.token}')
    private String token

    private RestTemplate restTemplate

    FacebookAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate
    }

    void sendMessageToFacebook(String clientId, String message){
        FacebookResponse facebookResponse = new FacebookResponse(clientId, message)
        String url = this.urlFacebook << this.token
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON))
        HttpEntity request = new HttpEntity<>(facebookResponse,headers)
        restTemplate.postForEntity(url, request, String)
    }
}
