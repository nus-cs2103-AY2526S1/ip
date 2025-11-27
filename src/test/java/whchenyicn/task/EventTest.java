package whchenyicn.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {

    @Test
    void toSaveTest() {
        Event e = new Event("project meeting", "2025-09-01 10:00", "2025-09-01 12:00");
        assertEquals("E | 0 | project meeting | 2025-09-01 10:00 | 2025-09-01 12:00", e.toSave());
    }

    @Test
    void toStringTest() {
        Event e = new Event("team sync", "9am", "10am");
        String s = e.toString();
        assertTrue(s.startsWith("[E]"));
        assertTrue(s.contains("team sync"));
        assertTrue(s.contains("(from: 9am to: 10am)"));
    }
}
