package mon.task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    void testDeadlineCreation() {
        Deadline deadline = new Deadline("Submit assignment", "2023-12-25");
        assertEquals("Submit assignment", deadline.getTaskName());
        assertFalse(deadline.getStatus());
        assertEquals(LocalDate.of(2023, 12, 25), deadline.getDeadline());
    }

    @Test
    void testDeadlineCreationWithStatus() {
        Deadline deadline = new Deadline("Submit assignment", true, "2023-12-25");
        assertEquals("Submit assignment", deadline.getTaskName());
        assertTrue(deadline.getStatus());
        assertEquals(LocalDate.of(2023, 12, 25), deadline.getDeadline());
    }

    @Test
    void testDeadlineCreationInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("Submit assignment", "invalid-date");
        });
    }

    @Test
    void testToFileString() {
        Deadline deadline = new Deadline("Submit assignment", "2023-12-25");
        assertEquals("D | 0 | Submit assignment | Dec 25 2023", deadline.toFileString());
        
        deadline.setStatus(true);
        assertEquals("D | 1 | Submit assignment | Dec 25 2023", deadline.toFileString());
    }

    @Test
    void testToString() {
        Deadline deadline = new Deadline("Submit assignment", "2023-12-25");
        assertEquals("[D][ ] Submit assignment (by: Dec 25 2023)", deadline.toString());
        
        deadline.setStatus(true);
        assertEquals("[D][X] Submit assignment (by: Dec 25 2023)", deadline.toString());
    }

    @Test
    void testToDeadlineTask() {
        String taskString = "D | 1 | Submit assignment | Dec 25 2023";
        Deadline deadline = Deadline.toDeadlineTask(taskString);
        assertEquals("Submit assignment", deadline.getTaskName());
        assertTrue(deadline.getStatus());
        assertEquals(LocalDate.of(2023, 12, 25), deadline.getDeadline());
    }

    @Test
    void testToDeadlineTaskInvalidFormat() {
        String taskString = "D | 1 | Submit assignment"; // Missing deadline
        assertThrows(IllegalArgumentException.class, () -> {
            Deadline.toDeadlineTask(taskString);
        });
    }
}
