package shadow.message

import groovy.transform.ToString

@ToString(includeNames = true)
class Recipient {
    private String id

    Recipient() {

    }

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }
}
