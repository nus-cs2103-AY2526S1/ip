package application;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.Task;
import tasks.TodoTask;

public class TaskListTest {
    private TaskList taskList;
    private Task sampleTask1;
    private Task sampleTask2;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        sampleTask1 = new TodoTask("Read book");
        sampleTask2 = new TodoTask("Write code");
    }

    @Test
    public void testEmptyTaskList() {
        assertEquals(0, taskList.size());
    }

    @Test
    public void testAddTask() {
        taskList.add(sampleTask1);
        assertEquals(1, taskList.size());
        assertEquals(sampleTask1, taskList.get(0));
    }

    @Test
    public void testAddMultipleTasks() {
        taskList.add(sampleTask1);
        taskList.add(sampleTask2);
        assertEquals(2, taskList.size());
        assertEquals(sampleTask1, taskList.get(0));
        assertEquals(sampleTask2, taskList.get(1));
    }

    @Test
    public void testRemoveTask() {
        taskList.add(sampleTask1);
        taskList.add(sampleTask2);
        
        Task removedTask = taskList.remove(0);
        assertEquals(sampleTask1, removedTask);
        assertEquals(1, taskList.size());
        assertEquals(sampleTask2, taskList.get(0));
    }

    @Test
    public void testGetTaskAtValidIndex() {
        taskList.add(sampleTask1);
        assertEquals(sampleTask1, taskList.get(0));
    }

    @Test
    public void testGetTaskAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.get(0);
        });
    }

    @Test
    public void testConstructorWithArrayList() {
        ArrayList<Task> initialTasks = new ArrayList<>();
        initialTasks.add(sampleTask1);
        initialTasks.add(sampleTask2);
        
        TaskList taskListWithInitial = new TaskList(initialTasks);
        assertEquals(2, taskListWithInitial.size());
        assertEquals(sampleTask1, taskListWithInitial.get(0));
        assertEquals(sampleTask2, taskListWithInitial.get(1));
    }

    @Test
    public void testRetrieve() {
        taskList.add(sampleTask1);
        taskList.add(sampleTask2);
        
        ArrayList<Task> retrieved = taskList.retreive();
        assertEquals(2, retrieved.size());
        assertEquals(sampleTask1, retrieved.get(0));
        assertEquals(sampleTask2, retrieved.get(1));
    }
}
