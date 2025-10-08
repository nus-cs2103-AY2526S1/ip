package stella;

import java.time.DateTimeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains the tests for the TimeConverter class.
 */
public class TimeConverterTest {
    @Test
    public void convertDate_expectedInput_success() {
        assertEquals("12 March 2013",
                TimeConverter.convertDate("12-03-2013"));
        assertEquals("01 December 2025",
                TimeConverter.convertDate("01-12-2025"));
    }

    @Test
    public void convertDate_nonStandardTiming_rawFormat() {
        assertEquals("before Mon",
                TimeConverter.convertDate("before Mon"));
    }

    @Test
    public void convertDate_nonExistentDate_exceptionThrown() {
        try {
            assertEquals("Unknown Timing",
                    TimeConverter.convertDate("32-21-2013"));
        } catch (DateTimeException e) {
            assertEquals("Invalid value for MonthOfYear (valid values 1 - 12): 20",
                    e.getMessage());
        }
    }

    @Test
    public void convertDateWithTime_expectedInput_success() {
        assertEquals("12:30, 12 March 2013",
                TimeConverter.convertDateWithTime("12-03-2013-1230"));
        assertEquals("12:45, 01 December 2025",
                TimeConverter.convertDateWithTime("01-12-2025-1245"));
    }

    @Test
    public void convertDateWithTime_nonStandardTiming_rawFormat() {
            assertEquals("by Monday night",
                    TimeConverter.convertDateWithTime("by Monday night"));
    }

    @Test
    public void convertDateWithTime_nonExistentDate_exceptionThrown() {
        try {
            assertEquals("Unknown Timing",
                    TimeConverter.convertDateWithTime("11-12-2013-2500"));
        } catch (DateTimeException e) {
            assertEquals("Invalid value for HourOfDay (valid values 0 - 23): 25",
                    e.getMessage());
        }
    }
}
