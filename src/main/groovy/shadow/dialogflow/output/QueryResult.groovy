package shadow.dialogflow.output

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.Canonical
import groovy.transform.CompileDynamic

@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
@CompileDynamic
class QueryResult {

    private String queryText
    private String action
    private Parameters parameters
    private String allRequiredParamsPresent
    private String fulfillmentText
    private List<FulfillmentMessages> fulfillmentMessages
    private List<OutputContexts> outputContexts
    private Intent intent
    private String intentDetectionConfidence
    private String languageCode

    String getQueryText() {
        queryText
    }

    void setQueryText(String queryText) {
        this.queryText = queryText
    }

    String getAction() {
        action
    }

    void setAction(String action) {
        this.action = action
    }

    Parameters getParameters() {
        parameters
    }

    void setParameters(Parameters parameters) {
        this.parameters = parameters
    }

    String getAllRequiredParamsPresent() {
        allRequiredParamsPresent
    }

    void setAllRequiredParamsPresent(String allRequiredParamsPresent) {
        this.allRequiredParamsPresent = allRequiredParamsPresent
    }

    String getFulfillmentText() {
        fulfillmentText
    }

    void setFulfillmentText(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText
    }

    List<FulfillmentMessages> getFulfillmentMessages() {
        fulfillmentMessages
    }

    void setFulfillmentMessages(List<FulfillmentMessages> fulfillmentMessages) {
        this.fulfillmentMessages = fulfillmentMessages
    }

    List<OutputContexts> getOutputContexts() {
        outputContexts
    }

    void setOutputContexts(List<OutputContexts> outputContexts) {
        this.outputContexts = outputContexts
    }

    Intent getIntent() {
        intent
    }

    void setIntent(Intent intent) {
        this.intent = intent
    }

    String getIntentDetectionConfidence() {
        intentDetectionConfidence
    }

    void setIntentDetectionConfidence(String intentDetectionConfidence) {
        this.intentDetectionConfidence = intentDetectionConfidence
    }

    String getLanguageCode() {
        languageCode
    }

    void setLanguageCode(String languageCode) {
        this.languageCode = languageCode
    }

}
