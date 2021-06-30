package shadow.dialogflow.input

import groovy.transform.ToString

@ToString(includeNames = true)
class Text {
    private String text
    private String language_code

    Text(String text){
        this.text = text
        this.language_code = "pt-BR"
    }

    String getText() {
        return text
    }

    void setText(String text) {
        this.text = text
    }

    String getLanguage_code() {
        return language_code
    }

    void setLanguage_code(String language_code) {
        this.language_code = language_code
    }
}
