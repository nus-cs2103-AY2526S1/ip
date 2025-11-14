package task;

import exception.BaymaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;
    private Task t1;
    private Task t2;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        t1 = new Task("Task 1", TaskType.TODO);
        t2 = new Task("Task 2", TaskType.DEADLINE);
    }

    @Test
    void testAddAndSize() throws BaymaxException {
        taskList.add(t1);
        taskList.add(t2);
        assertEquals(2, taskList.size());
    }

    @Test
    void testAddDuplicateThrows() throws BaymaxException {
        taskList.add(t1);
        BaymaxException e = assertThrows(BaymaxException.class, () -> taskList.add(t1));
        assertEquals("This task already exists!", e.getMessage());
    }

    @Test
    void testRemoveAndGet() throws BaymaxException {
        taskList.add(t1);
        taskList.add(t2);
        Task removed = taskList.remove(0);
        assertEquals(t1, removed);
        assertEquals(t2, taskList.get(0));
    }

    @Test
    void testFindTasks() throws BaymaxException {
        taskList.add(t1);
        taskList.add(t2);
        ArrayList<Task> results = taskList.findTasks("1");
        assertEquals(1, results.size());
        assertEquals(t1, results.get(0));

        // No match
        assertTrue(taskList.findTasks("nonexistent").isEmpty());
    }

    @Test
    void testGetAll() throws BaymaxException {
        taskList.add(t1);
        taskList.add(t2);
        ArrayList<Task> all = taskList.getAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(t1));
        assertTrue(all.contains(t2));
    }
}
