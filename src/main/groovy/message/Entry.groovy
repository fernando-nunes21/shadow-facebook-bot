package message


import groovy.transform.ToString

@ToString(includeNames = true)
class Entry {
    private String id
    private String time
    private List<Messaging> messaging

    Entry() {

    }

    String getId() {
        return id
    }

    String getTime() {
        return time
    }

    List<Messaging> getMessaging() {
        return messaging
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

    String getClientSender(){
        return this.messaging.get(0).getSender().getId()
    }

}
