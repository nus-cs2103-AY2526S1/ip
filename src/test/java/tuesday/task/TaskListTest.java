package tuesday.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList();
        Task todo = new TodoTask("read book");
        tasks.addTask(todo);
        assertEquals(1, tasks.size());
    }

    @Test
    public void removeTaskTest() {
        TaskList tasks = new TaskList();
        Task todo = new TodoTask("read book");
        tasks.addTask(todo);
        tasks.deleteTask(todo);
        assertEquals(0, tasks.size());
    }
}
