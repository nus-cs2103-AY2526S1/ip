package eve.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTimeUtilTest {

    @Test
    void parseDateTimeIsoDateOnlyBecomesMidnight() {
        Optional<LocalDateTime> dt = DateTimeUtil.parseDateTime("2019-12-02");
        assertTrue(dt.isPresent());
        assertEquals("2019-12-02T00:00", dt.get().toString());
        assertEquals("2019/12/2", DateTimeUtil.pretty(dt.get())); // uses your yyyy/M/d
    }

    @Test
    void parseDateTimeSpaceSeparatedMdyWithTimeColon() {
        Optional<LocalDateTime> dt = DateTimeUtil.parseDateTime("12 2 2019 12:00");
        assertTrue(dt.isPresent());
        assertEquals("2019-12-02T12:00", dt.get().toString());
        assertEquals("2019/12/2 12:00", DateTimeUtil.pretty(dt.get()));
    }

    @Test
    void parseDateTimeSlashDmyCompactTimeOk() {
        Optional<LocalDateTime> dt = DateTimeUtil.parseDateTime("2/12/2019 1800");
        assertTrue(dt.isPresent());
        assertEquals("2019-12-02T18:00", dt.get().toString());
        assertEquals("2019/12/2 18:00", DateTimeUtil.pretty(dt.get()));
    }

    @Test
    void parseDateTimeGarbageReturnsEmpty() {
        assertTrue(DateTimeUtil.parseDateTime("not a date").isEmpty());
    }
}
