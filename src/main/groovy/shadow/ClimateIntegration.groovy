package shadow

import climate.ClimateReceived
import org.springframework.beans.factory.annotation.Value
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Slf4j
@Service

class ClimateIntegration {
    @Value('${climate.token}')
    private String token

    @Value('${climate.url}')
    private String urlClimate

    @Value('${climate.apiWeather}')
    private String apiWeather
    private Integer id
    private RestTemplate restTemplate

    ClimateIntegration(){
        this.restTemplate = new RestTemplate()
        this.id = 5346
        this.token
        this.urlClimate
        this.apiWeather
    }

    String getActualWheather(){
        String sendUrl = this.urlClimate
        String apiCityRequest = this.apiWeather.replaceAll(":id",this.id.toString())
        sendUrl = sendUrl+apiCityRequest+this.token
        ClimateReceived result = this.restTemplate.getForObject(sendUrl, ClimateReceived.class)
        return "A temepratura atual é de "+result.getTemperature()+"ºC"
    }
}
