package lynx.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import lynx.parser.LynxSorter;
import objectclasses.exception.LynxException;
import objectclasses.task.DeadlineTask;
import objectclasses.task.EventTask;
import objectclasses.task.Task;
import objectclasses.task.TodoTask;

public class LynxTaskListTest {

    private final LynxTaskList taskList = new LynxTaskList();

    @Test
    public void testFilterTasksById() throws LynxException {
        taskList.clearTasks();
        TodoTask testTask = new TodoTask("a", 0);
        taskList.addTask(testTask);
        int id = testTask.getId();
        assertEquals(id, LynxSorter.filterTasksById(taskList.getAllTasks(), id)
                .findFirst().get().getId());
    }

    @Test
    public void testFilterTasksByKeyword() throws LynxException {
        taskList.clearTasks();
        taskList.addTask(new TodoTask("A aaa test BBB", 0));
        taskList.addTask(new TodoTask("BAAA,AtestBB", 0));

        assertEquals(2, LynxSorter.filterTasksByKeyword(
                taskList.getAllTasks(), "a").count());
        assertEquals(2, LynxSorter.filterTasksByKeyword(
                taskList.getAllTasks(), "AAA").count());
        assertEquals(2, LynxSorter.filterTasksByKeyword(
                taskList.getAllTasks(), "test").count());
        assertEquals(1, LynxSorter.filterTasksByKeyword(
                taskList.getAllTasks(), "a,aTEST").count());
        assertEquals(1, LynxSorter.filterTasksByKeyword(
                taskList.getAllTasks(), "bbb").count());
        assertEquals(2, LynxSorter.filterTasksByKeyword(
                taskList.getAllTasks(), "ate").count());
        assertEquals(1, LynxSorter.filterTasksByKeyword(
                taskList.getAllTasks(), "aaaa").count());
    }

    @Test
    public void testFilterTasksByDate() throws LynxException {
        taskList.clearTasks();
        taskList.addTask(new DeadlineTask("a", 0, LocalDateTime.of(
                2025, 11, 11, 0, 0)));
        taskList.addTask(new DeadlineTask("a", 0, LocalDateTime.of(
                2025, 11, 12, 0, 0)));
        taskList.addTask(new EventTask("a", 0,
                LocalDateTime.of(2025, 11, 12, 0, 0),
                LocalDateTime.of(2025, 11, 13, 0, 0)));

        assertEquals(1, LynxSorter.filterTasksByDate(taskList.getAllTasks(),
                LocalDateTime.of(2025, 11, 11, 0, 0)).count());
        assertEquals(2, LynxSorter.filterTasksByDate(taskList.getAllTasks(),
                LocalDateTime.of(2025, 11, 12, 6, 0)).count());
        assertEquals(0, LynxSorter.filterTasksByDate(taskList.getAllTasks(),
                LocalDateTime.of(2025, 11, 13, 6, 0)).count());
    }

    @Test
    public void testFilterTasksByStatus() throws LynxException {
        taskList.clearTasks();
        taskList.addTask(new DeadlineTask("a", 0, LocalDateTime.of(
                1925, 11, 11, 0, 0)));
        taskList.addTask(new DeadlineTask("a", 0, LocalDateTime.of(
                2025, 11, 12, 0, 0)));
        DeadlineTask testTask = new DeadlineTask("b", 0, LocalDateTime.of(
                2025, 11, 12, 0, 0));
        testTask.setComplete();
        taskList.addTask(testTask);

        assertEquals(1, LynxSorter.filterTasksByStatus(taskList.getAllTasks(),
                Task.Status.COMPLETE).count());
        assertEquals(1, LynxSorter.filterTasksByStatus(taskList.getAllTasks(),
                Task.Status.INCOMPLETE).count());
        assertEquals(1, LynxSorter.filterTasksByStatus(taskList.getAllTasks(),
                Task.Status.EXPIRED).count());
    }

    @Test
    public void testFilterTasksByType() throws LynxException {
        taskList.clearTasks();
        taskList.addTask(new TodoTask("a", 0));
        taskList.addTask(new DeadlineTask("a", 0, LocalDateTime.of(
                2025, 11, 11, 0, 0)));
        taskList.addTask(new EventTask("a", 0,
                LocalDateTime.of(2025, 11, 12, 0, 0),
                LocalDateTime.of(2025, 11, 13, 0, 0)));

        assertEquals(1, LynxSorter.filterTasksByType(taskList.getAllTasks(),
                Task.TaskType.TODO).count());
        assertEquals(1, LynxSorter.filterTasksByType(taskList.getAllTasks(),
                Task.TaskType.DEADLINE).count());
        assertEquals(1, LynxSorter.filterTasksByType(taskList.getAllTasks(),
                Task.TaskType.EVENT).count());
    }

}
