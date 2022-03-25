package shadow.dialogflow.input

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
@CompileDynamic
class Text {

    private String text

    @JsonProperty('language_code')
    private String languageCode

    String getText() {
        text
    }

    void setText(String text) {
        this.text = text
    }

    String getLanguageCode() {
        languageCode
    }

    void setLanguageCode(String languageCode) {
        this.languageCode = languageCode
    }

}
