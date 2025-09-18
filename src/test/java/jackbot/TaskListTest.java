package jackbot;

import jackbot.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void add_get_delete_andBounds() throws JackbotException {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("A"));
        tasks.add(new Todo("B"));
        tasks.add(new Todo("C"));

        assertEquals(3, tasks.size());
        assertTrue(tasks.get(1).toString().contains("A"));
        assertTrue(tasks.get(2).toString().contains("B"));
        assertTrue(tasks.get(3).toString().contains("C"));

        var removed = tasks.delete(2);
        assertTrue(removed.toString().contains("B"));
        assertEquals(2, tasks.size());
        assertTrue(tasks.get(2).toString().contains("C")); // shifted

        assertThrows(JackbotException.class, () -> tasks.get(0));
        assertThrows(JackbotException.class, () -> tasks.get(99));
        assertThrows(JackbotException.class, () -> tasks.delete(0));
        assertThrows(JackbotException.class, () -> tasks.delete(99));
    }
}
