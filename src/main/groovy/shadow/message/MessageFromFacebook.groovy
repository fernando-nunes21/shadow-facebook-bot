package shadow.message

import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
@CompileDynamic
class MessageFromFacebook {

    private String mid
    private String text

    String getMid() {
        mid
    }

    String getText() {
        text
    }

    void setMid(String mid) {
        this.mid = mid
    }

    void setText(String text) {
        this.text = text
    }

}
