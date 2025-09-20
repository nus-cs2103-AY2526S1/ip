package lyra.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Task class.
 * Tests all public methods and edge cases.
 */
public class TaskTest {
    
    private Task task;
    
    @BeforeEach
    void setUp() {
        task = new Task("Test task description");
    }
    
    @Test
    void testConstructor() {
        assertEquals("Test task description", task.getDescription());
        assertFalse(task.isDone());
    }
    
    @Test
    void testConstructorWithEmptyDescription() {
        Task emptyTask = new Task("");
        assertEquals("", emptyTask.getDescription());
        assertFalse(emptyTask.isDone());
    }
    
    @Test
    void testConstructorWithNullDescription() {
        Task nullTask = new Task(null);
        assertNull(nullTask.getDescription());
        assertFalse(nullTask.isDone());
    }
    
    @Test
    void testGetStatusIcon_InitiallyNotDone() {
        assertEquals(" ", task.getStatusIcon());
    }
    
    @Test
    void testGetStatusIcon_AfterMarkingAsDone() {
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }
    
    @Test
    void testGetStatusIcon_AfterMarkingAsNotDone() {
        task.markAsDone();
        task.markAsNotDone();
        assertEquals(" ", task.getStatusIcon());
    }
    
    @Test
    void testIsDone_Initially() {
        assertFalse(task.isDone());
    }
    
    @Test
    void testIsDone_AfterMarkingAsDone() {
        task.markAsDone();
        assertTrue(task.isDone());
    }
    
    @Test
    void testIsDone_AfterMarkingAsNotDone() {
        task.markAsDone();
        task.markAsNotDone();
        assertFalse(task.isDone());
    }
    
    @Test
    void testGetDescription() {
        assertEquals("Test task description", task.getDescription());
    }
    
    @Test
    void testMarkAsDone() {
        task.markAsDone();
        assertTrue(task.isDone());
        assertEquals("X", task.getStatusIcon());
    }
    
    @Test
    void testMarkAsNotDone() {
        task.markAsDone();
        task.markAsNotDone();
        assertFalse(task.isDone());
        assertEquals(" ", task.getStatusIcon());
    }
    
    @Test
    void testToString_InitiallyNotDone() {
        assertEquals("[ ] Test task description", task.toString());
    }
    
    @Test
    void testToString_AfterMarkingAsDone() {
        task.markAsDone();
        assertEquals("[X] Test task description", task.toString());
    }
    
    @Test
    void testToString_AfterMarkingAsNotDone() {
        task.markAsDone();
        task.markAsNotDone();
        assertEquals("[ ] Test task description", task.toString());
    }
    
    @Test
    void testMultipleStateChanges() {
        // Test multiple state changes
        assertFalse(task.isDone());
        assertEquals(" ", task.getStatusIcon());
        
        task.markAsDone();
        assertTrue(task.isDone());
        assertEquals("X", task.getStatusIcon());
        
        task.markAsNotDone();
        assertFalse(task.isDone());
        assertEquals(" ", task.getStatusIcon());
        
        task.markAsDone();
        assertTrue(task.isDone());
        assertEquals("X", task.getStatusIcon());
    }
}
