package luna.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import luna.exception.LunaException;

public class TaskListTest {
    @Test
    public void delete_invalidTaskNumber_exceptionThrown() {
        TaskList list = new TaskList();
        Assertions.assertThrows(LunaException.class, () -> list.delete(-5));
        Assertions.assertThrows(LunaException.class, () -> list.delete(0));
        Assertions.assertThrows(LunaException.class, () -> list.delete(1));
        Assertions.assertThrows(LunaException.class, () -> list.delete(300));

        list.add(null);
        Assertions.assertThrows(LunaException.class, () -> list.delete(0));
        Assertions.assertThrows(LunaException.class, () -> list.delete(2));
    }

    @Test
    public void delete_validTaskNumber_success() {
        TaskList list = new TaskList();
        Task task1 = new ToDo("1");
        Task task2 = new ToDo("2");
        Task task3 = new ToDo("3");

        list.add(task1);
        Assertions.assertEquals(task1, list.delete(1));

        list.add(task2);
        list.add(task3);
        Assertions.assertEquals(task2, list.delete(1));
        Assertions.assertEquals(task3, list.delete(1));
    }
}