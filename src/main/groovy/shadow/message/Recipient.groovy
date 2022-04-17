package shadow.message

import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
@CompileDynamic
class Recipient {

    private String id

    String getId() {
        id
    }

    void setId(String id) {
        this.id = id
    }

}
