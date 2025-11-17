package pagrobot.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pagrobot.tasks.ToDo;

public class TaskListTest {
    @Test
    public void addTask() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test123"));
        assertEquals("test123", taskList.get(0).getName());
    }

    @Test
    void deleteTask() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test123"));
        taskList.delete(0);
        assertEquals(0, taskList.size());
    }

    @Test
    void deleteInvalidIndex() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.delete(0));
    }
}
