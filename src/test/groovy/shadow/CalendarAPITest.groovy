package shadow

import spock.lang.Specification

class CalendarAPITest extends Specification{

    CalendarAPI calendarAPI = new CalendarAPI()

    def "get calendar week events" () {
        when:
        String result = calendarAPI.getCalendarWeekEvents()

        then:
        assert result != ""
    }
}
