import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import pingu.*;

public class TaskTest {
    
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Read book");
    }

    @Test
    @DisplayName("Constructor should set description and mark task as not done")
    void testConstructor() {
        assertEquals("Read book", task.getDescription());
        assertEquals(" ", task.getStatusIcon(), "New task should not be done initially");
    }

    @Test
    @DisplayName("getDescription should return the correct description")
    void testGetDescription() {
        assertEquals("Read book", task.getDescription());
        
        Task emptyTask = new Task("");
        assertEquals("", emptyTask.getDescription());
    }

    @Test
    @DisplayName("getStatusIcon should return correct icon for done and not done tasks")
    void testGetStatusIcon() {
        // Initially not done
        assertEquals(" ", task.getStatusIcon());
        
        // After marking as done
        task.toggleStatus(true);
        assertEquals("X", task.getStatusIcon());
        
        // After marking as not done
        task.toggleStatus(false);
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    @DisplayName("toggleStatus should correctly change task status")
    void testToggleStatus() {
        // Mark as done
        task.toggleStatus(true);
        assertEquals("X", task.getStatusIcon());
        
        // Mark as not done
        task.toggleStatus(false);
        assertEquals(" ", task.getStatusIcon());
        
        // Mark as done again
        task.toggleStatus(true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    @DisplayName("toFileString should return correct format for saving")
    void testToFileString() {
        // When not done
        assertEquals("T | 0 | Read book", task.toFileString());
        
        // When done
        task.toggleStatus(true);
        assertEquals("T | 1 | Read book", task.toFileString());
        
        // Test with empty description
        Task emptyTask = new Task("");
        assertEquals("T | 0 | ", emptyTask.toFileString());
    }

    @Test
    @DisplayName("toString should return correct display format")
    void testToString() {
        // When not done
        assertEquals("[T][ ] Read book", task.toString());
        
        // When done
        task.toggleStatus(true);
        assertEquals("[T][X] Read book", task.toString());
    }

    @Test
    @DisplayName("Task with empty description should work correctly")
    void testEmptyDescription() {
        Task emptyTask = new Task("");
        assertEquals("", emptyTask.getDescription());
        assertEquals(" ", emptyTask.getStatusIcon());
        assertEquals("T | 0 | ", emptyTask.toFileString());
        assertEquals("[T][ ] ", emptyTask.toString());
    }

    @Test
    @DisplayName("Task with special characters should work correctly")
    void testSpecialCharacters() {
        Task specialTask = new Task("Buy groceries @ 3pm | urgent!");
        assertEquals("Buy groceries @ 3pm | urgent!", specialTask.getDescription());
        assertEquals("T | 0 | Buy groceries @ 3pm | urgent!", specialTask.toFileString());
        assertEquals("[T][ ] Buy groceries @ 3pm | urgent!", specialTask.toString());
    }

    @Test
    @DisplayName("Task status changes should be independent")
    void testMultipleTasksIndependence() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        
        task1.toggleStatus(true);
        
        assertEquals("X", task1.getStatusIcon());
        assertEquals(" ", task2.getStatusIcon(), "Other tasks should not be affected");
    }
}
