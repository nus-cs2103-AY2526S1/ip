package chash.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    /*
     * AI Tool: ChatGPT 5 Free
     *
     * The tool helped to analyze some of the more complex source files and help me write sample
     * test cases. I then cross referenced it to write my test cases as I have a better context
     * compared to the tool.
     * */

    @Test
    public void addTask_increasesSize() {
        TaskList tasks = new TaskList();
        Task task = new Todo("borrow book");

        tasks.add(task);
        assertEquals(1, tasks.size(), "TaskList should have a size of 1 now");

        tasks.add(task);
        assertEquals(2, tasks.size(), "TaskList should have a size of 2 now");

        assertEquals(task, tasks.get(0), "Task object retrieved should be the same");
    }

    @Test
    public void deleteTask_removesCorrectTask() {
        TaskList tasks = new TaskList();
        Task t1 = new Todo("task one");
        Task t2 = new Todo("task two");
        tasks.add(t1);
        tasks.add(t2);

        Task deleted = tasks.remove(0);

        assertEquals(t1, deleted, "Deleted task should be the first one added");
        assertEquals(1, tasks.size(), "List size should decrease by 1");
        assertEquals(t2, tasks.get(0), "Remaining task should be task two");
    }

    @Test
    public void deleteTask_invalidIndex_throwsException() {
        TaskList tasks = new TaskList();

        assertThrows(
            IndexOutOfBoundsException.class, () -> tasks.remove(0),
            "Deleting from an empty list should throw IndexOutOfBoundsException"
        );
    }
}
