package lynx.storage;

import lynx.exception.LynxException;
import lynx.task.DeadlineTask;
import lynx.task.EventTask;
import lynx.task.Task;
import lynx.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LynxTaskListTest {

    @Test
    public void findTaskById() throws LynxException {
        TodoTask testTask = new TodoTask("a");
        LynxTaskList.addTask(testTask, true);
        int id = testTask.getId();
        assertEquals(testTask, LynxTaskList.findTaskById(id));

        try {
            LynxTaskList.findTaskById(-1);
            fail();
        } catch (LynxException e) {
        }
    }

    @Test
    public void findTaskByPos() throws LynxException {
        LynxTaskList.addTask(new TodoTask("a"), true);
        LynxTaskList.addTask(new TodoTask("b"), true);
        Task testTask1 = LynxTaskList.findTaskByPosition(1);
        assertEquals(testTask1, LynxTaskList.findTaskByPosition(1));
        Task testTask2 = LynxTaskList.findTaskByPosition(2);
        assertEquals(testTask2, LynxTaskList.findTaskByPosition(2));

        try {
            LynxTaskList.findTaskByPosition(0);
            fail();
        } catch (LynxException e) {
            try {
                LynxTaskList.findTaskByPosition(LynxTaskList.getCount() + 1);
                fail();
            } catch (LynxException ex) {
            }
        }
    }

    @Test
    public void printTasksOnDate() {
        LynxTaskList.addTask(new DeadlineTask("a", LocalDateTime.of(
                2025, 11, 11, 0, 0)), true);
        LynxTaskList.addTask(new EventTask("a",
                LocalDateTime.of(2025, 11, 12, 2, 0),
                LocalDateTime.of(2025, 11, 13, 0, 0)), true);
        assertTrue(LynxTaskList.printTasksOnDate(
                LocalDateTime.of(2025, 11, 11, 0, 0)));
        assertTrue(LynxTaskList.printTasksOnDate(
                LocalDateTime.of(2025, 11, 12, 6, 0)));
        assertFalse(LynxTaskList.printTasksOnDate(
                LocalDateTime.of(2025, 11, 12, 0, 0)));
    }

}
