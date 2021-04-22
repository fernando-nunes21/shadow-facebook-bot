package shadow

import climate.ClimateReceived
import com.google.api.client.util.Value
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Slf4j
@Service
class ClimateIntegration {
    //@Value('${climate.token}')
    private String token

    //@Value('${climate.url}')
    private String urlClimate

    //@Value('${climate.apiWeather}')
    private String apiWeather
    private Integer id
    private RestTemplate restTemplate

    ClimateIntegration(){
        this.restTemplate = new RestTemplate()
        this.id = 5346
        this.token = "506551f0ade0bc6fcec84db6c5d03bc3"
        this.urlClimate = "http://apiadvisor.climatempo.com.br"
        this.apiWeather = "/api/v1/weather/locale/:id/current?token="
    }

    String getActualWheather(){
        String sendUrl = this.urlClimate
        log.info(this.token.toString())
        log.info(this.urlClimate.toString())
        log.info(this.apiWeather.toString())
        String apiCityRequest = this.apiWeather.replaceAll(":id",this.id.toString())
        sendUrl = sendUrl+apiCityRequest+this.token
        ClimateReceived result = this.restTemplate.getForObject(sendUrl, ClimateReceived.class)
        log.info(result.getTemperature())
        return result.getTemperature()
    }
}
