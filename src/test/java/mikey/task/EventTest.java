package mikey.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

//Claude AI was used for implementing these tests
class EventTest {

    @Test
    @DisplayName("Should format event correctly")
    void testEventFormatting() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 14, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 1, 16, 0);
        Event task = new Event("Meeting", start, end);

        String taskString = task.toString();
        assertTrue(taskString.contains("[E]"));
        assertTrue(taskString.contains("Meeting"));
        assertTrue(taskString.contains("from:"));
        assertTrue(taskString.contains("to:"));
    }

    @Test
    @DisplayName("Should generate correct save string")
    void testEventSaveString() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 14, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 1, 16, 0);
        Event task = new Event("Meeting", start, end);

        String saveString = task.toSaveString();
        assertTrue(saveString.startsWith("E | 0 | Meeting"));
        // Should contain both start and end times
        String[] parts = saveString.split("\\|");
        assertEquals(5, parts.length);
    }
}