package message

import groovy.transform.ToString

@ToString(includeNames = true)
class Sender {
    private String id

    Sender() {

    }

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }
}
