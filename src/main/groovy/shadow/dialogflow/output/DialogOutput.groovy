package shadow.dialogflow.output

import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
@CompileDynamic
class DialogOutput {

    private String responseId
    private QueryResult queryResult

    String getResponseId() {
        responseId
    }

    void setResponseId(String responseId) {
        this.responseId = responseId
    }

    QueryResult getQueryResult() {
        queryResult
    }

    void setQueryResult(QueryResult queryResult) {
        this.queryResult = queryResult
    }

    String getTextOutput() {
        this.queryResult.fulfillmentText
    }

    String getEventDay() {
        this.queryResult.parameters.date
    }

    String getEventHours() {
        this.queryResult.parameters.time
    }

    String getEventLocation() {
        this.queryResult.parameters.location
    }

    String getEventLocationData() {
        this.queryResult.parameters.location.fullLocation
    }

}
