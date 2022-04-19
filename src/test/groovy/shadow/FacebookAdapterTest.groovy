package shadow

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import shadow.message.FacebookResponse
import spock.lang.Specification

class FacebookAdapterTest extends Specification{

    private final RestTemplate restTemplate = Mock(RestTemplate)
    private FacebookAdapter facebookAdapter = new FacebookAdapter(restTemplate)

    def "send message to facebook" () {
        given:
        String clientId = "12345"
        String clientMessage = "Bot mandando mensagem"
        String url = "testtest"
        FacebookResponse facebookResponse = new FacebookResponse(clientId, clientMessage)
        HttpHeaders headers = new HttpHeaders()
        HttpEntity request = new HttpEntity<>(facebookResponse, headers)

        facebookAdapter.urlFacebook = "test"
        facebookAdapter.tokenFacebook = "test"

        when:
        facebookAdapter.sendMessageToFacebook(clientId, clientMessage)

        then:
        noExceptionThrown()
    }

}
