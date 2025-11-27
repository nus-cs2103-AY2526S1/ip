package mikey.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

//Claude AI was used for implementing these tests
class TaskTest {
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Todo("Test task");
    }

    @Test
    @DisplayName("New task should be incomplete")
    void testNewTaskIncomplete() {
        assertFalse(task.isDone);
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    @DisplayName("Should mark task as done")
    void testMarkDone() {
        task.markDone();
        assertTrue(task.isDone);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    @DisplayName("Should mark task as undone")
    void testMarkUndone() {
        task.markDone();
        task.markUndone();
        assertFalse(task.isDone);
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    @DisplayName("Should handle tagging")
    void testTagging() {
        assertFalse(task.isTagged());
        task.setTag("important");
        assertTrue(task.isTagged());
        assertTrue(task.toString().contains("tag: important"));
    }

    @Test
    @DisplayName("Should return correct description")
    void testGetDescription() {
        assertEquals("Test task", task.getDescription());
    }
}