package mambo.parser;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeParserTest {

    @Test
    public void parseDateTimeTest1() {
        assertEquals("Dec 15 2003, 6:00 pm",
                DateTimeParser.formatDateTime(DateTimeParser.parseDateTime("15-12-2003 1800")));
    }

    @Test
    public void parseDateTimeTest2() {
        assertEquals("Dec 15 2003, 6:00 pm",
                DateTimeParser.formatDateTime(DateTimeParser
                        .parseDateTime("dEcEMber 15 2003 6pm")));
    }

    @Test
    public void parseDateTimeTest3() {
        assertEquals("Dec 15 2003, 6:00 pm",
                DateTimeParser.formatDateTime(DateTimeParser.parseDateTime("15/12/03 18:00")));
    }

    @Test
    public void parseDateTimeTest4() {
        assertEquals("Dec 15 2003, 6:00 pm",
                DateTimeParser.formatDateTime(DateTimeParser.parseDateTime("15 dEC 2003 6:00 pm")));
    }

    @Test
    public void parseDateTimeTest5() {
        assertEquals("Dec 15 2003, 11:59 pm",
                DateTimeParser.formatDateTime(DateTimeParser.parseDateTime("15/12/2003")));
    }

    @Test
    public void parseDateTimeTestError() {
        try {
            DateTimeParser.formatDateTime(DateTimeParser.parseDateTime("monday 12 october"));
        } catch (DateTimeParseException e) {
            assertEquals("no valid date or time", e.getMessage());
        }
    }
}
