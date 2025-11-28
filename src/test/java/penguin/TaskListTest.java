package penguin;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import penguin.tasks.Task;
import penguin.app.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testGetNumOfTasks() {
        List<Task> emptyList = new ArrayList<>();
        List<Task> lst = new ArrayList<>();
        lst.add(new Task("dummy"));
        TaskList emptyTasks = new TaskList(emptyList);
        TaskList tasks = new TaskList(lst);
        assertEquals(0, emptyTasks.getNumOfTasks());
        assertEquals(1, tasks.getNumOfTasks());
    }

    @Test
    public void testGetTask() {
        List<Task> lst = new ArrayList<>();
        Task first = new Task("first");
        Task second = new Task("second");
        Task third = new Task("third");
        lst.add(first);
        lst.add(second);
        lst.add(third);
        TaskList tasks = new TaskList(lst);
        assertEquals(first, tasks.getTask(0));
        assertEquals(second, tasks.getTask(1));
        assertEquals(third, tasks.getTask(2));
    }
}
