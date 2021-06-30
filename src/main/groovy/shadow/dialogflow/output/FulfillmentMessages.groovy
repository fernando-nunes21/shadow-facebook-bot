package shadow.dialogflow.output

class FulfillmentMessages {
    private Text text

    FulfillmentMessages(){

    }

    FulfillmentMessages(Text text) {
        this.text = text
    }

    Text getText() {
        return text
    }

    void setText(Text text) {
        this.text = text
    }
}
