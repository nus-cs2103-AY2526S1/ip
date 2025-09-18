package chiikawa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chiikawa.task.Task;
import chiikawa.task.ToDoTask;

public class TaskListTest {
    @Test
    public void constructor_empty_createsTaskList() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getTaskList().size());
    }

    @Test
    public void addTask_newTask_increasesSizeAndStoresTask() {
        TaskList taskList = new TaskList();
        ToDoTask newTask = new ToDoTask("drink water");
        taskList.addTask(newTask);
        assertEquals(1, taskList.getTaskList().size());
        assertEquals(taskList.getTaskList().get(0), newTask);
    }

    @Test
    public void deleteTask_delete_decreasesSizeAndRemovesTask() {
        TaskList taskList = new TaskList();
        ToDoTask newTask = new ToDoTask("drink water");
        taskList.addTask(newTask);
        ToDoTask newTask2 = new ToDoTask("eat brains");
        taskList.addTask(newTask2);

        taskList.deleteTask(1);
        assertEquals(1, taskList.getTaskList().size());
        assertEquals(taskList.getTaskList().get(0), newTask2);
    }

    @Test
    public void markTest_mark_marksTaskAsCompleted() {
        TaskList taskList = new TaskList();
        ToDoTask newTask = new ToDoTask("drink water");
        taskList.addTask(newTask);
        ToDoTask newTask2 = new ToDoTask("eat brains");
        taskList.addTask(newTask2);
        Task returnTask = taskList.markTask(1);

        assertEquals(taskList.getTaskList().get(0).getStatusIcon(), "1");
        assertEquals(taskList.getTaskList().get(0), returnTask);
    }

    @Test
    public void unmarkTest_unmark_unmarksTaskAsNotCompleted() {
        TaskList taskList = new TaskList();
        ToDoTask newTask = new ToDoTask("drink water");
        taskList.addTask(newTask);
        ToDoTask newTask2 = new ToDoTask("eat brains", true);
        taskList.addTask(newTask2);
        Task returnTask = taskList.unmarkTask(2);

        assertEquals(taskList.getTaskList().get(0).getStatusIcon(), "0");
        assertEquals(taskList.getTaskList().get(1), returnTask);
    }
}
