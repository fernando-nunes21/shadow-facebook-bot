package shadow.dialogflow.input

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.ToString

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(includeNames = true)
class DialogInput {
    private QueryInput query_input
    private QueryParams queryParams

    DialogInput(){

    }

    DialogInput(String input){
        this.query_input = new QueryInput(input)
    }

    QueryParams getQueryParams() {
        return queryParams
    }

    void setQueryParams(QueryParams queryParams) {
        this.queryParams = queryParams
    }

    QueryInput getQuery_input() {
        return query_input
    }

    void setQuery_input(QueryInput query_input) {
        this.query_input = query_input
    }


}
