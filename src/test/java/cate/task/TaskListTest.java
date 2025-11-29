package cate.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList taskList;
    private Task todo;
    private Task deadline;
    private Task event;

    @BeforeEach
    public void setUp() {
        todo = new Todo("Buy milk");
        deadline = new Deadline("Submit report", java.time.LocalDateTime.now());
        event = new Event("Meeting", "10:00", "11:00");

        List<Task> tasks = new ArrayList<>();
        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);

        taskList = new TaskList(tasks);
    }

    @Test
    public void testAddTask() {
        Task newTask = new Todo("Read book");
        int oldSize = taskList.size();
        taskList.addTask(newTask);
        assertEquals(oldSize + 1, taskList.size());
        assertTrue(taskList.getList().contains(newTask));
    }

    @Test
    public void testDeleteTask() {
        int oldSize = taskList.size();
        taskList.deleteTask(0);
        assertEquals(oldSize - 1, taskList.size());
        assertFalse(taskList.getList().contains(todo));
    }

    @Test
    public void testMarkTask() {
        taskList.markTask(0);
        assertTrue(taskList.getTask(0).isDone());
    }

    @Test
    public void testUnmarkTask() {
        taskList.markTask(0);
        taskList.unmarkTask(0);
        assertFalse(taskList.getTask(0).isDone());
    }

    @Test
    public void testFindTasks() {
        List<Task> found = taskList.findTasks("Buy");
        assertEquals(1, found.size());
        assertTrue(found.contains(todo));
    }

    @Test
    public void testGetTaskAndGetIndexOfTask() {
        assertEquals(todo, taskList.getTask(0));
        assertEquals(0, taskList.getIndexOfTask(todo));
    }

    @Test
    public void testSize() {
        assertEquals(3, taskList.size());
    }

    @Test
    public void testGetListReturnsCopy() {
        List<Task> copy = taskList.getList();
        copy.clear();
        // Original list should not be affected
        assertEquals(3, taskList.size());
    }
}
