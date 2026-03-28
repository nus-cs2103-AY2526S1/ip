package clippy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void markAsDone_marksTaskAsDone() {
        Task task = new Task("read book");
        task.markAsCompleted();
        assertTrue(task.isCompleted());
    }

    @Test
     public void toString_returnsCorrectFormat() {
        Task task = new Task("read book");
        assertEquals("[ ] read book", task.toString());
        task.markAsCompleted();
        assertEquals("[X] read book", task.toString());
    }
}
