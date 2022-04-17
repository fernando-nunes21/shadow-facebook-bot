package shadow.message

import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
@CompileDynamic
class Entry {

    private String id
    private String time
    private List<Messaging> messaging

    String getId() {
        id
    }

    String getTime() {
        time
    }

    List<Messaging> getMessaging() {
        messaging
    }

    void setId(String id) {
        this.id = id
    }

    void setTime(String time) {
        this.time = time
    }

    void setMessaging(List<Messaging> messaging) {
        this.messaging = messaging
    }

    String getClientSender() {
        this.messaging.get(0).sender.id
    }

}
