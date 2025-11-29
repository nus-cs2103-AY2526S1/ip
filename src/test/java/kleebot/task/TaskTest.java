package kleebot.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void testMarkAndUnmark() {
        Task task = new ToDo("Read book");
        assertFalse(task.getStatus());

        task.markAsDone();
        assertTrue(task.getStatus());

        task.unmarkAsDone();
        assertFalse(task.getStatus());
    }

    @Test
    void testToStringFormat() {
        Task task = new ToDo("Write report");
        assertEquals("[T][ ]Write report", task.toString());

        task.markAsDone();
        assertEquals("[T][X]Write report", task.toString());
    }
}
