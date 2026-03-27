package sunday;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeHelperTest {
    @Test
    public void parseDate_acceptsIsoAndSlash() {
        assertEquals(LocalDate.of(2025, 8, 25),
                DateTimeHelper.parseDate("2025-08-25"));
        assertEquals(LocalDate.of(2025, 8, 25),
                DateTimeHelper.parseDate("25/8/2025"));
    }

    @Test
    public void parseDateTime_acceptsNoTrimAndColon() {
        assertEquals(LocalDateTime.of(2025, 8, 25, 18, 0),
                DateTimeHelper.parseDateTime("2025-08-25 1800"));
        assertEquals(LocalDateTime.of(2025, 8, 25, 18, 0),
                DateTimeHelper.parseDateTime("2025-08-25 18:00"));
        assertEquals(LocalDateTime.of(2025, 8, 25, 18, 0),
                DateTimeHelper.parseDateTime("  2025-08-25 1800  "));
    }
}
