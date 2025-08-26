package rafayel.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void validEvent_success() {
        // Valid
        String description = "return book";
        LocalDateTime startDate = LocalDateTime.of(2024, 9, 12, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2025, 3, 14, 4, 13);
        Event event = new Event(description, startDate, endDate);

        // check contents
        assertEquals(description, event.getDescription());
        assertFalse(event.isDone);
        assertEquals(startDate, event.startDate);
        assertEquals(endDate, event.endDate);
    }

    @Test
    public void testSaveTaskName_Undone_returnsCorrectFormat() {
        // check SaveTaskName() and markAsUndone()
        String description = "Return book";
        LocalDateTime startDate = LocalDateTime.of(2024, 9, 12, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2025, 3, 14, 4, 13);

        Event event = new Event(description, startDate, endDate);
        String result = event.saveTaskName();

        assertEquals("E | 0 | Return book | Sept 12 2024 12:00 - Mar 14 2025 04:13", result);
    }

    @Test
    public void testSaveTaskName_markAsDone_returnsCorrectFormat() {
        // check SaveTaskName() and markAsDone()

        String description = "Return book";
        LocalDateTime startDate = LocalDateTime.of(2024, 9, 12, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2025, 3, 14, 4, 13);

        Event event = new Event(description, startDate, endDate);
        event.markAsDone();
        String result = event.saveTaskName();

        assertEquals("E | 1 | Return book | Sept 12 2024 12:00 - Mar 14 2025 04:13", result);
    }

}
