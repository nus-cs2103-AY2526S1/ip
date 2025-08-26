package lynx.storage;

import lynx.exception.LynxException;
import lynx.task.DeadlineTask;
import lynx.task.EventTask;
import lynx.task.Task;
import lynx.task.TodoTask;

import java.time.LocalDateTime;
import java.util.stream.Stream;

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

        assertEquals(2, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "a").count());
        assertEquals(2, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "AAA").count());
        assertEquals(2, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "test").count());
        assertEquals(1, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "a,TEST").count());
        assertEquals(1, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "bbb").count());
        assertEquals(1, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "a a").count());
        assertEquals(0, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), " a ").count());
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

        assertEquals(1, LynxTaskList.filterTasksByDate(LynxTaskList.getAllTasks(),
                LocalDateTime.of(2025, 11, 11, 0, 0)).count());
        assertEquals(2, LynxTaskList.filterTasksByDate(LynxTaskList.getAllTasks(),
                LocalDateTime.of(2025, 11, 12, 6, 0)).count());
        assertEquals(0, LynxTaskList.filterTasksByDate(LynxTaskList.getAllTasks(),
                LocalDateTime.of(2025, 11, 13, 6, 0)).count());
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
