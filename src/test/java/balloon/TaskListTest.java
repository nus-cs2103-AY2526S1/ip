package balloon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import balloon.exception.TaskNumberException;
import balloon.logic.TaskList;
import balloon.task.Deadline;
import balloon.task.Todo;

public class TaskListTest {
    @Test
    public void unmarkTask_indexOutOfBounds_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.unmarkTask(-2);
            fail(); // If runtime reaches this line it is a failed test case
        } catch (TaskNumberException e) {
            assertEquals("The given task number does not exist!", e.toString());
        }
    }

    @Test
    public void getSize_success() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("todo1"));
        tasks.addTask(new Todo("todo2"));
        tasks.addTask(new Deadline("deadline1", "tonight"));
        assertEquals(3, tasks.getSize());
    }
}
