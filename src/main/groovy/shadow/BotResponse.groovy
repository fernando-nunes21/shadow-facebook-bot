package shadow

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.CompileDynamic
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import shadow.dialogflow.DialogflowAPI
import shadow.dialogflow.output.DialogOutput

import java.text.SimpleDateFormat

@Service
@CompileDynamic
class BotResponse {

    private final static String EVENT_DAY = 'eventDay'
    private final static String EVENT_HOURS = 'eventHours'
    private final static String EVENT_LOCATION = 'eventLocation'
    private final static int START_DAY_SUBSTRING = 0
    private final static int FINAL_DAY_SUBSTRING = 11
    private final static int START_HOURS_SUBSTRING = 11
    private final static int FINAL_HOURS_SUBSTRING = 19

    private final CalendarAPI calendarAPI
    private final DialogflowAPI dialogflowAPI
    private final ObjectMapper objectMapper
    Map<String, Map<String, String>> context

    BotResponse(CalendarAPI calendarAPI, DialogflowAPI dialogflowAPI, ObjectMapper objectMapper) {
        this.calendarAPI = calendarAPI
        this.dialogflowAPI = dialogflowAPI
        this.context = [:]
        this.objectMapper = objectMapper
    }

    String getBotResponse(String clientId, String clientMessage) {
        ResponseEntity<String> dialogFlowResponse = dialogflowAPI.getDialogFlowResponse(clientMessage, clientId)
        getMessageResponse(objectMapper.readValue(dialogFlowResponse.body, DialogOutput), clientId)
    }

    private static long convertDateTimeToMilliseconds(String eventDay, String eventHours) {
        String eventDateToFormat = eventDay[START_DAY_SUBSTRING..FINAL_DAY_SUBSTRING] << eventHours[
                START_HOURS_SUBSTRING..FINAL_HOURS_SUBSTRING]
        SimpleDateFormat dataModelToFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        Date eventDateInMilliSeconds = dataModelToFormat.parse(eventDateToFormat.toString())
        eventDateInMilliSeconds.time
    }

    private String getMessageResponse(DialogOutput dialogOutput, String clientId) {
        if (dialogOutput.textOutput == 'callCalendarWeakPlan') {
            return this.calendarAPI.calendarWeekEvents
        } else if (dialogOutput.textOutput == 'callCalendarSetEvent') {
            String contextMessage
            if (isClientContextEmpty(clientId)) {
                this.context.put(clientId, startClientContext(dialogOutput))
            } else {
                updateClientContextFields(clientId, dialogOutput)
            }
            contextMessage = getMessageForEmptyContextField(clientId)
            if (isContextMessageNotEmpty(contextMessage)) {
                return contextMessage
            }
            long eventDataConverted = convertDateTimeToMilliseconds(
                    this.context.get(clientId).get(EVENT_DAY),
                    this.context.get(clientId).get(EVENT_HOURS))
            String eventLocation = this.context.get(clientId).get(EVENT_LOCATION)
            this.context.remove(clientId)
            return this.calendarAPI.getCalendarSetEventConfirmation(eventDataConverted, eventLocation)
        }
        this.context.remove(clientId)
        dialogOutput.textOutput
    }

    private boolean isClientContextEmpty(String clientId) {
        this.context.get(clientId) == null
    }

    private boolean isContextMessageNotEmpty(String contextMessage) {
        contextMessage != ''
    }

    private Map<String, String> startClientContext(DialogOutput dialogOutput) {
        Map<String, String> newContextContent = [:]
        newContextContent.put(EVENT_DAY, dialogOutput.eventDay)
        newContextContent.put(EVENT_HOURS, dialogOutput.eventHours)
        if (dialogOutput.eventLocation == null) {
            newContextContent.put(EVENT_LOCATION, null)
        } else {
            newContextContent.put(EVENT_LOCATION, dialogOutput.eventLocationData)
        }
        newContextContent
    }

    private void updateClientContextFields(String clientId, DialogOutput dialogOutput) {
        if (isClientContextEventDayFieldEmptyOrNull(clientId)) {
            this.context.get(clientId).put(EVENT_DAY, dialogOutput.eventDay)
        }
        if (isClientContextEventHoursFieldEmptyOrNull(clientId)) {
            this.context.get(clientId).put(EVENT_HOURS, dialogOutput.eventHours)
        }
        if (isClientContextEventLocationFieldNull(clientId)) {
            if (dialogOutput.eventLocation == null || dialogOutput.eventLocationData == '') {
                this.context.get(clientId).put(EVENT_LOCATION, null)
            } else {
                this.context.get(clientId).put(EVENT_LOCATION,
                        dialogOutput.eventLocationData)
            }
        }
    }

    private boolean isClientContextEventDayFieldEmptyOrNull(String clientId) {
        this.context.get(clientId).get(EVENT_DAY) == null || this.context.get(clientId).get(EVENT_DAY) == ''
    }

    private boolean isClientContextEventHoursFieldEmptyOrNull(String clientId) {
        this.context.get(clientId).get(EVENT_HOURS) == null || this.context.get(clientId).get(EVENT_HOURS) == ''
    }

    private boolean isClientContextEventLocationFieldNull(String clientId) {
        this.context.get(clientId).get(EVENT_LOCATION) == null
    }

    private String getMessageForEmptyContextField(String clientId) {
        if (isAllClientContextFieldsEmpty(clientId)) {
            return 'Por favor, informe o Dia, Hora e Local do evento'
        } else if (isClientContextFieldDayAndContextFieldHoursEmpty(clientId)) {
            return 'Por favor, me informe o dia e a hora do evento'
        } else if (isClientContextFieldDayAndContextFieldLocationEmpty(clientId)) {
            return 'Por favor, me informe o dia e o local do evento'
        } else if (isClientContextFieldHoursAndContextFieldLocationEmpty(clientId)) {
            return 'Por favor, me informe a hora e o local do evento'
        } else if (isClientContextEventDayFieldEmptyOrNull(clientId)) {
            return 'Por favor, me informe o dia do evento'
        } else if (isClientContextEventHoursFieldEmptyOrNull(clientId)) {
            return 'Por favor, me informe a hora do evento'
        }
        isClientContextEventLocationFieldNull(clientId) ? 'Por favor, me informe o local do evento' : ''
    }

    private boolean isAllClientContextFieldsEmpty(String clientId) {
        (isClientContextEventDayFieldEmptyOrNull(clientId)) &&
                (isClientContextEventHoursFieldEmptyOrNull(clientId)) &&
                isClientContextEventLocationFieldNull(clientId)
    }

    private boolean isClientContextFieldDayAndContextFieldHoursEmpty(String clientId) {
        (isClientContextEventDayFieldEmptyOrNull(clientId)) &&
                (isClientContextEventHoursFieldEmptyOrNull(clientId))
    }

    private boolean isClientContextFieldDayAndContextFieldLocationEmpty(String clientId) {
        (isClientContextEventDayFieldEmptyOrNull(clientId)) &&
                isClientContextEventLocationFieldNull(clientId)
    }

    private boolean isClientContextFieldHoursAndContextFieldLocationEmpty(String clientId) {
        (isClientContextEventHoursFieldEmptyOrNull(clientId)) &&
                isClientContextEventLocationFieldNull(clientId)
    }

}
