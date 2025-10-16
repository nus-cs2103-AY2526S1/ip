package monet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTask_addOneTask_sizeIncreasesByOne() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("read book", Priority.MEDIUM));
        assertEquals(1, tasks.getSize());
    }

    @Test
    public void deleteTask_validIndex_taskRemovedSuccessfully() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("task 1", Priority.HIGH));
        tasks.addTask(new Todo("task 2", Priority.LOW));
        tasks.deleteTask(0);
        assertEquals(1, tasks.getSize());
        assertEquals("[T][L][ ] task 2", tasks.getTask(0).toString());
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        // 1. Setup: Create an empty task list.
        TaskList tasks = new TaskList();

        // 2. Assert: Check that trying to delete from an empty list now throws
        //    an AssertionError because our assertion will fail first.
        assertThrows(AssertionError.class, () -> { // <-- THIS LINE IS THE FIX
            tasks.deleteTask(0);
        });
    }
}
