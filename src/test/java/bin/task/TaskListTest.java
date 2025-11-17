package bin.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void add_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Task(false, "read books"));
        assertEquals(1, tasks.size());
    }
}
