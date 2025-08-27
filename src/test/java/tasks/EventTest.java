package tasks;

import exception.RainyException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {

    @Test
    public void toFileFormat_notDone_correctFormat() throws RainyException {
        Event event = new Event("project meeting", "2025-10-01 1400", "2025-10-01 1600");
        assertEquals("E | 0 | project meeting | 2025-10-01T14:00 | 2025-10-01T16:00", event.toFileFormat());
    }

    @Test
    public void toFileFormat_done_correctFormat() throws RainyException {
        Event event = new Event("project meeting", "1/10/2025 1400", "1/10/2025 1600");
        event.markAsDone();
        assertEquals("E | 1 | project meeting | 2025-10-01T14:00 | 2025-10-01T16:00", event.toFileFormat());
    }

    @Test
    public void invalidDateFormat_throwsException() {
        assertThrows(RainyException.class, () -> new Event("project meeting", "01-10-2025", "01-10-2025"));
    }

    @Test
    public void constructorWithLocalDateTime_correctFormat() {
        LocalDateTime from = LocalDateTime.of(2025, 10, 1, 14, 0);
        LocalDateTime to = LocalDateTime.of(2025, 10, 1, 16, 0);
        Event event = new Event("project meeting", from, to);
        assertEquals("E | 0 | project meeting | 2025-10-01T14:00 | 2025-10-01T16:00", event.toFileFormat());
    }

    @Test
    public void testToString() throws RainyException {
        Event event = new Event("project meeting", "2025-08-30 2359", "2025-09-30 2359");
        assertEquals("[E][ ] project meeting (from: Aug 30 2025 11:59 pm to: Sept 30 2025 11:59 pm)"
                , event.toString());
    }

    @Test
    public void testMarkAsDone() throws RainyException {
        Event event = new Event("project meeting", "2025-08-30 2359", "2025-09-30 2359");
        event.markAsDone();
        assertEquals("[E][X] project meeting (from: Aug 30 2025 11:59 pm to: Sept 30 2025 11:59 pm)"
                , event.toString());
    }

}

