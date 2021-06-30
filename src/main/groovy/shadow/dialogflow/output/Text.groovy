package shadow.dialogflow.output

class Text {
    private List<String> text

    Text(){

    }

    Text(List<String> text) {
        this.text = text
    }

    List<String> getText() {
        return text
    }

    void setText(List<String> text) {
        this.text = text
    }
}
