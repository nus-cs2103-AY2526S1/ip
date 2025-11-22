import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.GenieweenieException;
import task.Events;

/**
 * Tests the Event class which represents tasks with a specific time.
 */
public class EventsTest {

    /**
     * Ensures that creating an Event with an empty description throws a GenieweenieException.
     */
    @Test
    public void constructorEmptyDescriptionThrowsGenieweenieException() {
        assertThrows(GenieweenieException.class, () -> new Events("", "2026-09-17 18:00",
                "2026-09-17 20:00"));
    }

    /**
     * Ensures that the toString method returns the correct formatted string.
     */
    @Test
    public void toStringValidEventReturnsCorrectString() throws GenieweenieException {
        Events event = new Events("Project meeting", "2026-09-17 18:00", "2026-09-17 20:00");
        assertEquals("[E][ ] Project meeting (from: 2026-09-17 18:00 to: 2026-09-17 20:00)", event.toString());
    }
}
