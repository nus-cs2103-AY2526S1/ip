package bobby;

import bobby.task.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import bobby.task.Task;

public class TaskListTest {
    @Test
    public void testAddAndSize() {
        TaskList list = new TaskList();
        list.add(new DummyTask("task1"));
        assertEquals(1, list.size());
    }

    @Test
    public void testDeleteAndGet() {
        TaskList list = new TaskList();
        DummyTask t1 = new DummyTask("task1");
        list.add(t1);
        assertEquals(t1, list.get(0));
        assertEquals(t1, list.delete(0));
        assertEquals(0, list.size());
    }

    @Test
    public void testFind() {
        TaskList list = new TaskList();
        DummyTask t1 = new DummyTask("read book");
        DummyTask t2 = new DummyTask("write code");
        list.add(t1);
        list.add(t2);
        ArrayList<Task> found = list.find("book");
        assertTrue(found.contains(t1));
        assertFalse(found.contains(t2));
    }
}