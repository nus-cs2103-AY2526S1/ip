package mikey.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Claude AI was used for implementing these tests
class TaskListTest {
    private TaskList taskList;
    private Todo todo;
    private Deadline deadline;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(null);
        todo = new Todo("Buy milk");
        deadline = new Deadline("Assignment", LocalDateTime.of(2024, 1, 1, 23, 59));
        taskList.addTask(todo);
        taskList.addTask(deadline);
    }

    @Test
    @DisplayName("Should add tasks correctly")
    void testAddTask() {
        assertEquals(2, taskList.size());
    }

    @Test
    @DisplayName("Should mark task correctly")
    void testMarkTask() {
        Task marked = taskList.markTask(0);
        assertEquals(todo, marked);
        assertTrue(todo.isDone);
    }

    @Test
    @DisplayName("Should handle invalid mark index")
    void testMarkInvalidIndex() {
        Task result = taskList.markTask(10);
        assertNull(result);
    }

    @Test
    @DisplayName("Should unmark task correctly")
    void testUnmarkTask() {
        todo.markDone();
        Task unmarked = taskList.unmarkTask(0);
        assertEquals(todo, unmarked);
        assertFalse(todo.isDone);
    }

    @Test
    @DisplayName("Should delete task correctly")
    void testDeleteTask() {
        Task deleted = taskList.deleteTask(0);
        assertEquals(todo, deleted);
        assertEquals(1, taskList.size());
    }

    @Test
    @DisplayName("Should find tasks by keyword")
    void testFindTasks() {
        List<Task> found = taskList.findTasks("milk");
        assertEquals(1, found.size());
        assertEquals(todo, found.get(0));
    }

    @Test
    @DisplayName("Should find tasks case insensitively")
    void testFindTasksCaseInsensitive() {
        List<Task> found = taskList.findTasks("MILK");
        assertEquals(1, found.size());
    }

    @Test
    @DisplayName("Should tag task correctly")
    void testTagTask() {
        Task tagged = taskList.tagTask(0, "grocery");
        assertEquals(todo, tagged);
        assertTrue(todo.isTagged());
    }
}