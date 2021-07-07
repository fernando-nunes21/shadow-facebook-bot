package shadow

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import shadow.dialogflow.DialogflowAPI
import shadow.dialogflow.output.DialogOutput
import java.text.SimpleDateFormat

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
            String contextMessage
            if (isClientContextEmpty(clientId)){
                createClientContextFields(clientId, dialogOutput)
            } else {
                updateClientContextFields(clientId, dialogOutput)
            }
            contextMessage = getMessageForEmptyContextField(clientId)
            if (isContextMessageNotEmpty(contextMessage)){
                return contextMessage
            } else {
                long eventDataConverted = convertDateTimeToMilliseconds(
                        this.context.get(clientId).get("eventDay"),
                        this.context.get(clientId).get("eventHours"))
                String eventLocation = this.context.get(clientId).get("eventLocation")
                this.context.remove(clientId)
                return this.calendarAPI.getCalendarSetEventConfirmation(eventDataConverted, eventLocation)
            }
        } else{
            this.context.remove(clientId)
            return dialogOutput.getTextOutput()
        }
    }

    private boolean isClientContextEmpty(String clientId){
        return this.context.get(clientId) == null
    }

    private boolean isContextMessageNotEmpty(String contextMessage){
        return contextMessage != ""
    }

    private void createClientContextFields(String clientId, DialogOutput dialogOutput){
        Map<String, String> newContextContent = new HashMap<>()
        newContextContent.put("eventDay", dialogOutput.getEventDay())
        newContextContent.put("eventHours", dialogOutput.getEventHours())
        if (dialogOutput.getEventLocation() == null){
            newContextContent.put("eventLocation", null)
        } else{
            newContextContent.put("eventLocation", dialogOutput.getEventLocationData())
        }
        this.context.put(clientId, newContextContent)
    }

    private void updateClientContextFields(String clientId, DialogOutput dialogOutput){
        if (isClientContextEventDayFieldEmptyOrNull(clientId)){
            this.context.get(clientId).put("eventDay", dialogOutput.getEventDay())
        }
        if (isClientContextEventHoursFieldEmptyOrNull(clientId)){
            this.context.get(clientId).put("eventHours", dialogOutput.getEventHours())
        }
        if (isClientContextEventLocationFieldNull(clientId)){
            if (dialogOutput.getEventLocation() == null || dialogOutput.getEventLocationData() == ""){
                this.context.get(clientId).put("eventLocation", null)
            } else{
                this.context.get(clientId).put("eventLocation",
                        dialogOutput.getEventLocationData().toString())
            }
        }
    }

    private boolean isClientContextEventDayFieldEmptyOrNull(String clientId){
        return this.context.get(clientId).get("eventDay") == null ||
                this.context.get(clientId).get("eventDay") == ""
    }

    private boolean isClientContextEventHoursFieldEmptyOrNull(String clientId){
        return this.context.get(clientId).get("eventHours") == null ||
                this.context.get(clientId).get("eventHours") == ""
    }

    private boolean isClientContextEventLocationFieldNull(String clientId){
        return this.context.get(clientId).get("eventLocation") == null
    }

    private String getMessageForEmptyContextField(String clientId){
        if (isAllClientContextFieldsEmpty(clientId)){
            return "Por favor, informe o Dia, Hora e Local do evento"
        } else if (isClientContextFieldDayAndContextFieldHoursEmpty(clientId)){
            return "Por favor, me informe o dia e a hora do evento"
        } else if (isClientContextFieldDayAndContextFieldLocationEmpty(clientId)){
            return "Por favor, me informe o dia e o local do evento"
        } else if (isClientContextFieldHoursAndContextFieldLocationEmpty(clientId)){
            return "Por favor, me informe a hora e o local do evento"
        } else if (isClientContextEventDayFieldEmptyOrNull(clientId)) {
            return "Por favor, me informe o dia do evento"
        } else if (isClientContextEventHoursFieldEmptyOrNull(clientId)){
            return "Por favor, me informe a hora do evento"
        } else if (isClientContextEventLocationFieldNull(clientId)){
            return "Por favor, me informe o local do evento"
        } else{
            return ""
        }
    }

    private boolean isAllClientContextFieldsEmpty(String clientId){
        return (isClientContextEventDayFieldEmptyOrNull(clientId)) &&
                (isClientContextEventHoursFieldEmptyOrNull(clientId)) &&
                isClientContextEventLocationFieldNull(clientId)
    }

    private boolean isClientContextFieldDayAndContextFieldHoursEmpty(String clientId){
        return (isClientContextEventDayFieldEmptyOrNull(clientId)) &&
                (isClientContextEventHoursFieldEmptyOrNull(clientId))
    }

    private boolean isClientContextFieldDayAndContextFieldLocationEmpty(String clientId){
        return (isClientContextEventDayFieldEmptyOrNull(clientId)) &&
                isClientContextEventLocationFieldNull(clientId)
    }

    private boolean isClientContextFieldHoursAndContextFieldLocationEmpty(String clientId){
        return (isClientContextEventHoursFieldEmptyOrNull(clientId)) &&
                isClientContextEventLocationFieldNull(clientId)
    }

    private static long convertDateTimeToMilliseconds(String eventDay, String eventHours){
        String eventDateToFormat = eventDay.substring(0,11) << eventHours.substring(11,19)
        SimpleDateFormat dataModelToFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        Date EventDateInMilliseconds = dataModelToFormat.parse(eventDateToFormat.toString())
        return EventDateInMilliseconds.getTime()
    }
}
