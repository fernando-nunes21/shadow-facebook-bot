package shadow.dialogflow.output

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Intent {
    private String name
    private String displayName

    Intent(){

    }

    Intent(String name, String displayName) {
        this.name = name
        this.displayName = displayName
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getDisplayName() {
        return displayName
    }

    void setDisplayName(String displayName) {
        this.displayName = displayName
    }
}
