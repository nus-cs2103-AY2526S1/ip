package beebong.util;

import java.time.LocalDateTime;

import beebong.exception.InvalidDateException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeUtilTest {

    // parseDateTime tests
    @Test
    public void parseDateTime_validDateTimeString_returnsCorrectLocalDateTime() {
        LocalDateTime result = DateTimeUtil.parseDateTime("21/8/2025 14:30");
        assertEquals(LocalDateTime.of(2025, 8, 21, 14, 30), result);
    }

    @Test
    public void parseDateTime_validDateOnlyString_returnsLocalDateTimeAtStartOfDay() {
        LocalDateTime result = DateTimeUtil.parseDateTime("21/8/2025");
        assertEquals(LocalDateTime.of(2025, 8, 21, 0, 0), result);
    }

    @Test
    public void parseDateTime_singleDigitDayMonth_returnsCorrectLocalDateTime() {
        LocalDateTime result = DateTimeUtil.parseDateTime("3/2/2025 14:30");
        assertEquals(LocalDateTime.of(2025, 2, 3, 14, 30), result);
    }

    @Test
    public void parseDateTime_leapYearDate_returnsCorrectLocalDateTime() {
        LocalDateTime result = DateTimeUtil.parseDateTime("29/2/2024");
        assertEquals(LocalDateTime.of(2024, 2, 29, 0, 0), result);
    }

    @Test
    public void parseDateTime_invalidString_throwsDateTimeParseException() {
        assertThrows(InvalidDateException.class, () -> DateTimeUtil.parseDateTime("date"));
    }

    @Test
    public void parseDateTime_invalidTime_returnsCorrectLocalDateTime() {
        assertThrows(InvalidDateException.class, () -> DateTimeUtil.parseDateTime("3/2/2025 14:3"));
    }

    // toString tests
    @Test
    public void toString_dateTimeWithTime_returnsFormattedStringWithTime() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 8, 21, 14, 30);
        String result = DateTimeUtil.toString(dateTime);
        assertEquals("21 Aug 2025 14:30", result);
    }

    @Test
    public void toString_dateTimeWithoutTime_returnsFormattedStringDateOnly() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 8, 21, 0, 0);
        String result = DateTimeUtil.toString(dateTime);
        assertEquals("21 Aug 2025", result);
    }

    @Test
    public void toString_singleDigitDayMonthTime_returnsFormattedStringWithTime() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 2, 3, 4, 5);
        String result = DateTimeUtil.toString(dateTime);
        assertEquals("03 Feb 2025 04:05", result);
    }

    // toSerializedString tests
    @Test
    public void toSerializedString_dateTimeWithTime_returnsSerializedStringWithTime() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 8, 21, 14, 30);
        String result = DateTimeUtil.toSerializedString(dateTime);
        assertEquals("21/08/2025 14:30", result);
    }

    @Test
    public void toSerializedString_dateTimeAtStartOfDay_returnsSerializedStringDateOnly() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 8, 21, 0, 0);
        String result = DateTimeUtil.toSerializedString(dateTime);
        assertEquals("21/08/2025", result);
    }

    @Test
    public void toSerializedString_singleDigitDayMonthTime_returnsCorrectly() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 2, 3, 4, 5);
        String result = DateTimeUtil.toSerializedString(dateTime);
        assertEquals("03/02/2025 04:05", result);
    }
}