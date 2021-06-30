package shadow.dialogflow.input

import groovy.transform.ToString

@ToString(includeNames = true)
class QueryInput {
    private Text text

    QueryInput(String inputMessage){
        this.text = new Text(inputMessage)
    }

    Text getText() {
        return text
    }

    void setText(Text text) {
        this.text = text
    }
}
