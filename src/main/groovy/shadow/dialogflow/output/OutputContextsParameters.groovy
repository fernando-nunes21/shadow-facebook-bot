package shadow.dialogflow.output

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.CompileDynamic

@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
@CompileDynamic
class OutputContextsParameters {

    private String language

    @JsonProperty('language.original')
    private String languageOriginal

    @JsonProperty('language-programming')
    private String languageProgramming

    @JsonProperty('language-programming.original')
    private String languageProgrammingOriginal

    @JsonProperty('date-time')
    private DateTime dateTime

    private String locationEntity

    DateTime getDateTime() {
        dateTime
    }

    void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime
    }

    String getLocationEntity() {
        locationEntity
    }

    void setLocationEntity(String locationEntity) {
        this.locationEntity = locationEntity
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
