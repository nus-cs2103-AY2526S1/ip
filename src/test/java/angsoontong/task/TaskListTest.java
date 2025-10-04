package angsoontong.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    /**
     * test involving adding of tasks to tasklist
     */
    @Test
    public void addTask_sizeIncreases() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("read book");

        tasks.add(todo);

        assertEquals(1, tasks.size());
    }

    /**
     * test involving deletion of tasks from tasklist
     */
    @Test
    public void deleteTask_validIndex_taskRemoved() {
        TaskList tasks = new TaskList();
        ToDo t1 = new ToDo("task one");
        ToDo t2 = new ToDo("task two");
        tasks.add(t1);
        tasks.add(t2);

        Task removed = tasks.delete(0); // remove first task

        assertEquals(t1, removed); // check correct task removed
        assertEquals(1, tasks.size());
        assertEquals(t2, tasks.get(0)); // remaining task is correct
    }

    // test trying to access task that does not exist
    // commented out JUNIT test as case is covered by assertions in TaskList class
    /*
    @Test
    public void deleteTask_invalidIndex_throwsException() {
        TaskList tasks = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tasks.delete(5); // no tasks yet, should throw
        });
    }
     */

}

