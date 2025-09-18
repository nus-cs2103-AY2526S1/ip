package charsiew;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import charsiew.task.Task;
import charsiew.task.TaskList;
import charsiew.task.Todo;

public class TaskListTest {

    @Test
    public void addTask_increasesSize() {
        TaskList list = new TaskList();
        Task t = new Todo("read book");
        list.add(t);
        assertEquals(1, list.size());
    }

    @Test
    public void deleteTask_removesCorrectTask() {
        TaskList list = new TaskList();
        Task t1 = new Todo("read book");
        Task t2 = new Todo("write code");
        list.add(t1);
        list.add(t2);

        Task removed = list.remove(1); // delete second task
        assertEquals("write code", removed.getName());
        assertEquals(1, list.size());
    }
}
