package goober;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import goober.helper.Parser;

public class ParserTest {

    @Nested
    @DisplayName("getFlagArg()")
    class GetFlagArgTests {

        @Test
        void getFlagArg_valueBetweenFlagAndNextSlash_returnsValue() {
            String line = "deadline return book /by 2025-09-01 1800 /loc library";
            String arg = Parser.getFlagArg(line, "/by");
            assertEquals("2025-09-01 1800", arg);
        }

        @Test
        void getFlagArg_flagAbsent_returnsEmptyString() {
            String line = "event party /from 2025-09-01 1400 /to 1600";
            assertEquals("", Parser.getFlagArg(line, "/by"));
        }

        @Test
        void getFlagArg_valueWithSurroundingWhitespace_trimsWhitespace() {
            String line = "todo do stuff /tag    urgent   /note blah";
            assertEquals("urgent", Parser.getFlagArg(line, "/tag"));
        }

        @Test
        void getFlagArg_flagAtEnd_returnsEmptyString() {
            String line = "deadline return book /by";
            assertEquals("", Parser.getFlagArg(line, "/by"));
        }
    }

    @Nested
    @DisplayName("parseDateTime() with built-in formats")
    class ParseDateTimeTests {
        @Test
        void parseDateTime_inputYearMonthDayHourMinute_parsesCorrectly() {
            LocalDateTime dt = Parser.parseDateTime("2025-09-01 1800");
            assertEquals(LocalDateTime.of(2025, 9, 1, 18, 0), dt);
        }

        @Test
        void parseDateTime_inputHourMinuteYearMonthDay_parsesCorrectly() {
            LocalDateTime dt = Parser.parseDateTime("1800 2025-09-01");
            assertEquals(LocalDateTime.of(2025, 9, 1, 18, 0), dt);
        }

        @Test
        void parseDateTime_unmatchedFormat_throwsDateTimeParseException() {
            assertThrows(DateTimeParseException.class, () -> Parser.parseDateTime("01/09/2025 18:00"));
        }
    }

    @Nested
    @DisplayName("dateTimeToString()")
    class DateTimeToStringTests {
        @Test
        void dateTimeToString_defaultPattern_formatsToYearMonthDayHourMinute() {
            LocalDateTime dt = LocalDateTime.of(2025, 9, 1, 18, 0);
            String expected = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm").format(dt) + "H";
            assertEquals(expected, Parser.dateTimeToString(dt));
        }
    }

    @Nested
    @DisplayName("buildDateTimeFormatterFromPatterns()")
    class FormatterFromListTests {
        @Test
        void buildDateTimeFormatterFromPatterns_multipleOptionalPatterns_parsesBoth() {
            var fmt = Parser.buildDateTimeFormatterFromPatterns(List.of("yyyy/MM/dd HHmm", "dd-MM-yyyy HHmm"));
            LocalDateTime a = LocalDateTime.parse("2025/09/01 1800", fmt);
            LocalDateTime b = LocalDateTime.parse("01-09-2025 1800", fmt);
            assertEquals(LocalDateTime.of(2025, 9, 1, 18, 0), a);
            assertEquals(LocalDateTime.of(2025, 9, 1, 18, 0), b);
        }
    }
}


