package junny;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import junny.tasks.Task;

class TaskTest {

    @Test
    void testConvertToTask() {
        // Todo task, not done
        Task t1 = Task.convertToTask("T | 0 | read book");
        assertNotNull(t1);
        assertEquals(TaskTypes.TODO, t1.getType());
        assertFalse(t1.getStatusIcon().equals("X")); // not done

        // Deadline, done
        Task t2 = Task.convertToTask("D | 1 | submit report | 2025-09-01");
        assertNotNull(t2);
        assertEquals(TaskTypes.DEADLINE, t2.getType());
        assertEquals("X", t2.getStatusIcon()); // done

        // Event, not done
        Task t3 = Task.convertToTask("E | 0 | meeting | 2025-08-31 to 2025-09-01");
        assertNotNull(t3);
        assertEquals(TaskTypes.EVENT, t3.getType());
        assertEquals(" ", t3.getStatusIcon());
    }
}
