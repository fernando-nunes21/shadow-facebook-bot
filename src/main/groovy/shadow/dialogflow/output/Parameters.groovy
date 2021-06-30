package shadow.dialogflow.output

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.api.client.json.Json

@JsonIgnoreProperties(ignoreUnknown = true)
class Parameters {
    private String language
    private String locationEntity
    private Location location
    private String date
    private String time

    @JsonProperty("date-time")
    private DateTime dateTime

    @JsonProperty("language-programming")
    private String languageProgramming

    Parameters(){

    }

    Parameters(String language, String locationEntity, String languageProgramming,
               DateTime dateTime, Location location, String date, String time) {
        this.language = language
        this.location = location
        this.languageProgramming = languageProgramming
        this.dateTime = dateTime
        this.locationEntity = locationEntity
        this.date = date
        this.time = time
    }

    Location getLocation() {
        return location
    }

    void setLocation(Location location) {
        this.location = location
    }

    String getDate() {
        return date
    }

    void setDate(String date) {
        this.date = date
    }

    String getTime() {
        return time
    }

    void setTime(String time) {
        this.time = time
    }

    String getLocationEntity() {
        return locationEntity
    }

    void setLocationEntity(String locationEntity) {
        this.locationEntity = locationEntity
    }

    DateTime getDateTime() {
        return dateTime
    }

    void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime
    }

    String getLanguage() {
        return language
    }

    void setLanguage(String language) {
        this.language = language
    }

    String getLanguageProgramming() {
        return languageProgramming
    }

    void setLanguageProgramming(String languageProgramming) {
        this.languageProgramming = languageProgramming
    }

}
