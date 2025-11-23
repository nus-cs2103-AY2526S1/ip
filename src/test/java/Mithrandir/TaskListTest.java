package Mithrandir;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mithrandir.task.Task;
import Mithrandir.task.Todo;

public class TaskListTest {
    private TaskList taskList;
    private Task testTask1;
    private Task testTask2;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        testTask1 = new Todo("Test task 1");
        testTask2 = new Todo("Test task 2");
    }

    @Test
    void TaskList_addTask_validTask_increasesSize() {
        taskList.addTask(testTask1);
        assertEquals(1, taskList.getSize());
    }

    @Test
    void TaskList_getTask_validIndex_returnsCorrectTask() {
        taskList.addTask(testTask1);
        assertEquals(testTask1, taskList.getTask(0));
    }

    @Test
    void TaskList_Mark_validIndex_marksTaskAsDone() {
        taskList.addTask(testTask1);
        taskList.Mark(0);
        assertTrue(testTask1.isMarked());
    }

    @Test
    void TaskList_Unmark_markedTask_marksTaskAsUndone() {
        taskList.addTask(testTask1);
        taskList.Mark(0);
        taskList.Unmark(0);
        assertFalse(testTask1.isMarked());
    }

    @Test
    void TaskList_DeleteTask_validIndex_removesTask() {
        taskList.addTask(testTask1);
        taskList.addTask(testTask2);
        Task deletedTask = taskList.DeleteTask(0);
        assertEquals(testTask1, deletedTask);
        assertEquals(1, taskList.getSize());
        assertEquals(testTask2, taskList.getTask(0));
    }

    @Test
    void TaskList_generateFileStrings_emptyList_returnsEmptyString() {
        assertEquals("", taskList.generateFileStrings().trim());
    }

    @Test
    void TaskList_generateFileStrings_withTasks_returnsCorrectFormat() {
        taskList.addTask(testTask1);
        taskList.addTask(testTask2);
        String expected = String.format("TODO || undone || %s%nTODO || undone || %s",
                testTask1.getDescription(), testTask2.getDescription());
        assertEquals(expected, taskList.generateFileStrings().trim());
    }

    @Test
    void TaskList_findTasks_matchingKeyword_returnsMatchingTasks() {
        taskList.addTask(new Todo("Buy groceries"));
        taskList.addTask(new Todo("Do laundry"));
        taskList.addTask(new Todo("Buy new shoes"));

        TaskList foundTasks = taskList.findTasks("buy");
        assertEquals(2, foundTasks.getSize());
        assertTrue(foundTasks.toString().contains("Buy groceries"));
        assertTrue(foundTasks.toString().contains("Buy new shoes"));
    }

    @Test
    void TaskList_findTasks_noMatchingKeyword_returnsEmptyList() {
        taskList.addTask(new Todo("Buy groceries"));
        TaskList foundTasks = taskList.findTasks("nonexistent");
        assertEquals(0, foundTasks.getSize());
    }

    @Test
    void TaskList_getSize_emptyList_returnsZero() {
        assertEquals(0, taskList.getSize());
    }

    @Test
    void TaskList_getSize_afterAddingTasks_returnsCorrectCount() {
        taskList.addTask(testTask1);
        taskList.addTask(testTask2);
        assertEquals(2, taskList.getSize());
    }

    @Test
    void TaskList_getTasks_returnsAllTasks() {
        taskList.addTask(testTask1);
        taskList.addTask(testTask2);
        assertEquals(2, taskList.getTasks().size());
        assertTrue(taskList.getTasks().contains(testTask1));
        assertTrue(taskList.getTasks().contains(testTask2));
    }

    @Test
    void TaskList_toString_emptyList_returnsEmptyMessage() {
        assertEquals("", taskList.toString().trim());
    }

    @Test
    void TaskList_toString_withTasks_returnsNumberedList() {
        taskList.addTask(testTask1);
        taskList.addTask(testTask2);
        String expected = String.format("1. %s\n2. %s",
                testTask1.toString(), testTask2.toString());
        assertEquals(expected, taskList.toString().trim());
    }

    @Test
    void TaskList_getTask_invalidIndex_throwsException() {
        taskList.addTask(testTask1);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(1));
    }

    @Test
    void TaskList_Mark_invalidIndex_throwsException() {
        taskList.addTask(testTask1);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.Mark(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.Mark(1));
    }

    @Test
    void TaskList_Unmark_invalidIndex_throwsException() {
        taskList.addTask(testTask1);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.Unmark(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.Unmark(1));
    }

    @Test
    void TaskList_DeleteTask_invalidIndex_throwsException() {
        taskList.addTask(testTask1);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.DeleteTask(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.DeleteTask(1));
    }
}