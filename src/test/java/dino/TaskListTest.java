package dino;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Homework");
        taskList.addTask(todo);
        assertEquals(1, taskList.getAllTasks().size());
        assertEquals(todo, taskList.get(0));

        Task deadline = new Deadline("Quiz", "2025-09-09");
        taskList.addTask(deadline);
        assertEquals(2, taskList.getAllTasks().size());
        assertEquals(deadline, taskList.get(1));

        Task event = new Event("Zoom meeting", "3pm", "4pm");
        taskList.addTask(event);
        assertEquals(3, taskList.getAllTasks().size());
        assertEquals(event, taskList.get(2));
    }
}
