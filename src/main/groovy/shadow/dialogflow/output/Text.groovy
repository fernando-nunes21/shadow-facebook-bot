package shadow.dialogflow.output

import groovy.transform.Canonical
import groovy.transform.CompileDynamic

@Canonical
@CompileDynamic
class Text {

    private List<String> text

    List<String> getText() {
        text
    }

    void setText(List<String> text) {
        this.text = text
    }

}
