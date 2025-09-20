package marquess;

import marquess.task.Deadline;
import marquess.task.Task;
import marquess.task.Todo;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void taskList_addTaskBase_success() throws Exception {
        Field taskListField = TaskList.class.getDeclaredField("taskList");
        taskListField.setAccessible(true);
        Field descriptionField = Task.class.getDeclaredField("description");
        descriptionField.setAccessible(true);

        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("test"));
        @SuppressWarnings("unchecked")
        ArrayList<Task> temp = (ArrayList<Task>) taskListField.get(taskList);
        assertEquals(1, temp.size());
        assertEquals("test", descriptionField.get(temp.get(0)));

        taskList.addTask(new Deadline("test2", "2025-01-01"));
        assertEquals(2, temp.size());
        assertEquals("test2", descriptionField.get(temp.get(1)));
    }

    @Test
    public void taskList_deleteTaskBase_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("test"));
        taskList.addTask(new Deadline("test2", "2025-01-01"));
        String result = taskList.deleteTask(0);

        // Only one task should remain
        Field taskListField = TaskList.class.getDeclaredField("taskList");
        taskListField.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<Task> temp = (ArrayList<Task>) taskListField.get(taskList);
        assertEquals(1, temp.size());
        Field descriptionField = Task.class.getDeclaredField("description");
        descriptionField.setAccessible(true);
        assertEquals("test2", descriptionField.get(temp.get(0)));
    }

    @Test
    public void taskList_deleteTaskInvalidIndex_exceptionThrown() throws Exception {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("test"));
        try {
            taskList.deleteTask(5);
            fail();
        } catch (Exception e) {
            assert(e.getMessage().contains("cannot be found"));
        }
    }

    @Test
    public void taskList_markTaskBase_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("test"));
        String result = taskList.markTask(0);
        assert(result.contains("[T][X] test"));
    }

    @Test
    public void taskList_unmarkTaskBase_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("test"));
        taskList.markTask(0);
        String result = taskList.unmarkTask(0);
        assert(result.contains("[T][ ] test"));
    }

    @Test
    public void taskList_markTaskInvalidIndex_exceptionThrown() throws Exception {
        TaskList taskList = new TaskList();
        try {
            taskList.markTask(0);
            fail();
        } catch (Exception e) {
            assert(e.getMessage().contains("cannot be found"));
        }
    }

    @Test
    public void taskList_findTasksBase_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Deadline("write book", "2025-01-01"));
        String result = taskList.findTasks("book");
        // Should find both tasks
        assert(result.contains("read book"));
        assert(result.contains("write book"));
    }

    @Test
    public void taskList_getTaskDisplayBase_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Deadline("write book", "2025-01-01"));
        String result = taskList.getTaskDisplay();
        assert(result.contains("read book"));
        assert(result.contains("write book"));
    }
}
