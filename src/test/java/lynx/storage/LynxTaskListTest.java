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
    public void findTaskById() throws LynxException {
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

    @Test
    public void findTaskByPos() throws LynxException {
        LynxTaskList.clearTasks(false);
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
    public void printTasksContaining() {
        LynxTaskList.clearTasks(false);
        LynxTaskList.addTask(new TodoTask("A aaa test BBB"), true);
        LynxTaskList.addTask(new TodoTask("BAAA,testBB"), true);
        assertEquals(2, LynxTaskList.printTasksContaining("a"));
        assertEquals(2, LynxTaskList.printTasksContaining("AAA"));
        assertEquals(2, LynxTaskList.printTasksContaining("test"));
        assertEquals(1, LynxTaskList.printTasksContaining("a,TEST"));
        assertEquals(1, LynxTaskList.printTasksContaining("bbb"));
        assertEquals(1, LynxTaskList.printTasksContaining("a a"));
        assertEquals(0, LynxTaskList.printTasksContaining(" a "));
    }

    @Test
    public void printTasksOnDate() {
        LynxTaskList.clearTasks(false);
        LynxTaskList.addTask(new DeadlineTask("a", LocalDateTime.of(
                2025, 11, 11, 0, 0)), true);
        LynxTaskList.addTask(new DeadlineTask("a", LocalDateTime.of(
                2025, 11, 12, 0, 0)), true);
        LynxTaskList.addTask(new EventTask("a",
                LocalDateTime.of(2025, 11, 12, 0, 0),
                LocalDateTime.of(2025, 11, 13, 0, 0)), true);
        assertEquals(1, LynxTaskList.printTasksOnDate(
                LocalDateTime.of(2025, 11, 11, 0, 0)));
        assertEquals(2, LynxTaskList.printTasksOnDate(
                LocalDateTime.of(2025, 11, 12, 6, 0)));
        assertEquals(0, LynxTaskList.printTasksOnDate(
                LocalDateTime.of(2025, 11, 13, 6, 0)));
    }

}
