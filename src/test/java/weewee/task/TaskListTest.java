package weewee.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTask_taskIncreaseInSize() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList list = new TaskList(tasks);
        list.add(new Task("read book"));

        assertEquals(1, list.size());
        assertEquals("read book", list.get(0).getTaskName());
    }

    @Test
    public void deleteTask_validIndex_removesCorrectTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList list = new TaskList(tasks);
        list.add(new Task("A"));
        list.add(new Task("B"));

        Task removed = list.remove(0);

        assertEquals("A", removed.getTaskName());
        assertEquals(1, list.size());
        assertEquals("B", list.get(0).getTaskName());
    }
}
