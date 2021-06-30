package shadow.dialogflow.output

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
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

    QueryResult(){

    }

    QueryResult(String queryText, String action, Parameters parameters, String allRequiredParamsPresent,
                String fulfillmentText, List<FulfillmentMessages> fulfillmentMessages,
                List<OutputContexts> outputContexts, Intent intent, String intentDetectionConfidence,
                String languageCode) {
        this.queryText = queryText
        this.action = action
        this.parameters = parameters
        this.allRequiredParamsPresent = allRequiredParamsPresent
        this.fulfillmentText = fulfillmentText
        this.fulfillmentMessages = fulfillmentMessages
        this.outputContexts = outputContexts
        this.intent = intent
        this.intentDetectionConfidence = intentDetectionConfidence
        this.languageCode = languageCode
    }

    String getQueryText() {
        return queryText
    }

    void setQueryText(String queryText) {
        this.queryText = queryText
    }

    String getAction() {
        return action
    }

    void setAction(String action) {
        this.action = action
    }

    Parameters getParameters() {
        return parameters
    }

    void setParameters(Parameters parameters) {
        this.parameters = parameters
    }

    String getAllRequiredParamsPresent() {
        return allRequiredParamsPresent
    }

    void setAllRequiredParamsPresent(String allRequiredParamsPresent) {
        this.allRequiredParamsPresent = allRequiredParamsPresent
    }

    String getFulfillmentText() {
        return fulfillmentText
    }

    void setFulfillmentText(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText
    }

    List<FulfillmentMessages> getFulfillmentMessages() {
        return fulfillmentMessages
    }

    void setFulfillmentMessages(List<FulfillmentMessages> fulfillmentMessages) {
        this.fulfillmentMessages = fulfillmentMessages
    }

    List<OutputContexts> getOutputContexts() {
        return outputContexts
    }

    void setOutputContexts(List<OutputContexts> outputContexts) {
        this.outputContexts = outputContexts
    }

    Intent getIntent() {
        return intent
    }

    void setIntent(Intent intent) {
        this.intent = intent
    }

    String getIntentDetectionConfidence() {
        return intentDetectionConfidence
    }

    void setIntentDetectionConfidence(String intentDetectionConfidence) {
        this.intentDetectionConfidence = intentDetectionConfidence
    }

    String getLanguageCode() {
        return languageCode
    }

    void setLanguageCode(String languageCode) {
        this.languageCode = languageCode
    }

}
