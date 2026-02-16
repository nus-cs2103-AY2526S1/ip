package mon.task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class EventTest {
    @Test
    void testEventCreation() {
        Event event = new Event("Conference", "2023-12-25", "2023-12-26");
        assertEquals("Conference", event.getTaskName());
        assertFalse(event.getStatus());
        assertEquals(LocalDate.of(2023, 12, 25), event.getStartTime());
        assertEquals(LocalDate.of(2023, 12, 26), event.getEndTime());
    }

    @Test
    void testEventCreationWithStatus() {
        Event event = new Event("Conference", true, "2023-12-25", "2023-12-26");
        assertEquals("Conference", event.getTaskName());
        assertTrue(event.getStatus());
        assertEquals(LocalDate.of(2023, 12, 25), event.getStartTime());
        assertEquals(LocalDate.of(2023, 12, 26), event.getEndTime());
    }

    @Test
    void testEventCreationInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("Conference", "invalid-date", "2023-12-26");
        });
    }

    @Test
    void testToFileString() {
        Event event = new Event("Conference", "2023-12-25", "2023-12-26");
        assertEquals("E | 0 | Conference | Dec 25 2023 | Dec 26 2023", event.toFileString());
        
        event.setStatus(true);
        assertEquals("E | 1 | Conference | Dec 25 2023 | Dec 26 2023", event.toFileString());
    }

    @Test
    void testToString() {
        Event event = new Event("Conference", "2023-12-25", "2023-12-26");
        assertEquals("[E][ ] Conference (from: Dec 25 2023 to: Dec 26 2023)", event.toString());
        
        event.setStatus(true);
        assertEquals("[E][X] Conference (from: Dec 25 2023 to: Dec 26 2023)", event.toString());
    }

    @Test
    void testToEventTask() {
        String taskString = "E | 1 | Conference | Dec 25 2023 | Dec 26 2023";
        Event event = Event.toEventTask(taskString);
        assertEquals("Conference", event.getTaskName());
        assertTrue(event.getStatus());
        assertEquals(LocalDate.of(2023, 12, 25), event.getStartTime());
        assertEquals(LocalDate.of(2023, 12, 26), event.getEndTime());
    }

    @Test
    void testToEventTaskInvalidFormat() {
        String taskString = "E | 1 | Conference | Dec 25 2023"; // Missing end date
        assertThrows(IllegalArgumentException.class, () -> {
            Event.toEventTask(taskString);
        });
    }
}
