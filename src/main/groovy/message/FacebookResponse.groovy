package message

import com.fasterxml.jackson.annotation.JsonProperty

class FacebookResponse {

    @JsonProperty("messaging_type")
    private String messagingType = "RESPONSE"
    private Recipient recipient
    private MessageToFacebook message

    FacebookResponse(String recipient, String message) {
        this.recipient = new Recipient()
        this.recipient.setId(recipient)
        this.message = new MessageToFacebook(message)
    }

    String getMessagingType() {
        return messagingType
    }

    Recipient getRecipient() {
        return recipient
    }

    MessageToFacebook getMessage() {
        return message
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
