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
class ContextsParameters {

    private String language

    @JsonProperty('language.original')
    private String languageOriginal

    @JsonProperty('language-programming')
    private String languageProgramming

    @JsonProperty('language-programming.original')
    private String languageProgrammingOriginal

    private String locationEntity

    @JsonProperty('date-time')
    private DateTime dateTime

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

    String getLanguageOriginal() {
        languageOriginal
    }

    void setLanguageOriginal(String languageOriginal) {
        this.languageOriginal = languageOriginal
    }

    String getLanguageProgramming() {
        languageProgramming
    }

    void setLanguageProgramming(String languageProgramming) {
        this.languageProgramming = languageProgramming
    }

    String getLanguageProgrammingOriginal() {
        languageProgrammingOriginal
    }

    void setLanguageProgrammingOriginal(String languageProgrammingOriginal) {
        this.languageProgrammingOriginal = languageProgrammingOriginal
    }

}

