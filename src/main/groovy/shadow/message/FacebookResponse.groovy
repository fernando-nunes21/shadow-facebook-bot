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

}
