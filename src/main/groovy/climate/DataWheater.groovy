package climate

class DataWheater {

    private Integer temperature
    private String windDirection
    private Integer windVelocity
    private Integer humidity
    private String condition
    private Integer pressure
    private String icon
    private Integer sensation
    private String date

    Integer getTemperature() {
        return temperature
    }

    void setTemperature(Integer temperature) {
        this.temperature = temperature
    }

    String getWindDirection() {
        return windDirection
    }

    void setWindDirection(String windDirection) {
        this.windDirection = windDirection
    }

    Integer getWindVelocity() {
        return windVelocity
    }

    void setWindVelocity(Integer windVelocity) {
        this.windVelocity = windVelocity
    }

    Integer getHumidity() {
        return humidity
    }

    void setHumidity(Integer humidity) {
        this.humidity = humidity
    }

    String getCondition() {
        return condition
    }

    void setCondition(String condition) {
        this.condition = condition
    }

    Integer getPressure() {
        return pressure
    }

    void setPressure(Integer pressure) {
        this.pressure = pressure
    }

    String getIcon() {
        return icon
    }

    void setIcon(String icon) {
        this.icon = icon
    }

    Integer getSensation() {
        return sensation
    }

    void setSensation(Integer sensation) {
        this.sensation = sensation
    }

    String getDate() {
        return date
    }

    void setDate(String date) {
        this.date = date
    }
}
