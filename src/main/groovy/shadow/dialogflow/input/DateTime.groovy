package shadow.dialogflow.input

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.CompileDynamic

@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
@CompileDynamic
class DateTime {

    @JsonProperty('date_time')
    private String dateTime

    String getDateTime() {
        dateTime
    }

    void setDateTime(String dateTime) {
        this.dateTime = dateTime
    }

}
