package merlie.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import merlie.exception.MerlieException;

class ParsedDateTest {

    @Test
    void parse_validIsoDateWithTime_success() throws MerlieException {
        ParsedDate pd = ParsedDate.parseDate("2025-09-19 1530");
        assertEquals(LocalDateTime.of(2025, 9, 19, 15, 30), pd.getDate());
        assertEquals(true, pd.getHasTime());
    }

    @Test
    void parse_validIsoDateOnly_success() throws MerlieException {
        ParsedDate pd = ParsedDate.parseDate("2025-09-19");
        assertEquals(LocalDateTime.of(2025, 9, 19, 0, 0), pd.getDate());
        assertEquals(false, pd.getHasTime());
    }

    @Test
    void parse_validDashDateWithTime_success() throws MerlieException {
        ParsedDate pd = ParsedDate.parseDate("19-9-2025 0830");
        assertEquals(LocalDateTime.of(2025, 9, 19, 8, 30), pd.getDate());
        assertEquals(true, pd.getHasTime());
    }

    @Test
    void parse_validSlashDateOnly_success() throws MerlieException {
        ParsedDate pd = ParsedDate.parseDate("19/9/2025");
        assertEquals(LocalDateTime.of(2025, 9, 19, 0, 0), pd.getDate());
        assertEquals(false, pd.getHasTime());
    }

    @Test
    void parse_invalidFormat_throwsException() {
        assertThrows(MerlieException.class, () -> ParsedDate.parseDate("September 19, 2025"));
    }

    @Test
    void parse_invalidTime_throwsException() {
        assertThrows(MerlieException.class, () -> ParsedDate.parseDate("2025-09-19 9999"));
    }
}

