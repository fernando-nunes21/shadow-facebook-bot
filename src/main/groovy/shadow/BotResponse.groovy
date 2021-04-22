package shadow

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class BotResponse {

    private CalendarIntegration calendar
    private BotMessages botMessages
    private ClimateIntegration climateIntegration

    BotResponse(CalendarIntegration calendarIntegration, BotMessages botMessages,
                ClimateIntegration climateIntegration){
        this.calendar = calendarIntegration
        this.botMessages = botMessages
        this.climateIntegration = climateIntegration
    }

    String botMessageConstructor(String clientMessage){
        String message

        for (int i=0;i<4;i++){
            if(clientMessage =~ this.botMessages.getInputMessages(i)){
                if(this.botMessages.getOutputMessages(i) == ("call Calendar")){
                    List<String> calendarResponse = this.calendar.getCalendarStatus()
                    if(calendarResponse.isEmpty()){
                        message = "Não há eventos na minha agenda essa semana!"
                    }
                    else{
                        message = "Olha só, minha semana tá assim: \n"
                        for(String event : calendarResponse){
                            message = message+event+"\n"
                        }
                    }
                }
                else if(this.botMessages.getOutputMessages(i) == ("call Temperature")){
                    String temperature = this.climateIntegration.getActualWheather()
                    message = "A temperatura atual é de "+temperature+"ºC"
                }
                else{
                    message = this.botMessages.getOutputMessages(i)
                }
            }
        }
        return message ? message : "Desculpe, eu to aprendendo ainda as coisas e nao entendi sua mensagem"
    }
}
