package talkgpt.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testDeserialize() {
        String task = "E|false|project meeting|2025-12-01T14:00|2025-12-01T16:00|work";
        Event expected = new Event("project meeting", "2025-12-01T14:00", "2025-12-01T16:00", false, "work");
        assertEquals(expected, Event.deserialize(task.split("\\s*\\|\\s*")));
    }

    @Test
    public void testSerialize() {
        Event task = new Event("project meeting", "2025-12-01T14:00", "2025-12-01T16:00", false, "work");
        String expected = "E|false|project meeting|2025-12-01T14:00|2025-12-01T16:00|work";
        assertEquals(expected, task.serialize());
    }

    @Test
    public void testgetTag() {
        Event task = new Event("project meeting", "2025-12-01T14:00", "2025-12-01T16:00", false, "work");
        String expected = "work";
        assertEquals(expected, task.getTag());
    }

    @Test
    public void testgetDescription() {
        Event task = new Event("project meeting", "2025-12-01T14:00", "2025-12-01T16:00", false, "work");
        String expected = "project meeting";
        assertEquals(expected, task.getDescription());
    }

    @Test
    public void testgetStatus() {
        Event task = new Event("project meeting", "2025-12-01T14:00", "2025-12-01T16:00", false, "work");
        boolean expected = false;
        assertEquals(expected, task.getStatus());
    }

    @Test
    public void testMark() {
        Event task = new Event("project meeting", "2025-12-01T14:00", "2025-12-01T16:00", false, "work");
        task.mark();
        boolean expected = true;
        assertEquals(expected, task.getStatus());
    }

    @Test
    public void testUnmark() {
        Event task = new Event("project meeting", "2025-12-01T14:00", "2025-12-01T16:00", true, "work");
        task.unmark();
        boolean expected = false;
        assertEquals(expected, task.getStatus());
    }
}
