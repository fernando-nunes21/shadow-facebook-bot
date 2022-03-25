package shadow.dialogflow.output

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.CompileDynamic

@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
@CompileDynamic
class Location {

    @JsonProperty('business-name')
    private String businessName
    private String city

    @JsonProperty('street-address')
    private String streetAddress
    private String shortcut
    private String country

    @JsonProperty('subadmin-area')
    private String subadminArea

    @JsonProperty('admin-area')
    private String adminArea

    @JsonProperty('zip-code')
    private String zipCode
    private String island

    String getIsland() {
        island
    }

    void setIsland(String island) {
        this.island = island
    }

    String getBusinessName() {
        businessName
    }

    void setBusinessName(String businessName) {
        this.businessName = businessName
    }

    String getCity() {
        city
    }

    void setCity(String city) {
        this.city = city
    }

    String getStreetAddress() {
        streetAddress
    }

    void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress
    }

    String getShortcut() {
        shortcut
    }

    void setShortcut(String shortcut) {
        this.shortcut = shortcut
    }

    String getCountry() {
        country
    }

    void setCountry(String country) {
        this.country = country
    }

    String getSubAdminArea() {
        subadminArea
    }

    void setSubAdminArea(String subadminArea) {
        this.subadminArea = subadminArea
    }

    String getAdminArea() {
        adminArea
    }

    void setAdminArea(String adminArea) {
        this.adminArea = adminArea
    }

    String getZipCode() {
        zipCode
    }

    void setZipCode(String zipCode) {
        this.zipCode = zipCode
    }

    String getFullLocation() {
        if (this.businessName != null) {
            return this.businessName.toString()
        } else if (this.city != null) {
            return this.businessName.toString()
        } else if (this.streetAddress != null) {
            return this.businessName.toString()
        } else if (this.shortcut != null) {
            return this.businessName.toString()
        } else if (this.country != null) {
            return this.businessName.toString()
        } else if (this.adminArea != null) {
            return this.businessName.toString()
        }
        'Evento'
    }

}
