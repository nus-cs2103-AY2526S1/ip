package peanutbutter.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import peanutbutter.exceptions.JuinException;

public class TaskListTest {
    @Test
    public void deleteTaskSuccess() throws JuinException {
        TaskList testList = new TaskList(new ArrayList<>());
        Task task = new Todo("Test");
        testList.addTask(task);

        testList.deleteTask(0);
        assertEquals(0, testList.size());
    }

    @Test
    public void addTaskSuccess() throws JuinException {
        TaskList testList = new TaskList(new ArrayList<>());
        Task task = new Todo("Test");
        testList.addTask(task);

        assertEquals(1, testList.size());
        assertEquals("Test", testList.get(0).getDescription());
    }

    @Test
    public void markTaskSuccess() throws JuinException {
        TaskList testList = new TaskList(new ArrayList<>());
        Task task = new Todo("Test");
        testList.addTask(task);

        testList.markTaskDone(0);
        System.out.println(testList.get(0).isDone());
        assertEquals(true, testList.get(0).isDone());
    }
}
