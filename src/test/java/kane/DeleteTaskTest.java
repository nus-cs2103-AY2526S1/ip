package kane;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class DeleteTaskTest {

    @Test
    public void deleteTask_validIndex_taskRemovedSuccessfully() throws KaneException {
        // Create a task list and add some tasks
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1"));
        tasks.add(new ToDo("task 2"));
        tasks.add(new ToDo("task 3"));
        TaskList taskList = new TaskList(tasks);

        // Delete the task at index 1 (the second task)
        Task removedTask = taskList.deleteTask(1);

        // Check if the state is correct after deletion
        // Check if the list size has decreased
        assertEquals(2, taskList.getSize());

        // Check if the correct task was returned by the method
        assertEquals("task 2", removedTask.description);

        // Check if the remaining tasks are the correct ones
        assertEquals("task 1", taskList.getTasks().get(0).description);
        assertEquals("task 3", taskList.getTasks().get(1).description);
    }

    @Test
    public void deleteTask_indexOutOfBounds_throwsKaneException() {
        // Create a task list with only one task
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("only one task"));
        TaskList taskList = new TaskList(tasks);

        // Attempt to delete a task at an index that doesn't exist
        // We expect a KaneException for an invalid index
        Exception exception = assertThrows(KaneException.class, () -> {
            taskList.deleteTask(5);
        });

        assertEquals("OOPS!!! The task number is invalid.", exception.getMessage());
    }
}