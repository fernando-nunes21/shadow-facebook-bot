package shadow.message

import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
@CompileDynamic
class FacebookReceived {

    private String object
    private List<Entry> entry

    String getObject() {
        object
    }

    List<Entry> getEntry() {
        this.entry
    }

    String getTextMessage() {
        this.entry.get(0).messaging.get(0).message.text.toLowerCase()
    }

    String getSenderId() {
        this.entry.get(0).clientSender
    }

    void setObject(String object) {
        this.object = object
    }

    void setEntry(List<Entry> entry) {
        this.entry = entry
    }

}
