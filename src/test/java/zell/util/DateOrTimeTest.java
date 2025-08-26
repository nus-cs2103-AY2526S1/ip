package zell.util;

import zell.exception.ZellException;
import zell.util.DateOrTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateOrTimeTest {
    @Test
    public void constructor_validDate_success() throws ZellException {
        DateOrTime date = assertDoesNotThrow(() -> new DateOrTime("2025-08-09"));
        assertEquals("Aug 9 2025", date.toString());
    }

    @Test
    public void constructor_validDateTime_success() throws ZellException {
        DateOrTime dateTime = assertDoesNotThrow(() -> new DateOrTime("2025-08-10 07:30"));
        assertEquals("Aug 10 2025 07:30 am", dateTime.toString());
    }

    @Test
    public void constructor_invalidDate_exceptionThrown() {
        try {
            DateOrTime date = new DateOrTime("09-07-2025");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                    + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            DateOrTime date = new DateOrTime("09-2025-07");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                    + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            DateOrTime date = new DateOrTime("avava");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                    + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }
    }

    @Test
    public void constructor_invalidDateTime_exceptionThrown() {
        try {
            DateOrTime date = new DateOrTime("09-07-2025 19:30");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                    + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            DateOrTime date = new DateOrTime("09-2025-07 19:30");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                    + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            DateOrTime date = new DateOrTime("09-07-2025 19:30");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                    + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            DateOrTime date = new DateOrTime("2025-08-10 30:07");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                    + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            DateOrTime date = new DateOrTime("2025-08-10 30:a");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                    + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            DateOrTime date = new DateOrTime("avava");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                    + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }
    }

    @Test
    public void originalFormat_validDate_success() throws ZellException {
        String dateString = "2025-08-09";
        DateOrTime date = assertDoesNotThrow(() -> new DateOrTime(dateString));
        assertEquals(dateString, date.originalFormat());
    }

    @Test
    public void originalFormat_validDateTime_success() throws ZellException {
        String dateTimeString = "2025-08-10 07:30";
        DateOrTime dateTime = assertDoesNotThrow(() -> new DateOrTime(dateTimeString));
        assertEquals(dateTimeString, dateTime.originalFormat());
    }
}
