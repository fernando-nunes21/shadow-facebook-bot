package shadow.dialogflow.input

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.ToString

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(includeNames = true)
class QueryParams {
    private Contexts contexts

    QueryParams(){

    }

    QueryParams(Contexts contexts) {
        this.contexts = contexts
    }

    Contexts getContexts() {
        return contexts
    }

    void setContexts(Contexts contexts) {
        this.contexts = contexts
    }
}
