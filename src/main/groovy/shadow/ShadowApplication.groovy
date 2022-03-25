package shadow

import groovy.transform.CompileDynamic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@CompileDynamic
class ShadowApplication {

    static void main(String[] args) {
        SpringApplication.run(ShadowApplication, args)
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        restTemplateBuilder.build()
    }

}

