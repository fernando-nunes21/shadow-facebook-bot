package com.shadow.shadow

import org.omg.CORBA.Object

class SendingFacebookMessage {
    String idRecipient
    String messageText

    SendingFacebookMessage(String clientId, String clientSendMessage){
        this.idRecipient = clientId
        this.messageText = clientSendMessage
    }

    LinkedHashMap returnFacebookMessage(){
        LinkedHashMap returnMessage = [messaging_type: 'RESPONSE',
                                       recipient: [id: this.idRecipient],
                                       message:[text: this.messageText]]
        return returnMessage
    }

}
