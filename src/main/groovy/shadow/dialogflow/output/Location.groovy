package shadow.dialogflow.output

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Location {
    @JsonProperty("business-name")
    private String businessName
    private String city

    @JsonProperty("street-address")
    private String streetAddress
    private String shortcut
    private String country

    @JsonProperty("subadmin-area")
    private String subadminArea

    @JsonProperty("admin-area")
    private String adminArea

    @JsonProperty("zip-code")
    private String zipCode
    private String island


    Location(){

    }

    Location(String businessName, String city, String streetAddress, String shortcut, String country,
             String subadminArea, String adminArea, String zipCode, String island) {
        this.businessName = businessName
        this.city = city
        this.streetAddress = streetAddress
        this.shortcut = shortcut
        this.country = country
        this.subadminArea = subadminArea
        this.adminArea = adminArea
        this.zipCode = zipCode
        this.island = island
    }

    String getIsland() {
        return island
    }

    void setIsland(String island) {
        this.island = island
    }

    String getBusinessName() {
        return businessName
    }

    void setBusinessName(String businessName) {
        this.businessName = businessName
    }

    String getCity() {
        return city
    }

    void setCity(String city) {
        this.city = city
    }

    String getStreetAddress() {
        return streetAddress
    }

    void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress
    }

    String getShortcut() {
        return shortcut
    }

    void setShortcut(String shortcut) {
        this.shortcut = shortcut
    }

    String getCountry() {
        return country
    }

    void setCountry(String country) {
        this.country = country
    }

    String getSubAdminArea() {
        return subadminArea
    }

    void setSubAdminArea(String subadminArea) {
        this.subadminArea = subadminArea
    }

    String getAdminArea() {
        return adminArea
    }

    void setAdminArea(String adminArea) {
        this.adminArea = adminArea
    }

    String getZipCode() {
        return zipCode
    }

    void setZipCode(String zipCode) {
        this.zipCode = zipCode
    }

    String getFullLocation(){
        if(this.businessName != null){
            return this.businessName.toString()
        }
        else if(this.city != null){
            return this.businessName.toString()
        }
        else if(this.streetAddress != null){
            return this.businessName.toString()
        }
        else if(this.shortcut != null){
            return this.businessName.toString()
        }
        else if(this.country != null){
            return this.businessName.toString()
        }
        else if(this.adminArea != null){
            return this.businessName.toString()
        }
        else{
            return "Evento"
        }
    }
}
