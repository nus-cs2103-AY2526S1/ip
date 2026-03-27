package jaiden.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void isBetweenTest() {
        assertTrue(new Event("test", LocalDate.parse("2025-08-22"),
                LocalDate.parse("2025-08-22")).isBetween(LocalDate.parse("2025-08-22")));
    }

    @Test
    public void saveTest() {
        assertEquals("E | 0 | test | 2025-08-22 | 2025-08-22",
                new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")).save());
    }

    @Test
    public void toStringTest() {
        assertEquals("[E][ ] test (from: Aug 22 2025 to: Aug 22 2025)",
                new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")).toString());
    }
}
