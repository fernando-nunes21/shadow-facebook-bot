package shadow.dialogflow.output

import groovy.transform.ToString

@ToString(includeNames = true)

class DialogOutput {
    private String responseId
    private QueryResult queryResult

    DialogOutput(){

    }

    DialogOutput(String responseId, QueryResult queryResult) {
        this.responseId = responseId
        this.queryResult = queryResult
    }

    String getResponseId() {
        return responseId
    }

    void setResponseId(String responseId) {
        this.responseId = responseId
    }

    QueryResult getQueryResult() {
        return queryResult
    }

    void setQueryResult(QueryResult queryResult) {
        this.queryResult = queryResult
    }

    String getTextOutput(){
        return this.queryResult.getFulfillmentText()
    }

    String getEventDay(){
        return this.queryResult.getParameters().getDate()
    }

    String getEventHours(){
        return this.queryResult.getParameters().getTime()
    }

    String getEventLocation(){
        return this.queryResult.getParameters().getLocation()
    }

    String getEventLocationData(){
        return this.queryResult.getParameters().getLocation().getFullLocation()
    }

}
