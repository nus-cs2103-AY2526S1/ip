package sora.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void getStatusIcon_notDone_returnsSpace() {
        Task task = new Task(Task.TaskType.TODO, "Do something");
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_done_returnsX() {
        Task task = new Task(Task.TaskType.TODO, "Do something");
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void toFormat_todoTask_returnsCorrectFormat() {
        Task task = new Task(Task.TaskType.TODO, "Do something");
        assertEquals("T | 0 | Do something", task.toFormat());
    }

    @Test
    public void toFormat_doneTodoTask_returnsCorrectFormat() {
        Task task = new Task(Task.TaskType.TODO, "Do something");
        task.markAsDone();
        assertEquals("T | 1 | Do something", task.toFormat());
    }

    @Test
    public void toFormat_deadlineWithoutSubclass_throwsException() {
        try {
            assertEquals("", new Task(Task.TaskType.DEADLINE, "Do something").toFormat());
            fail();
        } catch (Exception e) {
            assertEquals("Not a deadline type", e.getMessage());
        }
    }

}
