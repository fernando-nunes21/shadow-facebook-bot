package shadow.dialogflow.input

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.ToString

@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(includeNames = true)
@CompileDynamic
class QueryParams {

    private Contexts contexts

    Contexts getContexts() {
        contexts
    }

    void setContexts(Contexts contexts) {
        this.contexts = contexts
    }

}
