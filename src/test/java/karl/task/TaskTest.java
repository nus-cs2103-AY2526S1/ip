package karl.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void constructor_setsDescriptionAndDefaultsToNotDone() {
        Task task = new Task("Read");
        assertEquals("Read", task.getDescription());
        assertFalse(task.isDone());
    }

    @Test
    public void markAsDone_setsTaskToDone() {
        Task task = new Task("Finish assignment");
        assertFalse(task.isDone());
        task.markAsDone();
        assertTrue(task.isDone());
    }

    @Test
    public void markAsNotDone_setsTaskToNotDone() {
        Task task = new Task("Go shopping");
        task.markAsDone();
        assertTrue(task.isDone());
        task.markAsNotDone();
        assertFalse(task.isDone());
    }

    @Test
    public void getStatusIcon_statusNotDone_returnsSpace() {
        Task task = new Task("status test");
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_statusDone_returnsX() {
        Task task = new Task("status test");
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void toString_formatsTaskCorrectly() {
        Task task = new Task("finish book");
        assertEquals("[ ] finish book", task.toString());
        task.markAsDone();
        assertEquals("[X] finish book", task.toString());
    }
}

// Used AI to help clean up code and naming conventions etc, mainly small issues brought up through code reviews