package shadow.dialogflow.input

import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
@CompileDynamic
class QueryInput {

    private Text text

    Text getText() {
        text
    }

    void setText(Text text) {
        this.text = text
    }

}
