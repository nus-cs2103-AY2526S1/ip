package finixx.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the EventTask class.
 */
class EventTaskTest {
    @Test
    public void testToSaveFormatString_doneTask_withoutNotes() {
        EventTask t = new EventTask("project meeting", true, "", "10am", "12pm");
        assertEquals("E | 1 | project meeting | 10am | 12pm | ", t.toSaveFormatString());
    }

    @Test
    public void testToSaveFormatString_doneTask_withNotes() {
        EventTask t = new EventTask("project meeting", true, "office", "10am", "12pm");
        assertEquals("E | 1 | project meeting | 10am | 12pm | office", t.toSaveFormatString());
    }

    @Test
    public void testToSaveFormatString_undoneTask_withoutNotes() {
        EventTask t = new EventTask("project meeting", false, "", "10am", "12pm");
        assertEquals("E | 0 | project meeting | 10am | 12pm | ", t.toSaveFormatString());
    }

    @Test
    public void testToSaveFormatString_undoneTask_withNotes() {
        EventTask t = new EventTask("project meeting", false, "office", "10am", "12pm");
        assertEquals("E | 0 | project meeting | 10am | 12pm | office", t.toSaveFormatString());
    }

    @Test
    public void testToString_doneTask_withoutNotes() {
        EventTask t = new EventTask("project meeting", true, "", "10am", "12pm");
        assertEquals("[E][X] project meeting (from: 10am to: 12pm)", t.toString());
    }

    @Test
    public void testToString_doneTask_withNotes() {
        EventTask t = new EventTask("project meeting", true, "office", "10am", "12pm");
        assertEquals("[E][X] project meeting (from: 10am to: 12pm) <office>", t.toString());
    }

    @Test
    public void testToString_undoneTask_withoutNotes() {
        EventTask t = new EventTask("project meeting", false, "", "10am", "12pm");
        assertEquals("[E][ ] project meeting (from: 10am to: 12pm)", t.toString());
    }

    @Test
    public void testToString_undoneTask_withNotes() {
        EventTask t = new EventTask("project meeting", false, "office", "10am", "12pm");
        assertEquals("[E][ ] project meeting (from: 10am to: 12pm) <office>", t.toString());
    }

    @Test
    public void testSaveAndLoadConsistency() {
        EventTask original = new EventTask("team lunch", false, "cafe", "1pm", "2pm");
        String saveFormat = original.toSaveFormatString();
        Task loaded = Task.fromSaveFormat(saveFormat);
        assertTrue(loaded instanceof EventTask);
        assertEquals(original.toString(), loaded.toString());
    }

    @Test
    public void testGetStartAndEnd() {
        EventTask t = new EventTask("conference", false, "hall", "9am", "5pm");
        assertEquals("9am", t.getStart());
        assertEquals("5pm", t.getEnd());
    }
}
