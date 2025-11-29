package kleebot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void testGetTypeFromTo() {
        Event event = new Event("Meeting", "2pm", "3pm");
        assertEquals("E", event.getType());
        assertEquals("2pm", event.getFrom());
        assertEquals("3pm", event.getTo());
    }

    @Test
    void testToString() {
        Event event = new Event("Meeting", "12-06-2025", "15/06/2025");
        assertEquals("[E][ ]Meeting (from: 12-06-2025 to: 15/06/2025)", event.toString());
    }
}
