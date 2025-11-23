package stewie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link EventTask}.
 */
class EventTaskTest {

    @Test
    void toFileFormat_validEvent_correctFormat() {
        EventTask task = new EventTask("Meeting",
                LocalDateTime.of(2025, 9, 21, 10, 0),
                LocalDateTime.of(2025, 9, 21, 12, 0));

        String expected = "E | 0 | Meeting | 21/09/2025 10:00 | 21/09/2025 12:00";
        assertEquals(expected, task.toFileFormat());
    }

    @Test
    void getDescription_validEvent_correctFormat() {
        EventTask task = new EventTask("Meeting",
                LocalDateTime.of(2025, 9, 21, 10, 0),
                LocalDateTime.of(2025, 9, 21, 12, 0));

        String expected = "[E][ ] Meeting (from: 21 Sep 2025 10:00 to: 21 Sep 2025 12:00)";
        assertEquals(expected, task.getDescription());
    }
}
