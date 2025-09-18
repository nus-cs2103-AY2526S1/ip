package cheryl.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import cheryl.task.Task;
import cheryl.task.Todo;

class TaskListTest {

    @Test
    void testAddTask() {
        TaskList list = new TaskList();
        Todo task = new Todo("Finish homework");

        // Normal add
        list.addTask(task);
        assertEquals(1, list.getTasks().size());
        assertEquals("Finish homework", list.getTasks().get(0).getTitle());

        // Adding another task
        Todo task2 = new Todo("Read book");
        list.addTask(task2);
        assertEquals(2, list.getTasks().size());
        assertEquals("Read book", list.getTasks().get(1).getTitle());
    }

    @Test
    public void testMarkTask() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("read book"));

        // ✅ Use 1-based indexing
        tasks.markTask(1);

        assertTrue(tasks.getTask(1).isDone(), "Task should be marked as done");
    }

    @Test
    public void testRemoveTask() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("task1"));
        tasks.addTask(new Todo("task2"));

        Task removed = tasks.deleteTask(1); // test assumes 1-based
        assertEquals("task1", removed.getTitle());
    }
}
