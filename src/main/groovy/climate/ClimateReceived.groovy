package climate

class ClimateReceived {

    private Integer id
    private String name
    private String state
    private String county
    private DataWheater data

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getState() {
        return state
    }

    void setState(String state) {
        this.state = state
    }

    String getCounty() {
        return county
    }

    void setCounty(String county) {
        this.county = county
    }

    DataWheater getData() {
        return data
    }

    void setData(DataWheater data) {
        this.data = data
    }

    String getTemperature(){
        this.data.getTemperature().toString()
    }
}
