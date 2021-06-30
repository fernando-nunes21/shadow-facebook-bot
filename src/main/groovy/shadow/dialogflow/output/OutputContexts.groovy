package shadow.dialogflow.output

class OutputContexts {
    private String name
    private String lifespanCount
    private OutputContextsParameters parameters

    OutputContexts(){

    }

    OutputContexts(String name, String lifespanCount, OutputContextsParameters parameters) {
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

    OutputContextsParameters getParameters() {
        return parameters
    }

    void setParameters(OutputContextsParameters parameters) {
        this.parameters = parameters
    }
}
