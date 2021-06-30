package shadow.dialogflow.output

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
@JsonIgnoreProperties(ignoreUnknown = true)
class DateTime {
    @JsonProperty("date_time")
    private String dateTime

    DateTime(){

    }

    DateTime(String dateTime) {
        this.dateTime = dateTime
    }

    String getDateTime() {
        return dateTime
    }

    void setDateTime(String dateTime) {
        this.dateTime = dateTime
    }
}
