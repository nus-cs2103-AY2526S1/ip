package mon.task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {
    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        task1 = new Todo("Task 1");
        task2 = new Todo("Task 2");
        taskList = new TaskList();
    }

    @Test
    void testEmptyTaskListCreation() {
        assertEquals(0, taskList.size());
        assertTrue(taskList.getTaskList().isEmpty());
    }

    @Test
    void testTaskListCreationWithArrayList() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        
        TaskList taskListWithTasks = new TaskList(tasks);
        assertEquals(2, taskListWithTasks.size());
        assertEquals(task1, taskListWithTasks.getTask(0));
        assertEquals(task2, taskListWithTasks.getTask(1));
    }

    @Test
    void testAddTask() {
        taskList.addTask(task1);
        assertEquals(1, taskList.size());
        assertEquals(task1, taskList.getTask(0));
        
        taskList.addTask(task2);
        assertEquals(2, taskList.size());
        assertEquals(task2, taskList.getTask(1));
    }

    @Test
    void testRemoveTask() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(2, taskList.size());
        
        taskList.removeTask(0);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.getTask(0));
        
        taskList.removeTask(0);
        assertEquals(0, taskList.size());
    }

    @Test
    void testGetTask() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        
        assertEquals(task1, taskList.getTask(0));
        assertEquals(task2, taskList.getTask(1));
    }

    @Test
    void testGetTaskList() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        
        ArrayList<Task> retrievedTasks = taskList.getTaskList();
        assertEquals(2, retrievedTasks.size());
        assertEquals(task1, retrievedTasks.get(0));
        assertEquals(task2, retrievedTasks.get(1));
    }

    @Test
    void testSize() {
        assertEquals(0, taskList.size());
        
        taskList.addTask(task1);
        assertEquals(1, taskList.size());
        
        taskList.addTask(task2);
        assertEquals(2, taskList.size());
        
        taskList.removeTask(0);
        assertEquals(1, taskList.size());
    }
}
