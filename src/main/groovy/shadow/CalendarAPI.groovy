package shadow

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.DateTime
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventDateTime
import com.google.api.services.calendar.model.Events
import org.springframework.stereotype.Service

@Service
class CalendarAPI {

    private static final String APPLICATION_NAME = "Google Calendar Integration"
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance()
    private static final String TOKENS_DIRECTORY_PATH = "tokens"
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR)
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json"
    private static final Integer TWO_HOURS_IN_MILLISECONDS = 7200000
    private static final Integer WEEK_IN_MILLISECONDS = 604800017
    private static final Calendar SERVICE_AUTHORIZATION = buildNewAuthorizedApiClientService()

    //TODO SideEffect problem - Voltar depois e pensar em uma solução que também evite o if se possível
    static String getCalendarWeekEvents(){
        String calendarMessage = "-- Eventos da Semana --\n"
        List<Event> items = getCalendarEvents(System.currentTimeMillis(),WEEK_IN_MILLISECONDS)
        if (items.isEmpty()) {
            return "Não há eventos na minha agenda essa semana!"
            } else {
                for (Event event : items) {
                    DateTime eventTime = event.getStart().getDateTime()
                    String nameAndTimeEvent = " -- " << event.getSummary().toString() << " -- " << eventTime.toString()
                    calendarMessage = calendarMessage << nameAndTimeEvent << "\n"
            }
            return calendarMessage
        }
    }

    static String getCalendarSetEventConfirmation(long eventDate, String location){
        return setEvent(eventDate,location)
    }

    private static String setEvent(long eventDate, String location){
        if (!validateDateEventAlreadySet(eventDate)){
            return "Já existe um evento registrado para essa data"
        } else{
            String calendarId = "primary"
            Event eventToSetOnCalendar = new Event()
                    .setSummary(location)
                    .setLocation(location)
            DateTime startDateTime = new DateTime(eventDate)
            EventDateTime startEventTime = new EventDateTime()
                    .setDateTime(startDateTime)
                    .setTimeZone("America/Sao_Paulo")
            eventToSetOnCalendar.setStart(startEventTime)
            DateTime endDateTime = new DateTime(eventDate+TWO_HOURS_IN_MILLISECONDS)
            EventDateTime endEventTime = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone("America/Sao_Paulo")
            eventToSetOnCalendar.setEnd(endEventTime)
            Event.Reminders reminders = new Event.Reminders()
                    .setUseDefault(false)
            eventToSetOnCalendar.setReminders(reminders)
            SERVICE_AUTHORIZATION.events().insert(calendarId, eventToSetOnCalendar).execute()
            return "Compromisso marcado na agenda com sucesso!"
        }
    }

    private static boolean validateDateEventAlreadySet(long eventDate){
        List<Event> items = getCalendarEvents(eventDate,eventDate+TWO_HOURS_IN_MILLISECONDS)
        return items.isEmpty()
    }

    private static List<Event> getCalendarEvents(long currentDayGet, long weekMilliseconds) {
        DateTime timeNowInMilliseconds = new DateTime(currentDayGet)
        DateTime timeEndInMilliseconds = new DateTime(currentDayGet + weekMilliseconds)
        Events eventsFromCalendar = SERVICE_AUTHORIZATION.events().list("primary")
                .setTimeMin(timeNowInMilliseconds)
                .setTimeMax(timeEndInMilliseconds)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute()
        return eventsFromCalendar.getItems()
    }

    private static Calendar buildNewAuthorizedApiClientService(){
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
        Calendar service = new Calendar.Builder
                (HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT) as
                        HttpRequestInitializer).setApplicationName(APPLICATION_NAME)
                .build()
        return service
    }

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream credentials = CalendarAPI.class.getResourceAsStream(CREDENTIALS_FILE_PATH)
        if (credentials == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH)
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(credentials))
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("online")
                .build()
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build()
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }
}
