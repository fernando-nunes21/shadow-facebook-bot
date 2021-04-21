package shadow

import groovy.util.logging.Slf4j
import message.FacebookReceived
import message.FacebookResponse
import org.springframework.stereotype.Service

@Slf4j
@Service
class ShadowBot {

    private SendMessage sendMessage
    private BotResponse botResponse

    ShadowBot(SendMessage sendMessage, BotResponse botResponse) {
        this.sendMessage = sendMessage
        this.botResponse = botResponse
    }

    void responseConstructor(FacebookReceived facebook){
        String message = botResponse.botMessageConstructor(facebook.getTextMessage())
        FacebookResponse facebookResponse = new FacebookResponse(facebook.getSenderId(),message)
        sendMessage.sendMessageToFacebook(facebookResponse)
    }

}
