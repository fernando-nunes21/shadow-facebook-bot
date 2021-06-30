package shadow.message

import groovy.transform.ToString

@ToString(includeNames = true)
class MessageFromFacebook {
    private String mid
    private String text

    MessageFromFacebook() {

    }

    String getMid() {
        return mid
    }

    String getText() {
        return text
    }

    void setMid(String mid) {
        this.mid = mid
    }

    void setText(String text) {
        this.text = text
    }
}
