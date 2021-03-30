package message

import groovy.transform.ToString

@ToString(includeNames = true)
class Messaging {
    private Sender sender
    private Recipient recipient
    private String timestamp
    private MessageFromFacebook message

    Messaging() {

    }

    Sender getSender() {
        return sender
    }

    Recipient getRecipient() {
        return recipient
    }

    String getTimestamp() {
        return timestamp
    }

    MessageFromFacebook getMessage() {
        return message
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
