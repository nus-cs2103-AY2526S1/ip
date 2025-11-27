package mikey.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

//Claude AI was used for implementing these tests
class DeadlineTest {

    @Test
    @DisplayName("Should format deadline correctly")
    void testDeadlineFormatting() {
        LocalDateTime deadline = LocalDateTime.of(2024, 1, 1, 14, 30);
        Deadline task = new Deadline("Assignment", deadline);

        String taskString = task.toString();
        assertTrue(taskString.contains("[D]"));
        assertTrue(taskString.contains("Assignment"));
        assertTrue(taskString.contains("by:"));
    }

    @Test
    @DisplayName("Should generate correct save string")
    void testDeadlineSaveString() {
        LocalDateTime deadline = LocalDateTime.of(2024, 1, 1, 14, 30);
        Deadline task = new Deadline("Assignment", deadline);

        String saveString = task.toSaveString();
        assertTrue(saveString.startsWith("D | 0 | Assignment"));
    }

    @Test
    @DisplayName("Should handle tagged deadline save string")
    void testTaggedDeadlineSaveString() {
        LocalDateTime deadline = LocalDateTime.of(2024, 1, 1, 14, 30);
        Deadline task = new Deadline("Assignment", deadline);
        task.setTag("school");

        String saveString = task.toSaveString();
        assertTrue(saveString.contains("| school"));
    }
}