package hermione.utils;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import hermione.exceptions.DateUtilsException;

public class DateUtilsTest {

    @Test
    public void parseDateString_invalidDateString_throwsException() {
        String invalidDateString = "2021-01-01 12:00:00";
        Assertions.assertThrows(
                DateUtilsException.class, () -> DateUtils.parseDateString(invalidDateString)
        );
    }

    @Test
    public void parseDateString_validDateString_returnsLocalDateTime() {
        String validDateString = "1/1/2021 1200";
        LocalDateTime result = DateUtils.parseDateString(validDateString);
        LocalDateTime expected = LocalDateTime.of(2021, 1, 1, 12, 0);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void formatDate_validLocalDateTime_returnsString() {
        LocalDateTime date = LocalDateTime.of(2021, 1, 1, 12, 0);
        String result = DateUtils.formatDate(date);
        String expected = "Jan 01 2021 12:00";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void undoFormatDate_invalidDateString_throwsException() {
        String invalidDateString = "2021-01-01 12:00:00";
        Assertions.assertThrows(
                DateUtilsException.class, () -> DateUtils.undoFormatDate(invalidDateString)
        );
    }

    @Test
    public void undoFormatDate_validDateString_returnsLocalDateTime() {
        String dateString = "Jan 01 2021 12:00";
        LocalDateTime result = DateUtils.undoFormatDate(dateString);
        LocalDateTime expected = LocalDateTime.of(2021, 1, 1, 12, 0);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void undoFormatDate_invalidDateFormat_throwsException() {
        String dateString = "01/01/2021 1200";
        Assertions.assertThrows(
                DateUtilsException.class, () -> DateUtils.undoFormatDate(dateString)
        );

    }

}
