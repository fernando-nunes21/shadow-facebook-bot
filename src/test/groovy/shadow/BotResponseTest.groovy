package shadow

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonOutput
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import shadow.dialogflow.DialogflowAPI
import shadow.dialogflow.output.DialogOutput
import shadow.dialogflow.output.Location
import shadow.dialogflow.output.Parameters
import shadow.dialogflow.output.QueryResult
import spock.lang.Specification

class BotResponseTest extends Specification {

    private final CalendarAPI calendarAPI = Mock(CalendarAPI)
    private final DialogflowAPI dialogflowAPI = Mock(DialogflowAPI)
    private final ObjectMapper objectMapper = new ObjectMapper()
    private BotResponse botResponse = new BotResponse(calendarAPI, dialogflowAPI, objectMapper)

    def "get a bot response when call" () {
        given:
        String clientMessage = "ola"
        String clientId = "12345"
        String botResponseMessage = "Olá, tudo certo?"
        DialogOutput dialogOutput = new DialogOutput(queryResult: new QueryResult(fulfillmentText: "Olá, tudo certo?"))

        dialogflowAPI.getDialogFlowResponse(clientMessage, clientId) >>
                new ResponseEntity<String>(JsonOutput.toJson(dialogOutput), HttpStatus.OK)

        when:
        String botMessage = botResponse.getBotResponse(clientId, clientMessage)

        then:
        assert botMessage == botResponseMessage
    }

    def "get a bot response when call a calendar set event" () {
        given:
        String clientMessage = "quero agendar uma hora"
        String clientId = "12345"
        String botResponseMessage = "Por favor, me informe o dia e a hora do evento"
        DialogOutput dialogOutput = new DialogOutput(
                queryResult: new QueryResult(
                        parameters: new Parameters(
                                date: "",
                                time: "",
                                location: new Location(
                                        city: ""
                                )
                        ),
                        fulfillmentText: "callCalendarSetEvent"
                )
        )


        dialogflowAPI.getDialogFlowResponse(clientMessage, clientId) >>
                new ResponseEntity<String>(JsonOutput.toJson(dialogOutput), HttpStatus.OK)

        when:
        String botMessage = botResponse.getBotResponse(clientId, clientMessage)

        then:
        assert botMessage == botResponseMessage
    }

    def "get a bot response when call calendar weak plan"() {
        given:
        String clientMessage = "quero agendar uma hora"
        String clientId = "12345"
        String botResponseMessage = "Não há eventos registrados essa semana"
        DialogOutput dialogOutput = new DialogOutput(
                queryResult: new QueryResult(
                        fulfillmentText: "callCalendarWeakPlan"
                )
        )


        dialogflowAPI.getDialogFlowResponse(clientMessage, clientId) >>
                new ResponseEntity<String>(JsonOutput.toJson(dialogOutput), HttpStatus.OK)

        calendarAPI.calendarWeekEvents >> "Não há eventos registrados essa semana"
        when:
        String botMessage = botResponse.getBotResponse(clientId, clientMessage)

        then:
        assert botMessage == botResponseMessage
    }

    def "get a bot response when bot context contains a day"() {
        given:
        String clientMessage = "quero agendar uma hora"
        String clientId = "12345"
        String botResponseMessage = "Por favor, me informe o local do evento"
        DialogOutput dialogOutput = new DialogOutput(
                queryResult: new QueryResult(
                        parameters: new Parameters(
                                date: "",
                                time: "10"
                        ),
                        fulfillmentText: "callCalendarSetEvent"
                )
        )


        dialogflowAPI.getDialogFlowResponse(clientMessage, clientId) >>
                new ResponseEntity<String>(JsonOutput.toJson(dialogOutput), HttpStatus.OK)

        botResponse.context = ["12345":["eventDay":"segunda"]]

        when:
        String botMessage = botResponse.getBotResponse(clientId, clientMessage)

        then:
        assert botMessage == botResponseMessage
    }

}
