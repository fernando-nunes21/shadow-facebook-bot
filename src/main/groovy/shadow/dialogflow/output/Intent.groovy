package shadow.dialogflow.output

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.Canonical
import groovy.transform.CompileDynamic

@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
@CompileDynamic
class Intent {

    private String name
    private String displayName

    String getName() {
        name
    }

    void setName(String name) {
        this.name = name
    }

    String getDisplayName() {
        displayName
    }

    void setDisplayName(String displayName) {
        this.displayName = displayName
    }

}
