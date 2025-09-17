package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.exception.InvalidInputException;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(null); // Using null storage for testing
    }

    // Basic functionality tests
    @Test
    public void add_singleTask_increasesSize() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        assertTrue(taskList.add(task));
        assertEquals(1, taskList.size());
    }

    @Test
    public void add_multipleTasks_increasesSize() throws InvalidInputException {
        Task task1 = Task.createFromString("todo read book");
        Task task2 = Task.createFromString("deadline submit report /by 01/01/2025 1200");
        
        taskList.add(task1);
        taskList.add(task2);
        
        assertEquals(2, taskList.size());
    }

    @Test
    public void isEmpty_newTaskList_returnsTrue() {
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void isEmpty_taskListWithTasks_returnsFalse() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        taskList.add(task);
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void size_emptyTaskList_returnsZero() {
        assertEquals(0, taskList.size());
    }

    @Test
    public void get_validIndex_returnsCorrectTask() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        taskList.add(task);
        
        Task retrieved = taskList.get(0);
        assertEquals(task, retrieved);
    }

    // Task manipulation tests
    @Test
    public void markDone_validIndex_marksTaskAsDone() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        taskList.add(task);
        
        Task markedTask = taskList.markDone(0);
        
        assertTrue(markedTask.isDone());
        assertTrue(taskList.get(0).isDone());
    }

    @Test
    public void unmarkDone_validIndex_unmarksTask() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        task.markDone(); // Mark it first
        taskList.add(task);
        
        Task unmarkedTask = taskList.unmarkDone(0);
        
        assertFalse(unmarkedTask.isDone());
        assertFalse(taskList.get(0).isDone());
    }

    @Test
    public void remove_validIndex_removesTaskAndDecreasesSize() throws InvalidInputException {
        Task task1 = Task.createFromString("todo read book");
        Task task2 = Task.createFromString("todo write code");
        taskList.add(task1);
        taskList.add(task2);
        
        Task removedTask = taskList.remove(0);
        
        assertEquals(task1, removedTask);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0)); // task2 should now be at index 0
    }

    // Index validation tests
    @Test
    public void isValidTaskIndex_validIndex_returnsTrue() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        taskList.add(task);
        
        assertTrue(taskList.isValidTaskIndex(0));
    }

    @Test
    public void isValidTaskIndex_negativeIndex_returnsFalse() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        taskList.add(task);
        
        assertFalse(taskList.isValidTaskIndex(-1));
    }

    @Test
    public void isValidTaskIndex_indexTooLarge_returnsFalse() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        taskList.add(task);
        
        assertFalse(taskList.isValidTaskIndex(1)); // Only index 0 is valid
    }

    @Test
    public void isValidTaskIndex_emptyTaskList_returnsFalse() {
        assertFalse(taskList.isValidTaskIndex(0));
    }

    // String representation tests
    @Test
    public void stringifyTasks_emptyTaskList_returnsNoTasksMessage() {
        assertEquals("No tasks in list.", taskList.stringifyTasks());
    }

    @Test
    public void stringifyTasks_singleTask_returnsFormattedTask() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        taskList.add(task);
        
        String expected = "\n\t1. [T][ ] read book";
        assertEquals(expected, taskList.stringifyTasks());
    }

    @Test
    public void stringifyTasks_multipleTasks_returnsAllTasksFormatted() throws InvalidInputException {
        Task task1 = Task.createFromString("todo read book");
        Task task2 = Task.createFromString("deadline submit report /by 01/01/2025 1200");
        taskList.add(task1);
        taskList.add(task2);
        
        String result = taskList.stringifyTasks();
        assertTrue(result.contains("1. [T][ ] read book"));
        assertTrue(result.contains("2. [D][ ] submit report"));
    }

    @Test
    public void stringifyTasks_staticMethodWithEmptyList_returnsNoTasksMessage() {
        assertEquals("No tasks in list.", TaskList.stringifyTasks(List.of()));
    }

    @Test
    public void stringifyTasks_staticMethodWithTasks_returnsFormattedTasks() throws InvalidInputException {
        Task task1 = Task.createFromString("todo read book");
        Task task2 = Task.createFromString("todo write code");
        List<Task> tasks = List.of(task1, task2);
        
        String result = TaskList.stringifyTasks(tasks);
        assertTrue(result.contains("1. [T][ ] read book"));
        assertTrue(result.contains("2. [T][ ] write code"));
    }

    // Filtering tests
    @Test
    public void filterTasksByKeyword_matchingKeyword_returnsMatchingTasks() throws InvalidInputException {
        Task task1 = Task.createFromString("todo read book");
        Task task2 = Task.createFromString("todo write code");
        Task task3 = Task.createFromString("todo read article");
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        
        List<Task> filtered = TaskList.filterTasksByKeyword(taskList.getTasks(), "read");
        
        assertEquals(2, filtered.size());
        assertTrue(filtered.contains(task1));
        assertTrue(filtered.contains(task3));
        assertFalse(filtered.contains(task2));
    }

    @Test
    public void filterTasksByKeyword_noMatchingKeyword_returnsEmptyList() throws InvalidInputException {
        Task task1 = Task.createFromString("todo read book");
        Task task2 = Task.createFromString("todo write code");
        taskList.add(task1);
        taskList.add(task2);
        
        List<Task> filtered = TaskList.filterTasksByKeyword(taskList.getTasks(), "homework");
        
        assertEquals(0, filtered.size());
    }

    @Test
    public void filterTasksByKeyword_emptyKeyword_returnsAllTasks() throws InvalidInputException {
        Task task1 = Task.createFromString("todo read book");
        Task task2 = Task.createFromString("todo write code");
        taskList.add(task1);
        taskList.add(task2);
        
        List<Task> filtered = TaskList.filterTasksByKeyword(taskList.getTasks(), "");
        
        assertEquals(2, filtered.size());
    }

    // Storage string tests
    @Test
    public void toStorageString_emptyTaskList_returnsEmptyString() {
        assertEquals("", taskList.toStorageString());
    }

    @Test
    public void toStorageString_taskListWithUnmarkedTask_returnsCorrectFormat() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        taskList.add(task);
        
        String expected = "0 todo read book\n";
        assertEquals(expected, taskList.toStorageString());
    }

    @Test
    public void toStorageString_taskListWithMarkedTask_returnsCorrectFormat() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        task.markDone();
        taskList.add(task);
        
        String expected = "1 todo read book\n";
        assertEquals(expected, taskList.toStorageString());
    }

    @Test
    public void toStorageString_taskListWithMixedTasks_returnsCorrectFormat() throws InvalidInputException {
        Task task1 = Task.createFromString("todo read book");
        Task task2 = Task.createFromString("deadline submit report /by 01/01/2025 1200");
        task1.markDone();
        taskList.add(task1);
        taskList.add(task2);
        
        String result = taskList.toStorageString();
        assertTrue(result.contains("1 todo read book"));
        assertTrue(result.contains("0 deadline submit report /by 01/01/2025 1200"));
    }

    // toString tests
    @Test
    public void toString_delegatesToStringifyTasks() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        taskList.add(task);
        
        assertEquals(taskList.stringifyTasks(), taskList.toString());
    }
}
