package reboot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void toString_withDateOnly_success() {
        Event e = new Event("project meeting", false,
                LocalDate.of(2025, 12, 2),
                LocalDate.of(2025, 12, 3));
        String expected = "[E][ ] project meeting (from: 02 12 2025 to: 03 12 2025)";
        assertEquals(expected, e.toString());
    }

    @Test
    public void toString_withDateTime_success() {
        Event e = new Event("project meeting", false,
                LocalDateTime.of(2025, 12, 2, 8, 0),
                LocalDateTime.of(2025, 12, 2, 10, 0));
        String expected = "[E][ ] project meeting (from: 02 12 2025 0800 to: 02 12 2025 1000)";
        assertEquals(expected, e.toString());
    }

    @Test
    public void toFileString_withDateOnly_success() {
        Event e = new Event("project meeting", false,
                LocalDate.of(2025, 12, 2),
                LocalDate.of(2025, 12, 3));
        String expected = "E | 0 | project meeting | 02 12 2025 | 03 12 2025";
        assertEquals(expected, e.toFileString());
    }

    @Test
    public void toFileString_withDateTime_success() {
        Event e = new Event("project meeting", false,
                LocalDateTime.of(2025, 12, 2, 8, 0),
                LocalDateTime.of(2025, 12, 2, 10, 0));
        String expected = "E | 0 | project meeting | 02 12 2025 0800 | 02 12 2025 1000";
        assertEquals(expected, e.toFileString());
    }
}
