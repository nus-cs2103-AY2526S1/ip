package tinkerton.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    void testAdd_success() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("Read book", false);
        tasks.add(todo);

        assertEquals(tasks.size(), 1);
    }

    @Test
    void testRemove_success() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("Read book", false);
        tasks.add(todo);

        assertEquals(tasks.size(), 1);

        tasks.remove(0);

        assertEquals(tasks.size(), 0);
    }

    @Test
    void testSize_success() {
        TaskList tasks = new TaskList();
        ToDo todo1 = new ToDo("Read book", false);
        ToDo todo2 = new ToDo("Read paper", false);
        tasks.add(todo1);

        assertEquals(tasks.size(), 1);

        tasks.add(todo2);

        assertEquals(tasks.size(), 2);
    }

    @Test
    void testGet_success() {
        TaskList tasks = new TaskList();
        ToDo todo1 = new ToDo("Read book", false);
        ToDo todo2 = new ToDo("Read paper", false);
        tasks.add(todo1);
        tasks.add(todo2);

        assertEquals("[T][ ] Read book", tasks.get(0).toString());
        assertEquals("[T][ ] Read paper", tasks.get(1).toString());
    }

    @Test
    void testFilter_success() {
        TaskList tasks = new TaskList();
        ToDo todo1 = new ToDo("Read book", true);
        ToDo todo2 = new ToDo("Read paper", false);
        tasks.add(todo1);
        tasks.add(todo2);
        TaskList filtered = tasks.filter(t -> t.isCompleted());

        assertEquals(filtered.size(), 1);
    }
}
