package lynx.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import objectclasses.exception.LynxException;
import objectclasses.task.DeadlineTask;
import objectclasses.task.EventTask;
import objectclasses.task.Task;
import objectclasses.task.TodoTask;

public class LynxTaskListTest {

    @Test
    public void testFilterTasksById() throws LynxException {
        LynxTaskList.clearTasks(false);
        TodoTask testTask = new TodoTask("a");
        LynxTaskList.addTask(testTask);
        int id = testTask.getId();
        assertEquals(id, LynxTaskList.filterTasksById(LynxTaskList.getAllTasks(), id)
                .findFirst().get().getId());
    }

    @Test
    public void testFilterTasksByKeyword() throws LynxException {
        LynxTaskList.clearTasks(false);
        LynxTaskList.addTask(new TodoTask("A aaa test BBB"));
        LynxTaskList.addTask(new TodoTask("BAAA,AtestBB"));

        assertEquals(2, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "a").count());
        assertEquals(2, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "AAA").count());
        assertEquals(2, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "test").count());
        assertEquals(1, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "a,aTEST").count());
        assertEquals(1, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "bbb").count());
        assertEquals(2, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "ate").count());
        assertEquals(1, LynxTaskList.filterTasksByKeyword(
                LynxTaskList.getAllTasks(), "aaaa").count());
    }

    @Test
    public void testFilterTasksByDate() throws LynxException {
        LynxTaskList.clearTasks(false);
        LynxTaskList.addTask(new DeadlineTask("a", LocalDateTime.of(
                2025, 11, 11, 0, 0)));
        LynxTaskList.addTask(new DeadlineTask("a", LocalDateTime.of(
                2025, 11, 12, 0, 0)));
        LynxTaskList.addTask(new EventTask("a",
                LocalDateTime.of(2025, 11, 12, 0, 0),
                LocalDateTime.of(2025, 11, 13, 0, 0)));

        assertEquals(1, LynxTaskList.filterTasksByDate(LynxTaskList.getAllTasks(),
                LocalDateTime.of(2025, 11, 11, 0, 0)).count());
        assertEquals(2, LynxTaskList.filterTasksByDate(LynxTaskList.getAllTasks(),
                LocalDateTime.of(2025, 11, 12, 6, 0)).count());
        assertEquals(0, LynxTaskList.filterTasksByDate(LynxTaskList.getAllTasks(),
                LocalDateTime.of(2025, 11, 13, 6, 0)).count());
    }

    @Test
    public void testFilterTasksByStatus() throws LynxException {
        LynxTaskList.clearTasks(false);
        LynxTaskList.addTask(new DeadlineTask("a", LocalDateTime.of(
                1925, 11, 11, 0, 0)));
        LynxTaskList.addTask(new DeadlineTask("a", LocalDateTime.of(
                2025, 11, 12, 0, 0)));
        DeadlineTask testTask = new DeadlineTask("b", LocalDateTime.of(
                2025, 11, 12, 0, 0));
        testTask.setComplete();
        LynxTaskList.addTask(testTask);

        assertEquals(1, LynxTaskList.filterTasksByStatus(LynxTaskList.getAllTasks(),
                Task.Status.COMPLETE).count());
        assertEquals(1, LynxTaskList.filterTasksByStatus(LynxTaskList.getAllTasks(),
                Task.Status.INCOMPLETE).count());
        assertEquals(1, LynxTaskList.filterTasksByStatus(LynxTaskList.getAllTasks(),
                Task.Status.EXPIRED).count());
    }

    @Test
    public void testFilterTasksByType() throws LynxException {
        LynxTaskList.clearTasks(false);
        LynxTaskList.addTask(new TodoTask("a"));
        LynxTaskList.addTask(new DeadlineTask("a", LocalDateTime.of(
                2025, 11, 11, 0, 0)));
        LynxTaskList.addTask(new EventTask("a",
                LocalDateTime.of(2025, 11, 12, 0, 0),
                LocalDateTime.of(2025, 11, 13, 0, 0)));

        assertEquals(1, LynxTaskList.filterTasksByType(LynxTaskList.getAllTasks(),
                Task.TaskType.TODO).count());
        assertEquals(1, LynxTaskList.filterTasksByType(LynxTaskList.getAllTasks(),
                Task.TaskType.DEADLINE).count());
        assertEquals(1, LynxTaskList.filterTasksByType(LynxTaskList.getAllTasks(),
                Task.TaskType.EVENT).count());
    }

}
