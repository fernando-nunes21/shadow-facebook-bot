package shadow.dialogflow.output

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.CompileDynamic

@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
@CompileDynamic
class Parameters {

    private String language
    private String locationEntity
    private Location location
    private String date
    private String time

    @JsonProperty('date-time')
    private DateTime dateTime

    @JsonProperty('language-programming')
    private String languageProgramming

    Location getLocation() {
        location
    }

    void setLocation(Location location) {
        this.location = location
    }

    String getDate() {
        date
    }

    void setDate(String date) {
        this.date = date
    }

    String getTime() {
        time
    }

    void setTime(String time) {
        this.time = time
    }

    String getLocationEntity() {
        locationEntity
    }

    void setLocationEntity(String locationEntity) {
        this.locationEntity = locationEntity
    }

    DateTime getDateTime() {
        dateTime
    }

    void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime
    }

    String getLanguage() {
        language
    }

    void setLanguage(String language) {
        this.language = language
    }

    String getLanguageProgramming() {
        languageProgramming
    }

    void setLanguageProgramming(String languageProgramming) {
        this.languageProgramming = languageProgramming
    }

}
