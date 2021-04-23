package shadow

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class BotResponseTest {
    private BotResponse botResponse
    private ClimateIntegration climateIntegration
    private CalendarIntegration calendarIntegration

    @BeforeEach
    void setUp(){
        climateIntegration = Mockito.mock(ClimateIntegration)
        calendarIntegration = Mockito.mock(CalendarIntegration)
        botResponse = new BotResponse(calendarIntegration, new BotMessages(), climateIntegration)
    }

    @Test
    void botMessageConstructorTest(){
        String messageReturn = "Opa, o meu nome é Shadow"
        assert messageReturn == this.botResponse.botMessageConstructor("Qual o seu nome?")
    }

}
