package shadow.dialogflow.output

import groovy.transform.Canonical
import groovy.transform.CompileDynamic

@Canonical
@CompileDynamic
class OutputContexts {

    private String name
    private String lifespanCount
    private OutputContextsParameters parameters

    String getName() {
        name
    }

    void setName(String name) {
        this.name = name
    }

    String getLifespanCount() {
        lifespanCount
    }

    void setLifespanCount(String lifespanCount) {
        this.lifespanCount = lifespanCount
    }

    OutputContextsParameters getParameters() {
        parameters
    }

    void setParameters(OutputContextsParameters parameters) {
        this.parameters = parameters
    }

}
