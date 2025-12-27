package Dan.Task;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void Test1() {
        String[] taskInfo = {"T", "false", "homework"};
        Task todo = Task.createTask(taskInfo);
        assert todo instanceof ToDo;
    }

    @Test
    public void Test2() {
        String[] taskInfo = {"D", "false", "assignment", "28/8/2025"};
        Task deadline = Task.createTask(taskInfo);
        assert deadline instanceof Deadline;
    }

    @Test
    public void Test3() {
        String[] taskInfo = {"E", "false", "event", "28/8/2025", "29/8/2025"};
        Task event = Task.createTask(taskInfo);
        assert event instanceof Event;
    }
}
