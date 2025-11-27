package whchenyicn.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    void constructorEmptyTest() {
        TaskList list = new TaskList();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void constructorLimitTest() {
        TaskList list = new TaskList(50);
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void constructorListTest() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("read book"));
        tasks.add(new Task("do homework"));
        TaskList list = new TaskList(tasks);

        assertEquals(2, list.size());
        assertEquals("read book", list.get(0).toString().substring(4));
        assertEquals("do homework", list.get(1).toString().substring(4));
    }

    @Test
    void addAndGetTest() {
        TaskList list = new TaskList();
        Task t = new Task("cs2103");
        list.add(t);

        assertEquals(1, list.size());
        assertEquals(t, list.get(0));
    }

    @Test
    void addAllTest() {
        TaskList list = new TaskList();
        List<Task> tasks = List.of(new Task("cs2103"), new Task("cs2109s"));
        list.addAll(tasks);

        assertEquals(2, list.size());
        assertEquals("cs2103", list.get(0).toString().substring(4));
        assertEquals("cs2109s", list.get(1).toString().substring(4));
    }

    @Test
    void removeTest() {
        TaskList list = new TaskList();
        Task t1 = new Task("cs2103");
        Task t2 = new Task("cs2109s");
        list.add(t1);
        list.add(t2);

        Task removed = list.remove(0);
        assertEquals(t1, removed);
        assertEquals(1, list.size());
        assertEquals(t2, list.get(0));
    }

    @Test
    void clearTest() {
        TaskList list = new TaskList();
        list.add(new Task("cs2103"));
        list.add(new Task("cs2109s"));
        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void asListTest() {
        Task t = new Task("do cs2103");
        List<Task> tasks = new ArrayList<>();
        tasks.add(t);

        TaskList list = new TaskList(tasks);
        List<Task> returned = list.asList();

        assertEquals(1, returned.size());
        assertEquals(t, returned.get(0));
    }
}
