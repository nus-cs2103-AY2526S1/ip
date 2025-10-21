package Task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void toString_notDone_formatsRange() {
        Event e = new Event("project meeting", false, LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 5));
        assertEquals("[E][ ] project meeting (from: Aug 1 2025 to: Aug 5 2025)", e.toString());
    }

    @Test
    void toString_done_formatsRange() {
        Event e = new Event("project meeting", true, LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 5));
        assertEquals("[E][X] project meeting (from: Aug 1 2025 to: Aug 5 2025)", e.toString());
    }

    @Test
    void toSaveString_matchesSpec() {
        Event e = new Event("project meeting", true, LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 5));
        // From your code: "E|<isDone>|<name>|<MMM d yyyy>|<MMM d yyyy>"
        assertEquals("E|true|project meeting|2025-08-01|2025-08-05", e.toSaveString());
    }
}
