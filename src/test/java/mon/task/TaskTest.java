package mon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    void testTaskCreation() {
        Task task = new Task("Test task");
        assertEquals("Test task", task.getTaskName());
        assertFalse(task.getStatus());
    }

    @Test
    void testTaskCreationWithStatus() {
        Task task = new Task("Test task", true);
        assertEquals("Test task", task.getTaskName());
        assertTrue(task.getStatus());
    }

    @Test
    void testSetStatus() {
        Task task = new Task("Test task");
        assertFalse(task.getStatus());
        
        task.setStatus(true);
        assertTrue(task.getStatus());
        
        task.setStatus(false);
        assertFalse(task.getStatus());
    }

    @Test
    void testToFileString() {
        Task task = new Task("Test task");
        assertEquals("0 | Test task", task.toFileString());
        
        task.setStatus(true);
        assertEquals("1 | Test task", task.toFileString());
    }

    @Test
    void testToString() {
        Task task = new Task("Test task");
        assertEquals("[ ] Test task", task.toString());
        
        task.setStatus(true);
        assertEquals("[X] Test task", task.toString());
    }

    @Test
    void testToTask() {
        String taskString = "T | 1 | Read book";
        Task task = Task.toTask(taskString);
        assertEquals("Read book", task.getTaskName());
        assertTrue(task.getStatus());
    }

    @Test
    void testConvertFileFormatToStandardDate() {
        String fileDate = "Dec 3 2017";
        String standardDate = Task.convertFileFormatToStandardDate(fileDate);
        assertEquals("2017-12-03", standardDate);
    }

    @Test
    void testConvertStandardToFileFormatDate() {
        String standardDate = "2017-12-03";
        String fileDate = Task.convertStandardToFileFormatDate(standardDate);
        assertEquals("Dec 3 2017", fileDate);
    }

    @Test
    void testConvertFileFormatToStandardDateInvalidFormat() {
        String invalidDate = "invalid-date";
        assertThrows(IllegalArgumentException.class, () -> {
            Task.convertFileFormatToStandardDate(invalidDate);
        });
    }

    @Test
    void testConvertStandardToFileFormatDateInvalidFormat() {
        String invalidDate = "invalid-date";
        assertThrows(IllegalArgumentException.class, () -> {
            Task.convertStandardToFileFormatDate(invalidDate);
        });
    }
}
