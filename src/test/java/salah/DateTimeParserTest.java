package salah;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateTimeParserTest {

    @Test
    @DisplayName("Parses yyyy-MM-dd HHmm → LocalDateTime")
    void parsesDashWithHHmm() {
        LocalDateTime dt = DateTimeParser.parseFlexible("2019-12-15 1800");
        assertEquals(LocalDateTime.of(2019, 12, 15, 18, 0), dt);
    }

    @Test
    @DisplayName("Parses yyyy-MM-dd HH:mm → LocalDateTime")
    void parsesDashWithColon() {
        LocalDateTime dt = DateTimeParser.parseFlexible("2019-12-15 18:00");
        assertEquals(LocalDateTime.of(2019, 12, 15, 18, 0), dt);
    }

    @Test
    @DisplayName("Parses d/M/yyyy HHmm → LocalDateTime")
    void parsesSlashWithHHmm() {
        LocalDateTime dt = DateTimeParser.parseFlexible("2/12/2019 0900");
        assertEquals(LocalDateTime.of(2019, 12, 2, 9, 0), dt);
    }

    @Test
    @DisplayName("Parses date-only yyyy-MM-dd → atStartOfDay")
    void parsesDateOnlyIso() {
        LocalDateTime dt = DateTimeParser.parseFlexible("2019-12-02");
        assertEquals(LocalDateTime.of(2019, 12, 2, 0, 0), dt);
    }

    @Test
    @DisplayName("Rejects unrecognized formats")
    void rejectsBadFormat() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> DateTimeParser.parseFlexible("Sunday 6pm")
        );
        assertTrue(ex.getMessage().contains("Unrecognized"), "message should indicate unrecognized format");
    }
}
