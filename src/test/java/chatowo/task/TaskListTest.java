package chatowo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTask_validTasks_success() {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("read book");
        Task event = new Deadline("meeting", "2024-01-01");

        taskList.add(todo);
        taskList.add(event);

        assertEquals(2, taskList.size());
        assertEquals(todo, taskList.get(0));
        assertEquals(event, taskList.get(1));
    }

    @Test
    public void addTask_nullTask_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TaskList().add(null);
        });
    }

    @Test
    public void removeTask_validIndex_removesTask() {
        TaskList list = new TaskList();
        list.add(new ToDo("task1"));
        list.add(new ToDo("task2"));
        list.remove(0);
        assertEquals(1, list.size());
        assertEquals("task2", list.get(0).getName());
    }

    @Test
    public void markTask_validIndex_marksTask() {
        TaskList list = new TaskList();
        list.add(new ToDo("task1"));
        list.done(0);
        assertTrue(list.get(0).isDone());
    }

    @Test
    public void markTask_invalidIndex_throwsException() {
        TaskList list = new TaskList();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.done(0);
        });
        assertTrue(exception.getMessage().contains("Index"));
    }
}
