package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.RomidasException;

/*
 * AI was able to assist in generating test cases for Event using by submitting my previously completed 
 * TodoTaskTest.java file, information about the deadline task like the different methods and the fields as reference
 */

public class EventTest {
    private Event event;
    private Event sameEvent;
    private Event differentFromEvent;
    private Event differentToEvent;

    @BeforeEach
    public void setUp() {
        event = new Event("Team meeting", "2pm", "4pm");
        sameEvent = new Event("Team meeting", "2pm", "4pm");
        differentFromEvent = new Event("Team meeting", "3pm", "4pm");
        differentToEvent = new Event("Team meeting", "2pm", "5pm");
    }

    @Test
    public void testEventCreation() {
        assertEquals("Team meeting", event.getDescription());
        assertFalse(event.isDone());
        assertEquals("2pm", event.getFrom());
        assertEquals("4pm", event.getTo());
        assertEquals("[E]", event.getStatus());
        assertEquals("[ ]", event.getStatusIcon());
    }

    @Test
    public void testGetFromAndTo() {
        assertEquals("2pm", event.getFrom());
        assertEquals("4pm", event.getTo());
    }

    @Test
    public void testToText_NotDone() {
        String expected = "E | 0 | Team meeting (from: 2pm to: 4pm) | 2pm-4pm";
        assertEquals(expected, event.toText());
    }

    @Test
    public void testToText_Done() {
        event.setIsDone(true);
        String expected = "E | 1 | Team meeting (from: 2pm to: 4pm) | 2pm-4pm";
        assertEquals(expected, event.toText());
    }

    @Test
    public void testToString_NotDone() {
        String expected = "[E][ ] Team meeting (from: 2pm to: 4pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToString_Done() {
        event.setIsDone(true);
        String expected = "[E][X] Team meeting (from: 2pm to: 4pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testSetIsDone() {
        assertFalse(event.isDone());
        event.setIsDone(true);
        assertTrue(event.isDone());
        assertEquals("[X]", event.getStatusIcon());
    }

    @Test
    public void testToTask_ValidInput_NotDone() throws RomidasException {
        String[] parts = {"E", "0", "Conference call (from: 9am to: 10am)", "9am-10am"};
        Task task = Event.toTask(parts);
        
        assertEquals("Conference call", task.getDescription());
        assertFalse(task.isDone());
        assertEquals("[E]", task.getStatus());
        assertTrue(task instanceof Event);
        assertEquals("9am", ((Event) task).getFrom());
        assertEquals("10am", ((Event) task).getTo());
    }

    @Test
    public void testToTask_ValidInput_Done() throws RomidasException {
        String[] parts = {"E", "1", "Workshop (from: 1pm to: 3pm)", "1pm-3pm"};
        Task task = Event.toTask(parts);
        
        assertEquals("Workshop", task.getDescription());
        assertTrue(task.isDone());
        assertEquals("[E]", task.getStatus());
        assertTrue(task instanceof Event);
        assertEquals("1pm", ((Event) task).getFrom());
        assertEquals("3pm", ((Event) task).getTo());
    }

    @Test
    public void testToTask_InvalidNumberOfArguments_TooFew() {
        String[] parts = {"E", "0", "Event"};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            Event.toTask(parts);
        });
        
        assertEquals("Invalid number of arguments. Expected 4 but got 3", exception.getMessage());
    }

    @Test
    public void testToTask_InvalidNumberOfArguments_TooMany() {
        String[] parts = {"E", "0", "Event", "9am-10am", "Extra"};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            Event.toTask(parts);
        });
        
        assertEquals("Invalid number of arguments. Expected 4 but got 5", exception.getMessage());
    }

    @Test
    public void testToTask_InvalidTimeFormat_MissingDash() {
        String[] parts = {"E", "0", "Event", "9am10am"};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            Event.toTask(parts);
        });
        
        assertEquals("Invalid event format. Expected 'from-to' but got: 9am10am", exception.getMessage());
    }

    @Test
    public void testToTask_InvalidTimeFormat_EmptyFromTime() {
        String[] parts = {"E", "0", "Event", "-10am"};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            Event.toTask(parts);
        });
        
        assertEquals("Invalid event format. Expected 'from-to' but got: -10am", exception.getMessage());
    }

    @Test
    public void testToTask_InvalidTimeFormat_EmptyToTime() {
        String[] parts = {"E", "0", "Event", "9am-"};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            Event.toTask(parts);
        });
        
        assertEquals("Invalid event format. Expected 'from-to' but got: 9am-", exception.getMessage());
    }

    @Test
    public void testToTask_InvalidTimeFormat_BlankFromTime() {
        String[] parts = {"E", "0", "Event", " -10am"};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            Event.toTask(parts);
        });
        
        assertEquals("Invalid event format. Expected 'from-to' but got:  -10am", exception.getMessage());
    }

    @Test
    public void testToTask_InvalidTimeFormat_BlankToTime() {
        String[] parts = {"E", "0", "Event", "9am- "};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            Event.toTask(parts);
        });
        
        assertEquals("Invalid event format. Expected 'from-to' but got: 9am- ", exception.getMessage());
    }

    @Test
    public void testEquals_SameDescriptionAndTimes() {
        assertTrue(event.equals(sameEvent));
        assertTrue(sameEvent.equals(event));
    }

    @Test
    public void testEquals_DifferentFromTime() {
        assertFalse(event.equals(differentFromEvent));
        assertFalse(differentFromEvent.equals(event));
    }

    @Test
    public void testEquals_DifferentToTime() {
        assertFalse(event.equals(differentToEvent));
        assertFalse(differentToEvent.equals(event));
    }

    @Test
    public void testEquals_DifferentType() {
        TodoTask todoTask = new TodoTask("Team meeting");
        assertFalse(event.equals(todoTask));
        assertFalse(todoTask.equals(event));
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(event.equals(event));
    }

    @Test
    public void testEquals_NullObject() {
        assertFalse(event.equals(null));
    }

    @Test
    public void testEquals_DifferentCompletionStatus() {
        event.setIsDone(true);
        sameEvent.setIsDone(false);
        // Completion status should not affect equality
        assertTrue(event.equals(sameEvent));
    }

    @Test
    public void testHashCode_SameDescriptionAndTimes() {
        assertEquals(event.hashCode(), sameEvent.hashCode());
    }

    @Test
    public void testHashCode_DifferentFromTime() {
        assertNotEquals(event.hashCode(), differentFromEvent.hashCode());
    }

    @Test
    public void testHashCode_DifferentToTime() {
        assertNotEquals(event.hashCode(), differentToEvent.hashCode());
    }

    @Test
    public void testToTask_EmptyDescription() throws RomidasException {
        String[] parts = {"E", "0", " (from: 9am to: 10am)", "9am-10am"};
        Task task = Event.toTask(parts);
        
        assertEquals("", task.getDescription());
        assertFalse(task.isDone());
        assertEquals("9am", ((Event) task).getFrom());
        assertEquals("10am", ((Event) task).getTo());
    }

    @Test
    public void testToTask_DescriptionWithoutTimeFormatting() throws RomidasException {
        String[] parts = {"E", "1", "Simple event", "9am-10am"};
        Task task = Event.toTask(parts);
        
        assertEquals("Simple event", task.getDescription());
        assertTrue(task.isDone());
        assertEquals("9am", ((Event) task).getFrom());
        assertEquals("10am", ((Event) task).getTo());
    }


    @Test
    public void testEquals_NullFromTime() {
        Event eventWithNullFrom = new Event("Test", null, "4pm");
        Event eventWithNullFrom2 = new Event("Test", null, "4pm");
        Event eventWithFrom = new Event("Test", "2pm", "4pm");
        
        assertTrue(eventWithNullFrom.equals(eventWithNullFrom2));
        assertFalse(eventWithNullFrom.equals(eventWithFrom));
        assertFalse(eventWithFrom.equals(eventWithNullFrom));
    }

    @Test
    public void testEquals_NullToTime() {
        Event eventWithNullTo = new Event("Test", "2pm", null);
        Event eventWithNullTo2 = new Event("Test", "2pm", null);
        Event eventWithTo = new Event("Test", "2pm", "4pm");
        
        assertTrue(eventWithNullTo.equals(eventWithNullTo2));
        assertFalse(eventWithNullTo.equals(eventWithTo));
        assertFalse(eventWithTo.equals(eventWithNullTo));
    }
}

