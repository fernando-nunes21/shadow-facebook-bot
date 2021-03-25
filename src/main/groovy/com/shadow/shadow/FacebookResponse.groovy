package com.shadow.shadow

import com.fasterxml.jackson.annotation.JsonProperty

class FacebookResponse {

    @JsonProperty("messaging_type")
    private String messagingType
    private Recipient recipient
    private MessageToFacebook message

    FacebookResponse(String messagingType, Recipient recipient, MessageToFacebook message) {
        this.messagingType = messagingType
        this.recipient = recipient
        this.message = message
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
