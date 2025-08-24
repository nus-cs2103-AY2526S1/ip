package lynx.storage;

import lynx.exception.LynxException;
import lynx.task.DeadlineTask;
import lynx.task.EventTask;
import lynx.task.Task;
import lynx.task.TodoTask;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

// printALlTasks is not tested as it is trivial.
// printTaskById is not tested as it relies on findTaskById, which is already tested.
public class LynxTaskListTest {

    @Test
    public void testFindTasksContaining() throws LynxException {
        LynxTaskList.clearTasks(false);
        LynxTaskList.addTask(new TodoTask("A aaa test BBB"), true);
        LynxTaskList.addTask(new TodoTask("BAAA,testBB"), true);
        assertEquals(2, LynxTaskList.findTasksContaining("a").size());
        assertEquals(2, LynxTaskList.findTasksContaining("AAA").size());
        assertEquals(2, LynxTaskList.findTasksContaining("test").size());
        assertEquals(1, LynxTaskList.findTasksContaining("a,TEST").size());
        assertEquals(1, LynxTaskList.findTasksContaining("bbb").size());
        assertEquals(1, LynxTaskList.findTasksContaining("a a").size());
        assertEquals(0, LynxTaskList.findTasksContaining(" a ").size());
    }

    @Test
    public void testFindTasksOnDate() throws LynxException {
        LynxTaskList.clearTasks(false);
        LynxTaskList.addTask(new DeadlineTask("a", LocalDateTime.of(
                2025, 11, 11, 0, 0)), true);
        LynxTaskList.addTask(new DeadlineTask("a", LocalDateTime.of(
                2025, 11, 12, 0, 0)), true);
        LynxTaskList.addTask(new EventTask("a",
                LocalDateTime.of(2025, 11, 12, 0, 0),
                LocalDateTime.of(2025, 11, 13, 0, 0)), true);
        assertEquals(1, LynxTaskList.findTasksOnDate(
                LocalDateTime.of(2025, 11, 11, 0, 0)).size());
        assertEquals(2, LynxTaskList.findTasksOnDate(
                LocalDateTime.of(2025, 11, 12, 6, 0)).size());
        assertEquals(0, LynxTaskList.findTasksOnDate(
                LocalDateTime.of(2025, 11, 13, 6, 0)).size());
    }

    @Test
    public void testFindTaskById() throws LynxException {
        LynxTaskList.clearTasks(false);
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

}
