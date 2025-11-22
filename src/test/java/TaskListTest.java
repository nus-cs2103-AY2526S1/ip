import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exception.GenieweenieException;
import task.Task;
import task.TaskList;
import task.Todo;


/**
 * Tests for {@link task.TaskList}.
 */
public class TaskListTest {


    /**
     * Verifies that a task added to the list can be retrieved by index.
     */
    @Test
    public void addAndGetTaskSuccess() throws GenieweenieException {
        TaskList list = new TaskList();
        Task task = new Todo("Write JUnit test");
        list.add(task);
        assertEquals(task, list.getTask(0));
    }


    /**
     * Verifies that the size reflects the number of tasks added.
     */
    @Test
    public void sizeAfterAddingTasksSuccess() throws GenieweenieException {
        TaskList list = new TaskList();
        Task task = new Todo("Write JUnit test");
        list.add(task);
        assertEquals(1, list.size());
    }
}
