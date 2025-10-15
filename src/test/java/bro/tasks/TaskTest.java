package bro.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void constructor_setsDescriptionAndIsDone() {
        Task t = new Task("desc");
        assertEquals("desc", t.getDescription());
        assertFalse(t.isDone);
    }

    @Test
    public void getStatusIcon_doneAndNotDone() {
        Task t1 = new Task("desc", false);
        Task t2 = new Task("desc", true);
        assertEquals(" ", t1.getStatusIcon());
        assertEquals("X", t2.getStatusIcon());
    }

    @Test
    public void mark_setsIsDoneTrue() {
        Task t = new Task("desc");
        t.markAsDone();
        assertTrue(t.isDone);
    }

    @Test
    public void unmark_setsIsDoneFalse() {
        Task t = new Task("desc", true);
        t.markAsUndone();
        assertFalse(t.isDone);
    }
}
