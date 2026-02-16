package geni.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo todo = new Todo("Read book");
        tasks.add(todo);
        assertEquals(1, tasks.size());
        assertEquals(todo, tasks.get(0));
    }

    @Test
    public void testDeleteTask() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo todo = new Todo("Read book");
        tasks.add(todo);
        tasks.delete(0);
        assertEquals(0, tasks.size());
    }

    @Test
    public void testFindMatchingTasks() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo t1 = new Todo("Read book");
        Todo t2 = new Todo("Write essay");
        tasks.add(t1);
        tasks.add(t2);

        List<Task> results = tasks.find("Read");
        assertEquals(1, results.size());
        assertEquals(t1, results.get(0));
    }

    @Test
    public void testFindFreeSlotWithNoTasks_returnsSlot() {
        TaskList tasks = new TaskList(new ArrayList<>());
        String result = tasks.findFreeSlot(2);

        // should always propose a free slot (since no tasks exist)
        assertTrue(result.contains("Nearest free 2h slot"));
    }

    @Test
    public void testSizeAfterMultipleAdds() {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.add(new Todo("Read book"));
        tasks.add(new Todo("Write essay"));
        tasks.add(new Todo("Go jogging"));

        assertEquals(3, tasks.size());
    }
}
