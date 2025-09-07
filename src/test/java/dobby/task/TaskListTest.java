package dobby;

import dobby.exceptions.InvalidTaskException;
import dobby.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void delete_validAndInvalidIndex() throws InvalidTaskException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("Task 1"));
        tasks.add(new ToDo("Task 2"));

        // Delete valid index
        assertEquals("Task 1", tasks.delete(0).getDescription());
        assertEquals(1, tasks.size());

        // Delete invalid index
        Exception exception = assertThrows(InvalidTaskException.class, () -> {
            tasks.delete(5);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }
}
