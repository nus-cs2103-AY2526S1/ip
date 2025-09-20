package lyra.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Todo class.
 * Tests all public methods, inheritance behavior, and data formatting.
 */
public class TodoTest {
    
    private Todo todo;
    
    @BeforeEach
    void setUp() {
        todo = new Todo("Test todo description");
    }
    
    @Test
    void testConstructor() {
        assertEquals("Test todo description", todo.getDescription());
        assertFalse(todo.isDone());
    }
    
    @Test
    void testConstructorWithEmptyDescription() {
        Todo emptyTodo = new Todo("");
        assertEquals("", emptyTodo.getDescription());
        assertFalse(emptyTodo.isDone());
    }
    
    @Test
    void testConstructorWithNullDescription() {
        Todo nullTodo = new Todo(null);
        assertNull(nullTodo.getDescription());
        assertFalse(nullTodo.isDone());
    }
    
    @Test
    void testTypeCode() {
        assertEquals("T", Todo.typeCode());
    }
    
    @Test
    void testToDataString_InitiallyNotDone() {
        String expected = "T | 0 | Test todo description";
        assertEquals(expected, todo.toDataString());
    }
    
    @Test
    void testToDataString_AfterMarkingAsDone() {
        todo.markAsDone();
        String expected = "T | 1 | Test todo description";
        assertEquals(expected, todo.toDataString());
    }
    
    @Test
    void testToDataString_AfterMarkingAsNotDone() {
        todo.markAsDone();
        todo.markAsNotDone();
        String expected = "T | 0 | Test todo description";
        assertEquals(expected, todo.toDataString());
    }
    
    @Test
    void testToDataString_EmptyDescription() {
        Todo emptyTodo = new Todo("");
        String expected = "T | 0 | ";
        assertEquals(expected, emptyTodo.toDataString());
    }
    
    @Test
    void testToDataString_NullDescription() {
        Todo nullTodo = new Todo(null);
        String expected = "T | 0 | null";
        assertEquals(expected, nullTodo.toDataString());
    }
    
    @Test
    void testToString_InitiallyNotDone() {
        assertEquals("[T][ ] Test todo description", todo.toString());
    }
    
    @Test
    void testToString_AfterMarkingAsDone() {
        todo.markAsDone();
        assertEquals("[T][X] Test todo description", todo.toString());
    }
    
    @Test
    void testToString_AfterMarkingAsNotDone() {
        todo.markAsDone();
        todo.markAsNotDone();
        assertEquals("[T][ ] Test todo description", todo.toString());
    }
    
    @Test
    void testToString_EmptyDescription() {
        Todo emptyTodo = new Todo("");
        assertEquals("[T][ ] ", emptyTodo.toString());
    }
    
    @Test
    void testToString_NullDescription() {
        Todo nullTodo = new Todo(null);
        assertEquals("[T][ ] null", nullTodo.toString());
    }
    
    @Test
    void testInheritanceBehavior() {
        // Test that Todo properly inherits from Task
        assertTrue(todo instanceof Task);
        
        // Test inherited methods work correctly
        assertEquals("Test todo description", todo.getDescription());
        assertFalse(todo.isDone());
        assertEquals(" ", todo.getStatusIcon());
        
        // Test state changes
        todo.markAsDone();
        assertTrue(todo.isDone());
        assertEquals("X", todo.getStatusIcon());
        
        todo.markAsNotDone();
        assertFalse(todo.isDone());
        assertEquals(" ", todo.getStatusIcon());
    }
    
    @Test
    void testMultipleStateChanges() {
        // Test multiple state changes
        assertFalse(todo.isDone());
        assertEquals("T | 0 | Test todo description", todo.toDataString());
        assertEquals("[T][ ] Test todo description", todo.toString());
        
        todo.markAsDone();
        assertTrue(todo.isDone());
        assertEquals("T | 1 | Test todo description", todo.toDataString());
        assertEquals("[T][X] Test todo description", todo.toString());
        
        todo.markAsNotDone();
        assertFalse(todo.isDone());
        assertEquals("T | 0 | Test todo description", todo.toDataString());
        assertEquals("[T][ ] Test todo description", todo.toString());
        
        todo.markAsDone();
        assertTrue(todo.isDone());
        assertEquals("T | 1 | Test todo description", todo.toDataString());
        assertEquals("[T][X] Test todo description", todo.toString());
    }
}
