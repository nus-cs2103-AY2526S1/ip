package tom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testEvent() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse("2025-08-29 1930", inputFormatter);
        LocalDateTime to = LocalDateTime.parse("2025-08-29 2200", inputFormatter);
        Event e = new Event("attend concert", from, to);
        assertEquals("E | 0 | attend concert | Aug 29 2025, 07:30 PM | Aug 29 2025, 10:00 PM", e.saveDesc());
    }
}
