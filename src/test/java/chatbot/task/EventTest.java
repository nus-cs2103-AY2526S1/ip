package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testEventCreation() {
        Event event = new Event("Chill session", "now", "later");
        assertFalse(event.isDone());
        assertEquals("[E][ ] Chill session from: now to: later", event.toString());
    }

    @Test
    public void testMarkDone() {
        Event event = new Event("Chill session", "now", "later");
        event.markDone();
        assertTrue(event.isDone());
        assertEquals("[E][X] Chill session from: now to: later", event.toString());
    }

    @Test
    public void testUnmarkDone() {
        Event event = new Event("Chill session", "now", "later");
        event.markDone();
        event.unmarkDone();
        assertFalse(event.isDone());
        assertEquals("[E][ ] Chill session from: now to: later", event.toString());
    }
}
