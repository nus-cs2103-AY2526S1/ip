import org.junit.jupiter.api.Test;

import thopz.Deadline;
import thopz.Events;
import thopz.Task;
import thopz.TaskList;
import thopz.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Task test with junit test cases
 * Test whether todo task can be saved
 * Test deadline class with date time format
 * check whether delete works
 */

class TaskTest {

    @Test
    void testTodotask() {
        Task task = new Todo("Test_todo");
        task.markTask();
        assertEquals("Test_todo", task.getTasks());
        assertTrue(task.getTaskStatus());
    }

    @Test
    void testDeadlineTask() {
        Task task = new Deadline("test_time", "04/09/2025 21:56");
        assertEquals("[D][ ] test_time (due by Sep 04 2025, 21:56)", task.toString());
        assertFalse(task.getTaskStatus());
    }

    @Test
    void addTest() {
        TaskList ls = new TaskList();
        Task task1 = new Todo("test1");
        Task task2 = new Deadline("test2", "12/12/2025 19:20");
        Task task3 = new Events("test3", "12/12/2024 19:20", "12-12-2025 19:25");
        ls.addTask(task1);
        ls.addTask(task2);
        assertEquals(2, ls.size());
        ls.addTask(task3);
        assertEquals(3., ls.size());
    }

    @Test
    void deleteTest() {
        TaskList ls = new TaskList();
        Task task1 = new Todo("test1");
        Task task2 = new Deadline("test2", "12/12/2025 19:20");
        Task task3 = new Events("test3", "12/12/2024 19:20", "12-12-2025 19:25");
        ls.addTask(task1);
        ls.addTask(task2);
        ls.addTask(task3);
        assertEquals(3, ls.size());
        ls.removeTask(2);
        assertEquals(2, ls.size());
        assertEquals("test1", ls.getTask(1).getTasks());
        assertEquals("test3", ls.getTask(2).getTasks());
    }
    @Test
    void toString_shouldShowUncheckedBoxWhenNotDone() {
        Task task = new Task("Read book");
        assertEquals("[ ] Read book", task.toString());
    }

    @Test
    void toString_shouldShowCheckedBoxWhenDone() {
        Task task = new Task("Submit assignment");
        task.markTask();
        assertEquals("[x] Submit assignment", task.toString());
    }

    @Test
    void saveFormat_shouldReturnNILByDefault() {
        Task task = new Task("Dummy task");
        assertEquals("NIL", task.saveformat(), "Default save format should be NIL");
    }

    @Test
    void markAndUnmark_shouldToggleCorrectly() {
        Task task = new Task("Go jogging");
        assertFalse(task.getTaskStatus());
        task.markTask();
        assertTrue(task.getTaskStatus());
        task.unmarkTask();
        assertFalse(task.getTaskStatus());
    }

    @Test
    void constructor_shouldThrowAssertionErrorIfNullDescription() {
        assertThrows(AssertionError.class, () -> new Task(null));
    }
}


