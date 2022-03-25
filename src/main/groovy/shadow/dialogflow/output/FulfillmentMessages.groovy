package shadow.dialogflow.output

import groovy.transform.Canonical
import groovy.transform.CompileDynamic

@Canonical
@CompileDynamic
class FulfillmentMessages {

    private Text text

    Text getText() {
        text
    }

    void setText(Text text) {
        this.text = text
    }

}
