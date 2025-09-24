package khat.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void constructor_validInputs_setsFieldsCorrectly() {
        Event event = new Event("meeting", false, "29-08-2025 2200", "29-08-2025 2300");
        assertEquals("meeting", event.description);
        assertFalse(event.isDone);
        assertEquals("29-08-2025 2200", event.from);
        assertEquals("29-08-2025 2300", event.to);
    }

    @Test
    public void toSaveString_validEvent_returnsCorrectFormat() {
        Event event = new Event("meeting", false, "29-08-2025 2200", "29-08-2025 2300");
        assertEquals("E | 0 | meeting | 29-08-2025 2200 | 29-08-2025 2300", event.toSaveString());
    }

    @Test
    public void toString_doneEvent_returnsCorrectFormat() {
        Event event = new Event("meeting", true, "29-08-2025 2200", "29-08-2025 2300");
        assertEquals("[E][X] meeting (from: 29 Aug 25 1000pm to: 29 Aug 25 1100pm)", event.toString());
    }
}
