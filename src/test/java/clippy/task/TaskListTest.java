package clippy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    void addTaskIncreaseSize() {
        TaskList list = new TaskList();
        int initialSize = list.size();
        list.add(new Task("write code"));
        assertEquals(initialSize + 1, list.size());
    }

    @Test
    void getTaskReturnsCorrectTask() {
        TaskList list = new TaskList();
        Task task = new Task("write code");
        list.add(task);
        assertEquals(task, list.get(0));
    }
}
