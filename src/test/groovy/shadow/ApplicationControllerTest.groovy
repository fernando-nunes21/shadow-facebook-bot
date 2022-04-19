package shadow

import org.springframework.http.ResponseEntity
import shadow.message.Entry
import shadow.message.FacebookReceived
import shadow.message.MessageFromFacebook
import shadow.message.Messaging
import shadow.message.Sender
import spock.lang.Specification

class ApplicationControllerTest extends Specification{

    private final FacebookAdapter facebookAdapter = Mock(FacebookAdapter)
    private final BotResponse botResponse = Mock(BotResponse)
    private ApplicationController applicationController = new ApplicationController(facebookAdapter, botResponse)

    def "get facebook webhook validation confirmation true" () {
        given:
        String facebookMode = "subscribe"
        String facebookToken = "tokenFacebook"
        String facebookChallenge = "test"

        applicationController.token = facebookToken

        when:
        ResponseEntity<String> result =
                applicationController.getFacebookChallengeValidation(facebookMode, facebookToken, facebookChallenge)

        then:
        assert result.statusCode.toString() == "200 OK"
    }

    def "get facebook webhook validation confirmation false" () {
        given:
        String facebookMode = "subscribe"
        String facebookToken = "tokenFacebook"
        String facebookChallenge = "test"

        when:
        ResponseEntity<String> result =
                applicationController.getFacebookChallengeValidation(facebookMode, facebookToken, facebookChallenge)

        then:
        assert result.statusCode.toString() == "401 UNAUTHORIZED"
    }

    def "get facebook content request" () {
        given:
        String clientId = "12345"
        String clientMessage = "Mensagem do Usuario do Face"
        FacebookReceived facebookReceived = new FacebookReceived(
                entry: [new Entry(
                        messaging: [
                                new Messaging(
                                        sender: new Sender(id: clientId),
                                        message: new MessageFromFacebook(text: clientMessage))
                        ])
                ]
        )

        botResponse.getBotResponse(clientId, clientMessage) >> "Olá Usuário do face"

        when:
        ResponseEntity<String> result =
                applicationController.getFacebookContent(facebookReceived)

        then:
        assert result.statusCode.toString() == "200 OK"
    }
}
