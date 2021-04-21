package shadow

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.Events
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class CalendarIntegration {
    private static final String APPLICATION_NAME = "Google Calendar Integration"
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance()
    private static final String TOKENS_DIRECTORY_PATH = "tokens"

    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY)
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json"
    private static final int WEEK_MILLISECONDS = 604800017

    List<String> getCalendarStatus(){
        List<String> calendarEvents = new ArrayList<>()
        List<Event> items = getCalendarEvents()

        if (items.isEmpty()) {
            return calendarEvents
        } else {
            log.info("Upcoming events")
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime()
                String eventText = event.getSummary().toString()+" "+start.toString()
                calendarEvents.add(eventText)
            }
        }
        return calendarEvents
    }

    private List<Event> getCalendarEvents() {
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
        Calendar service = new Calendar.Builder
                (HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT) as
                        HttpRequestInitializer).setApplicationName(APPLICATION_NAME)
                .build()

        DateTime now = new DateTime(System.currentTimeMillis())
        DateTime end = new DateTime(System.currentTimeMillis() + WEEK_MILLISECONDS)
        Events events = service.events().list("primary")
                .setTimeMin(now)
                .setTimeMax(end)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute()
        return events.getItems()

    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        InputStream credentials = CalendarIntegration.class.getResourceAsStream(CREDENTIALS_FILE_PATH)
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
