package shadow

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class BotResponse {

    private CalendarIntegration calendar
    private BotMessages botMessages

    BotResponse(CalendarIntegration calendarIntegration, BotMessages botMessages){
        this.calendar = calendarIntegration
        this.botMessages = botMessages
    }

    String botMessageConstructor(String clientMessage){
        String message

        for (int i=0;i<3;i++){
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
                else{
                    message = this.botMessages.getOutputMessages(i)
                }
            }
        }
        return message ? message : "Desculpe, eu to aprendendo ainda as coisas e nao entendi sua mensagem"
    }
}
