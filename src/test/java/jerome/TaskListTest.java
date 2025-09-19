package jerome;

import jerome.task.Deadline;
import jerome.task.Task;
import jerome.task.Todo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    void add_validTodo_success() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.add(new Todo("Read System Design"));
        assertEquals(1, taskList.size());
        taskList.add(new Deadline("Submit quiz", "2025-08-29"));
        assertEquals(2, taskList.size());
    }

    @Test
    void remove_validTodo_success() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.add(new Todo("Read System Design"));
        assertEquals(1, taskList.size());
        taskList.remove(0);
        assertEquals(0, taskList.size());
    }

    @Test
    void remove_outOfBounds_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.remove(0);
        });

    }

    @Test
    void get_outOfBounds_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        assertThrows(JeromeException.class, () -> {
            taskList.get(1);
        });

    }

}
