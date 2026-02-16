package angus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import angus.exception.AngusException;
import angus.ui.Ui;

public class TaskListTest {
    @Test
    public void getTaskList_emptyList_exceptionThrown() {
        try {
            List<Task> tasks = new ArrayList<>();
            assertTrue(tasks.isEmpty());
            TaskList taskList = new TaskList(new Ui(), tasks);
            assertEquals(0, taskList.getSize());
            taskList.getTaskList();
            fail();
        } catch (AngusException e) {
            assertEquals("Your task list is empty!", e.getMessage());
        }
    }
    @Test
    public void getTaskList_nonEmptyList_success() {
        try {
            List<Task> tasks = new ArrayList<>();
            assertTrue(tasks.isEmpty());
            TaskList taskList = new TaskList(new Ui(), tasks);
            assertEquals(0, taskList.getSize());
            taskList.addTodo("Test 1");
            assertEquals(1, taskList.getSize());
            assertEquals("Here are your tasks:\n1.[T][ ] Test 1", taskList.getTaskList());
        } catch (AngusException e) {
            fail();
        }
    }
}
