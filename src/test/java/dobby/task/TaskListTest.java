package dobby;

import dobby.exceptions.InvalidTaskException;
import dobby.task.ToDo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void delete_validAndInvalidIndex() throws InvalidTaskException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));

        // Delete valid index
        assertEquals("Task 1", tasks.deleteTask(0).getDescription());
        assertEquals(1, tasks.size());

        // Delete invalid index
        Exception exception = assertThrows(InvalidTaskException.class, () -> {
            tasks.deleteTask(5);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }

    private Exception assertThrows(Class<InvalidTaskException> invalidTaskExceptionClass, Object o) {
    }
}
