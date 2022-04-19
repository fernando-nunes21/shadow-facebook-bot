package shadow.message

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.CompileDynamic

@Canonical
@CompileDynamic
class FacebookResponse {

    @JsonProperty('messaging_type')
    private String messagingType = 'RESPONSE'
    private Recipient recipient
    private MessageToFacebook message

    FacebookResponse(String clientId, String message){
        setRecipientId(clientId)
        setMessageText(message)
    }

    String getMessagingType() {
        messagingType
    }

    Recipient getRecipient() {
        recipient
    }

    MessageToFacebook getMessage() {
        message
    }

    void setMessagingType(String messagingType) {
        this.messagingType = messagingType
    }

    void setRecipient(Recipient recipient) {
        this.recipient = recipient
    }

    void setMessage(MessageToFacebook message) {
        this.message = message
    }

    void setMessageText(String message){
        this.message = new MessageToFacebook(text: message)
    }

    void setRecipientId(String id){
        this.recipient = new Recipient(id: id)
    }

}
