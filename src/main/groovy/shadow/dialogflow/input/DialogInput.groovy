package shadow.dialogflow.input

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.ToString

@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(includeNames = true)
@CompileDynamic
class DialogInput {

    @JsonProperty('query_input')
    private QueryInput queryInput

    private QueryParams queryParams

    QueryParams getQueryParams() {
        queryParams
    }

    void setQueryParams(QueryParams queryParams) {
        this.queryParams = queryParams
    }

    QueryInput getQuery_input() {
        queryInput
    }

    void setQuery_input(QueryInput queryInput) {
        this.queryInput = queryInput
    }

}
