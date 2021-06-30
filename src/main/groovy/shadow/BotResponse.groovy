package shadow

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import shadow.dialogflow.DialogflowAPI
import shadow.dialogflow.output.DialogOutput

import java.text.SimpleDateFormat

@Slf4j
@Service

class BotResponse {

    private CalendarAPI calendarAPI
    private DialogflowAPI dialogflowAPI
    private Map<String, Map<String, String>> context
    private ObjectMapper objectMapper

    BotResponse(CalendarAPI calendarAPI, DialogflowAPI dialogflowAPI, ObjectMapper objectMapper) {
        this.calendarAPI = calendarAPI
        this.dialogflowAPI = dialogflowAPI
        this.context = [:]
        this.objectMapper = objectMapper
    }

    String getBotResponse(String clientId, String clientMessage){
        ResponseEntity<String> dialogFlowResponse = dialogflowAPI.getDialogFlowResponse(clientMessage, clientId)
        return getMessageResponse(objectMapper.readValue(dialogFlowResponse.body, DialogOutput.class), clientId)
    }

    private String getMessageResponse(DialogOutput dialogOutput, String clientId){
        if (dialogOutput.getTextOutput() == "callCalendarWeakPlan") {
            return this.calendarAPI.getCalendarWeekEvents()
        } else if (dialogOutput.getTextOutput() == "callCalendarSetEvent"){
            Map<String, String> newContextContent = new HashMap<>()
            String contextMessageEmptySpaces
            if (this.context.get(clientId) == null){
                newContextContent.put("eventDay", dialogOutput.getEventDay())
                newContextContent.put("eventHours", dialogOutput.getEventHours())
                if (dialogOutput.getEventLocation() == null){
                    newContextContent.put("eventLocation", null)
                }
                else{
                    newContextContent.put("eventLocation", dialogOutput.getEventLocationData())
                }
                this.context.put(clientId, newContextContent)
            } else {
                if (this.context.get(clientId).get("eventDay") == null ||
                    this.context.get(clientId).get("eventDay") == ""){
                    this.context.get(clientId).put("eventDay", dialogOutput.getEventDay())
                }
                if (this.context.get(clientId).get("eventHours") == null ||
                    this.context.get(clientId).get("eventHours") == ""){
                    this.context.get(clientId).put("eventHours", dialogOutput.getEventHours())
                }
                if (this.context.get(clientId).get("eventLocation") == null){
                    if (dialogOutput.getEventLocation() == null || dialogOutput.getEventLocationData() == ""){
                        this.context.get(clientId).put("eventLocation", null)
                    }
                    else{
                        this.context.get(clientId).put("eventLocation",
                                dialogOutput.getEventLocationData().toString())
                    }
                }
            }
            contextMessageEmptySpaces = getMessageForEmptyContextSpace(clientId)
            if (contextMessageEmptySpaces != ""){
                return contextMessageEmptySpaces
            } else {
                long eventDataConverted = convertDateTimeToMilliseconds(
                        this.context.get(clientId).get("eventDay"),
                        this.context.get(clientId).get("eventHours"))
                String eventLocation = this.context.get(clientId).get("eventLocation")
                this.context.remove(clientId)
                return this.calendarAPI.getCalendarSetConfirmation(eventDataConverted, eventLocation)
            }
        } else{
            this.context.remove(clientId)
            return dialogOutput.getTextOutput()
        }
    }

    private String getMessageForEmptyContextSpace(String clientId){
        if ((this.context.get(clientId).get("eventDay") == null ||
                  this.context.get(clientId).get("eventDay") == "") &&
                  (this.context.get(clientId).get("eventHours") == null ||
                  this.context.get(clientId).get("eventHours") == "") &&
                  this.context.get(clientId).get("eventLocation") == null){
            return "Por favor, informe o Dia, Hora e Local do evento"
        } else if ((this.context.get(clientId).get("eventDay") == null ||
                   this.context.get(clientId).get("eventDay") == "") &&
                  (this.context.get(clientId).get("eventHours") == null ||
                   this.context.get(clientId).get("eventHours") == "")){
            return "Por favor, me informe o dia e a hora do evento"
        } else if ((this.context.get(clientId).get("eventDay") == null ||
                   this.context.get(clientId).get("eventDay") == "") &&
                   this.context.get(clientId).get("eventLocation") == null){
            return "Por favor, me informe o dia e o local do evento"
        } else if ((this.context.get(clientId).get("eventHours") == null ||
                   this.context.get(clientId).get("eventHours") == "") &&
                   this.context.get(clientId).get("eventLocation") == null){
            return "Por favor, me informe a hora e o local do evento"
        } else if (this.context.get(clientId).get("eventDay") == null ||
                  this.context.get(clientId).get("eventDay") == "") {
            return "Por favor, me informe o dia do evento"
        } else if (this.context.get(clientId).get("eventHours") == null ||
                   this.context.get(clientId).get("eventHours") == ""){
            return "Por favor, me informe a hora do evento"
        } else if (this.context.get(clientId).get("eventLocation") == null){
            return "Por favor, me informe o local do evento"
        }
        return ""
    }
    private static long convertDateTimeToMilliseconds(String eventDay, String eventHours){
        String eventDateToFormat = eventDay.substring(0,11) << eventHours.substring(11,19)
        SimpleDateFormat dataModelToFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        Date EventDateInMilliseconds = dataModelToFormat.parse(eventDateToFormat.toString())
        return EventDateInMilliseconds.getTime()
    }

}
