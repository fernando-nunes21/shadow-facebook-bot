package shadow.dialogflow.input

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.ToString
import shadow.dialogflow.input.DateTime

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(includeNames = true)
class ContextsParameters {
    private String language

    @JsonProperty("language.original")
    private String languageOriginal

    @JsonProperty("language-programming")
    private String languageProgramming

    @JsonProperty("language-programming.original")
    private String languageProgrammingOriginal

    private String locationEntity

    @JsonProperty("date-time")
    private DateTime dateTime

    ContextsParameters(){

    }

    ContextsParameters(String language, String languageOriginal, String languageProgramming,
                             String languageProgrammingOriginal, DateTime dateTime, String locationEntity) {
        this.language = language
        this.languageOriginal = languageOriginal
        this.languageProgramming = languageProgramming
        this.languageProgrammingOriginal = languageProgrammingOriginal
        this.dateTime = dateTime
        this.locationEntity = locationEntity
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

    String getLanguageOriginal() {
        return languageOriginal
    }

    void setLanguageOriginal(String languageOriginal) {
        this.languageOriginal = languageOriginal
    }

    String getLanguageProgramming() {
        return languageProgramming
    }

    void setLanguageProgramming(String languageProgramming) {
        this.languageProgramming = languageProgramming
    }

    String getLanguageProgrammingOriginal() {
        return languageProgrammingOriginal
    }

    void setLanguageProgrammingOriginal(String languageProgrammingOriginal) {
        this.languageProgrammingOriginal = languageProgrammingOriginal
    }
}
