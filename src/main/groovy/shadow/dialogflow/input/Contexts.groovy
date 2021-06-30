package shadow.dialogflow.input

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.ToString

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(includeNames = true)
class Contexts {
    private String name
    private String lifespanCount
    private ContextsParameters parameters

    Contexts(){

    }

    Contexts(String name, String lifespanCount, ContextsParameters parameters) {
        this.name = name
        this.lifespanCount = lifespanCount
        this.parameters = parameters
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getLifespanCount() {
        return lifespanCount
    }

    void setLifespanCount(String lifespanCount) {
        this.lifespanCount = lifespanCount
    }

    Contexts getParameters() {
        return parameters
    }

    void setParameters(ContextsParameters parameters) {
        this.parameters = parameters
    }
}
