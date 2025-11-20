package jobe.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    
    @Test
    public void testToString_completed() {
        Task task = new Task("test", true);
        String result = task.toString();
        assertTrue(result.contains("[X] test"));
    }
    
    @Test
    public void testToString_uncompleted() {
        Task task = new Task("test", false);
        String result = task.toString();
        assertTrue(result.contains("[ ] test"));
    }
    
    @Test
    public void testFileConversion_completed() {
        Task task = new Task("test", true);
        String result = task.convertToFileFormat();
        assertTrue(result.contains("[X] / test"));
    }
    
    @Test
    public void testFileConversion_uncompleted() {
        Task task = new Task("test", false);
        String result = task.convertToFileFormat();
        assertTrue(result.contains("[ ] / test"));
    }
    
    @Test
    public void testIsDuplicate_overlap() {
        Task task1 = new Task("test", false);
        Task task2 = new Task("test", false);
        
        assertTrue(task1.isDuplicate(task2));
    }
}
