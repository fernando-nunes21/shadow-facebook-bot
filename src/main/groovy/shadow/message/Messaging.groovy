package shadow.message

import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
@CompileDynamic
class Messaging {

    private Sender sender
    private Recipient recipient
    private String timestamp
    private MessageFromFacebook message

    Sender getSender() {
        sender
    }

    Recipient getRecipient() {
        recipient
    }

    String getTimestamp() {
        timestamp
    }

    MessageFromFacebook getMessage() {
        message
    }

    void setSender(Sender sender) {
        this.sender = sender
    }

    void setRecipient(Recipient recipient) {
        this.recipient = recipient
    }

    void setTimestamp(String timestamp) {
        this.timestamp = timestamp
    }

    void setMessage(MessageFromFacebook message) {
        this.message = message
    }

}
