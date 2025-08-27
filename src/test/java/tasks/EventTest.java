package tasks;

import exception.RainyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() throws RainyException {
        Event event = new Event("project meeting", "2025-08-30 2359", "2025-09-30 2359");
        assertEquals("[E][ ] project meeting (from: Aug 30 2025 11:59 pm to: Sept 30 2025 11:59 pm)", event.toString());
    }

    @Test
    public void testMarkAsDone() throws RainyException {
        Event event = new Event("project meeting", "2025-08-30 2359", "2025-09-30 2359");
        event.markAsDone();
        assertEquals("[E][X] project meeting (from: Aug 30 2025 11:59 pm to: Sept 30 2025 11:59 pm)", event.toString());
    }
}

