package jobe.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;

public class DeadlineTaskTest {
    
    /**
     * Test that the DeadlineTask toString() returns the correct prefix for a completed jobe.task.
     * <p>
     * Specifically, we only test that it starts with "[X]".
     * The rest of the string is verified in the superclass tests.
     * </p>
     */
    @Test
    public void testToString_includesCorrectPrefix() throws JobeException {
        DeadlineTask deadline = DeadlineTask.createDeadlineTask("test",
                "by 24/08/2024 2222"
        );
        String result = deadline.toString();
        assertTrue(result.startsWith("[D]"));
    }
    
    @Test
    public void testToString_includesCorrectSuffix() throws JobeException {
        DeadlineTask deadline = DeadlineTask.createDeadlineTask("test",
                "by 24/08/2024 2222"
        );
        String result = deadline.toString();
        assertTrue(result.endsWith("(by: Aug 24 2024, 22:22)"));
    }
    
    
    @Test
    public void testFileConversion_includesCorrectPrefix() throws JobeException {
        DeadlineTask deadline = DeadlineTask.createDeadlineTask("test",
                "by 24/08/2024 2222"
        );
        String result = deadline.convertToFileFormat();
        assertTrue(result.startsWith("D / "));
    }
    
    @Test
    public void testFileConversion_includesCorrectSuffix() throws JobeException {
        DeadlineTask deadline = DeadlineTask.createDeadlineTask("test",
                "by 24/08/2024 2222"
        );
        String result = deadline.convertToFileFormat();
        assertTrue(result.endsWith(" / Aug 24 2024, 22:22"));
    }
    
    @Test
    public void testIsDuplicate_overlap() throws JobeException {
        DeadlineTask deadline1 = DeadlineTask.createDeadlineTask("test",
                "by 24/08/2024 2222"
        );
        DeadlineTask deadline2 = DeadlineTask.createDeadlineTask("test",
                "by 24/08/2024 2222"
        );
        assertTrue(deadline1.isDuplicate(deadline2));
    }
}
