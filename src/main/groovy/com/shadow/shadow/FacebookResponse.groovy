package com.shadow.shadow

import com.fasterxml.jackson.annotation.JsonProperty

class FacebookResponse {

    @JsonProperty("messaging_type")
    private String messagingType
    private Recipient recipient
    private Message message

    FacebookResponse(String messagingType, Recipient recipient, Message message) {
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

    Message getMessage() {
        return message
    }

}
