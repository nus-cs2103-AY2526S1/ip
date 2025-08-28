package evansbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testConstructorWithDescription() {
        Event event = new Event("read book", "6 pm", "8 pm");
        assertEquals("[E][ ] read book (From: 6 pm to: 8 pm)", event.toString());
        assertFalse(event.isDone(), "Task should not be done by default");
    }

    @Test
    public void testConstructorWithDescriptionAndStatus() {
        Event event = new Event("read book", true, "6 pm", "8 pm");
        assertEquals("[E][X] read book (From: 6 pm to: 8 pm)", event.toString());
        assertTrue(event.isDone(), "Task should be marked as done");
    }

}