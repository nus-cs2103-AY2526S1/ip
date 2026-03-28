package nacho.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testAddAndRetrieveTasks() {
        // Fresh Task List
        TaskList taskList = new TaskList("");

        // Add different types of tasks
        TodoTask todo = new TodoTask("Buy groceries");
        DeadlineTask deadline = new DeadlineTask("Submit report", "10/10/2025-23:59");
        EventTask event = new EventTask("Team Meeting", "15/10/2025-14:00", "15/10/2025-15:00");

        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        // Verify tasks are added correctly
        assertEquals(3, taskList.getTotalTasks());
        assertEquals(todo, taskList.getTask(0));
        assertEquals(deadline, taskList.getTask(1));
        assertEquals(event, taskList.getTask(2));
    }

    @Test
    public void testDeleteTasks() {
        // Fresh Task List
        TaskList taskList = new TaskList("");

        // Add tasks
        TodoTask todo1 = new TodoTask("Buy groceries");
        TodoTask todo2 = new TodoTask("Walk the dog");
        taskList.addTask(todo1);
        taskList.addTask(todo2);

        // Check initial count and order
        assertEquals(2, taskList.getTotalTasks());
        assertEquals(todo1, taskList.getTask(0));

        // Delete first task
        taskList.deleteTask(0);
        assertEquals(1, taskList.getTotalTasks());
        assertEquals(todo2, taskList.getTask(0)); // Now the second task should be first
    }

    @Test
    public void testStorageFormatIngestion() {
        // Simulate storage input
        String storageInput = "T | 0 | Buy groceries\n"
                              + "D | 1 | Submit report | 10/10/2025-23:59\n"
                              + "E | 0 | Team Meeting | 15/10/2025-14:00 | 15/10/2025-15:00";

        TaskList taskList = new TaskList(storageInput);

        // Verify tasks are loaded correctly
        assertEquals(3, taskList.getTotalTasks());

        Task firstTask = taskList.getTask(0);
        assertEquals("Buy groceries", firstTask.getTitle());
        assertEquals(0, firstTask.isCompleted());

        Task secondTask = taskList.getTask(1);
        assertEquals("Submit report", secondTask.getTitle());
        assertEquals(1, secondTask.isCompleted());

        Task thirdTask = taskList.getTask(2);
        assertEquals("Team Meeting", thirdTask.getTitle());
        assertEquals(0, thirdTask.isCompleted());
    }

    @Test
    public void testSortByTitle() {
        String storageInput = "T | 0 | Orange\n"
                              + "T | 0 | Pear\n"
                              + "T | 0 | Banana\n"
                              + "T | 0 | Apple";
        TaskList taskList = new TaskList(storageInput);

        // Sort tasks by title
        taskList.sortTaskByTitle();

        // Verify the order is correct
        assertEquals("Apple", taskList.getTask(0).getTitle());
        assertEquals("Banana", taskList.getTask(1).getTitle());
        assertEquals("Orange", taskList.getTask(2).getTitle());
        assertEquals("Pear", taskList.getTask(3).getTitle());
    }

    @Test
    public void testStringOutput() {
        String storageInput = "T | 0 | Buy groceries\n"
                              + "D | 1 | Submit report | 10/10/2025-23:59\n"
                              + "E | 0 | Team Meeting | Tomorrow | Next Monday";
        TaskList taskList = new TaskList(storageInput);

        String expectedOutput = "1. [T][ ] Buy groceries\n"
                                + "2. [D][X] Submit report (by: 10 October 2025 - 11:59 PM)\n"
                                + "3. [E][ ] Team Meeting (from: Tomorrow to: Next Monday)\n";

        assertEquals(expectedOutput, taskList.toString());
    }
}
