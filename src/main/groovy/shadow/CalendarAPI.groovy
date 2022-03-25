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
import groovy.transform.CompileDynamic
import org.springframework.stereotype.Service

@Service
@CompileDynamic
class CalendarAPI {

    private static final String TIME_SEPARATOR = '--'
    private static final String CALENDAR_ID = 'primary'
    private static final String CALENDAR_ZONE = 'America/Sao_Paulo'
    private static final String CALENDAR_START_TIME = 'startTime'
    private static final String ACESS_TYPE_ONLINE = 'online'
    private static final String USER_ID = 'user'
    private static final Integer WEEK_IN_MILLISECONDS = 604800017
    private static final Integer TWO_HOURS_IN_MILLISECONDS = 7200000
    private static final Integer LOCAL_SERVER_PORT = 8888
    private final String applicationName = 'Google Calendar Integration'
    private final GsonFactory jsonFactory = GsonFactory.defaultInstance
    private final String tokensDirectoryPath = 'tokens'
    private final List<String> scopes = Collections.singletonList(CalendarScopes.CALENDAR)
    private final String credentialsFilePath = '/credentials.json'
    private final Calendar serviceAuthorization = generateNewAuthorizedApiClientService()

    String getCalendarWeekEvents() {
        String calendarMessage = '-- Eventos da Semana --\n'
        List<Event> items = getCalendarEvents(System.currentTimeMillis(), WEEK_IN_MILLISECONDS)
        if (items.empty) {
            return 'Não há eventos na minha agenda essa semana!'
        }
        for (Event event : items) {
            DateTime eventTime = event.getStart().getDateTime()
            String nameAndTimeEvent =
                    TIME_SEPARATOR << event.getSummary().toString() << TIME_SEPARATOR << eventTime.toString()
            calendarMessage = calendarMessage << nameAndTimeEvent << '\n'
        }
        calendarMessage
    }

    String getCalendarSetEventConfirmation(long eventDate, String location) {
        setEvent(eventDate, location)
    }

    private String setEvent(long eventDate, String location) {
        if (!validateDateEventAlreadySet(eventDate)) {
            return 'Já existe um evento registrado para essa data'
        }
        String calendarId = CALENDAR_ID
        Event eventToSetOnCalendar = new Event()
                    .setSummary(location)
                    .setLocation(location)
        DateTime startDateTime = new DateTime(eventDate)
        EventDateTime startEventTime = new EventDateTime()
                    .setDateTime(startDateTime)
                    .setTimeZone(CALENDAR_ZONE)
        eventToSetOnCalendar.setStart(startEventTime)
        DateTime endDateTime = new DateTime(eventDate + TWO_HOURS_IN_MILLISECONDS)
        EventDateTime endEventTime = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone(CALENDAR_ZONE)
        eventToSetOnCalendar.setEnd(endEventTime)
        Event.Reminders reminders = new Event.Reminders().setUseDefault(false)
        eventToSetOnCalendar.setReminders(reminders)
        serviceAuthorization.events().insert(calendarId, eventToSetOnCalendar).execute()
        'Compromisso marcado na agenda com sucesso!'
    }

    private boolean validateDateEventAlreadySet(long eventDate) {
        List<Event> items = getCalendarEvents(eventDate, eventDate + TWO_HOURS_IN_MILLISECONDS)
        items.isEmpty()
    }

    private List<Event> getCalendarEvents(long currentDayGet, long weekMilliseconds) {
        DateTime timeNowInMilliseconds = new DateTime(currentDayGet)
        DateTime timeEndInMilliseconds = new DateTime(currentDayGet + weekMilliseconds)
        Events eventsFromCalendar = serviceAuthorization.events().list(CALENDAR_ID)
                .setTimeMin(timeNowInMilliseconds)
                .setTimeMax(timeEndInMilliseconds)
                .setOrderBy(CALENDAR_START_TIME)
                .setSingleEvents(true)
                .execute()
        eventsFromCalendar.getItems()
    }

    private Calendar generateNewAuthorizedApiClientService() {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        Calendar service = new Calendar.Builder
                (httpTransport, jsonFactory, getCredentials(httpTransport) as HttpRequestInitializer)
                .setApplicationName(applicationName)
                .build()
        service
    }

    private Credential getCredentials(final NetHttpTransport httpTransport) throws IOException {
        InputStream credentials = CalendarAPI.getResourceAsStream(credentialsFilePath)
        if (credentials == null) {
            throw new FileNotFoundException('Resource not found: ' + credentialsFilePath)
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(credentials))
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new File(tokensDirectoryPath)))
                .setAccessType(ACESS_TYPE_ONLINE)
                .build()
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(LOCAL_SERVER_PORT).build()
        new AuthorizationCodeInstalledApp(flow, receiver).authorize(USER_ID)
    }

}
