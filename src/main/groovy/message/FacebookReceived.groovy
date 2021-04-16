package message


import groovy.transform.ToString

@ToString(includeNames = true)
class FacebookReceived {

    private String object
    private List<Entry> entry

    FacebookReceived() {

    }

    String getObject() {
        return object
    }

    List<Entry> getEntry() {
        return this.entry
    }

    String getSenderId(){
        return this.entry.get(0).getClientSender()
    }

    void setObject(String object) {
        this.object = object
    }

    void setEntry(List<Entry> entry) {
        this.entry = entry
    }
}
