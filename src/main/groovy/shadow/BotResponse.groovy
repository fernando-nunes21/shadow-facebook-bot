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
                ClimateIntegration climateIntegration) {
        this.calendar = calendarIntegration
        this.botMessages = botMessages
        this.climateIntegration = climateIntegration
    }

    String botMessageConstructor(String clientMessage) {
        for (int i = 0; i < 4; i++) {
            if (clientMessage =~ this.botMessages.getInputMessages(i)) {
                if (this.botMessages.getOutputMessages(i) == ("call Calendar")) {
                    return this.calendar.getCalendarStatus()

                } else if (this.botMessages.getOutputMessages(i) == ("call Temperature")) {
                    return this.climateIntegration.getActualWheather()
                } else {
                    return this.botMessages.getOutputMessages(i)
                }
            }
        }
        return "Desculpe, eu to aprendendo ainda as coisas e nao entendi sua mensagem"
    }
}
