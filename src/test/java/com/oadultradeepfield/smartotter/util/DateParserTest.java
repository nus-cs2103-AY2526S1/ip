package com.oadultradeepfield.smartotter.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class DateParserTest {
    private static void assertDateTime(LocalDateTime actual, int... parts) {
        int i = 0;
        assertEquals(parts[i++], actual.getYear());
        assertEquals(parts[i++], actual.getMonthValue());
        assertEquals(parts[i++], actual.getDayOfMonth());
        assertEquals(parts[i++], actual.getHour());
        assertEquals(parts[i], actual.getMinute());
    }

    @Test
    void parse_shouldReturnDateTime_whenInputHasTime() {
        Optional<LocalDateTime> result = DateParser.parse("2/12/2025 1830");
        assertTrue(result.isPresent());
        assertDateTime(result.get(), 2025, 2, 12, 18, 30);
    }

    @Test
    void parse_shouldReturnStartOfDay_whenInputHasOnlyDate() {
        Optional<LocalDateTime> result = DateParser.parse("2/12/2025");
        assertTrue(result.isPresent());
        assertDateTime(result.get(), 2025, 2, 12, 0, 0);
    }

    @Test
    void parse_shouldReturnEmpty_whenInputIsInvalid() {
        Optional<LocalDateTime> result = DateParser.parse("invalid date");
        assertTrue(result.isEmpty());
    }

    @Test
    void format_shouldReturnReadableDate_whenTimeIsZero() {
        LocalDateTime dt = LocalDateTime.of(2025, 2, 12, 0, 0);
        String formatted = DateParser.format(dt);
        assertEquals("Feb 12, 2025", formatted);
    }

    @Test
    void format_shouldReturnReadableDateTime_whenTimeIsNonZero() {
        LocalDateTime dt = LocalDateTime.of(2025, 2, 12, 18, 30);
        String formatted = DateParser.format(dt);
        assertEquals("Feb 12, 2025 18:30", formatted);
    }

    @Test
    void formatForLine_shouldReturnInputStyleDate_whenTimeIsZero() {
        LocalDateTime dt = LocalDateTime.of(2025, 2, 12, 0, 0);
        String formatted = DateParser.formatForLine(dt);
        assertEquals("2/12/2025", formatted);
    }

    @Test
    void formatForLine_shouldReturnInputStyleDateTime_whenTimeIsNonZero() {
        LocalDateTime dt = LocalDateTime.of(2025, 2, 12, 18, 30);
        String formatted = DateParser.formatForLine(dt);
        assertEquals("2/12/2025 1830", formatted);
    }
}
