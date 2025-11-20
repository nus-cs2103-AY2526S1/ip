package jobe.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;

public class TodoTaskTest {
    
    /**
     * Test that the TodoTask toString() returns the correct prefix for a completed jobe.task.
     * <p>
     * Specifically, we only test that it starts with "[X]".
     * The rest of the string is verified in the superclass tests.
     * </p>
     */
    @Test
    public void testToString_includesCorrectPrefix() {
        TodoTask todo = new TodoTask("test");
        String result = todo.toString();
        assertTrue(result.startsWith("[T]"));
    }
    
    @Test
    public void testFileConversion_includesCorrectPrefix() {
        TodoTask todo = new TodoTask("test");
        String result = todo.convertToFileFormat();
        assertTrue(result.startsWith("T / "));
    }
    
    @Test
    public void testIsDuplicate_overlap() throws JobeException {
        TodoTask todo1 = new TodoTask("test");
        TodoTask todo2 = new TodoTask("test");
        assertTrue(todo1.isDuplicate(todo2));
    }
}
