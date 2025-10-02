package bobby;

import bobby.task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class DeadlineTest {
    @Test
    void constructor_validInput_success() {
        Deadline d = new Deadline("submit report", "18/9/2025 2359");
        assertEquals("submit report", d.getDescription());
        assertEquals(LocalDateTime.of(2025, 9, 18, 23, 59), d.getBy());
    }

    @Test
    void toString_and_toSaveFormat() {
        Deadline d = new Deadline("submit report", "18/9/2025 2359");
        String str = d.toString();
        assertTrue(str.contains("submit report"));
        assertTrue(str.contains("by:"));
        String save = d.toSaveFormat();
        assertTrue(save.startsWith("D | 0 | submit report | 18/9/2025 2359"));
    }

    @Test
    void markAsDone_and_markAsNotDone() {
        Deadline d = new Deadline("submit report", "18/9/2025 2359");
        d.markAsDone();
        assertTrue(d.isDone());
        d.markAsNotDone();
        assertFalse(d.isDone());
    }
}

