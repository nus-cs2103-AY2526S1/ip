package george.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import george.exceptions.GeorgeException;


class EventTaskTest {

    @Test
    void constructor_validDescriptionAndTimes_createsTask() throws GeorgeException {
        // Arrange & Act
        EventTask task = new EventTask("Team meeting", "2023-12-15 1400", "2023-12-15 1600");

        // Assert
        assertEquals("Team meeting", task.getDescription());
        assertFalse(task.isDone());
        assertEquals("[E]", task.getType());
        assertNotNull(task.toString());
    }

    @Test
    void constructor_withIsDoneTrue_createsDoneTask() throws GeorgeException {
        // Arrange & Act
        EventTask task = new EventTask("Birthday party", "2023-12-25 1800", "2023-12-25 2200", true);

        // Assert
        assertEquals("Birthday party", task.getDescription());
        assertTrue(task.isDone());
        assertNotNull(task.toString());
    }

    @Test
    void constructor_withIsDoneFalse_createsNotDoneTask() throws GeorgeException {
        // Arrange & Act
        EventTask task = new EventTask("Conference", "2023-12-20 0900", "2023-12-20 1700", false);

        // Assert
        assertEquals("Conference", task.getDescription());
        assertFalse(task.isDone());
        assertNotNull(task.toString());
    }

    @Test
    void constructor_nullDescription_throwsGeorgeException() {
        // Arrange, Act & Assert
        assertThrows(GeorgeException.class, () -> {
            new EventTask(null, "2023-12-15 1400", "2023-12-15 1600");
        });
    }

    @Test
    void constructor_invalidStartTimeFormat_throwsGeorgeException() {
        // Arrange, Act & Assert
        assertThrows(GeorgeException.class, () -> {
            new EventTask("Valid event", "invalid-time", "2023-12-15 1600");
        });
    }

    @Test
    void constructor_invalidEndTimeFormat_throwsGeorgeException() {
        // Arrange, Act & Assert
        assertThrows(GeorgeException.class, () -> {
            new EventTask("Valid event", "2023-12-15 1400", "invalid-time");
        });
    }

    @Test
    void getType_returnsCorrectType() throws GeorgeException {
        // Arrange
        EventTask task = new EventTask("Test event", "2023-12-15 1400", "2023-12-15 1600");

        // Act
        String type = task.getType();

        // Assert
        assertEquals("[E]", type);
    }

    @Test
    void getDisplayText_notDone_returnsCorrectFormat() throws GeorgeException {
        // Arrange
        EventTask task = new EventTask("Team meeting", "2023-12-15 1400", "2023-12-15 1600");

        // Act
        String displayText = task.getDisplayText();

        // Assert
        assertTrue(displayText.contains("[E]"));
        assertTrue(displayText.contains("[ ]"));
        assertTrue(displayText.contains("Team meeting"));
        assertTrue(displayText.contains("(from:"));
        assertTrue(displayText.contains("to:"));
        assertTrue(displayText.contains(")"));
    }

    @Test
    void getDisplayText_done_returnsCorrectFormat() throws GeorgeException {
        // Arrange
        EventTask task = new EventTask("Team meeting", "2023-12-15 1400", "2023-12-15 1600", true);

        // Act
        String displayText = task.getDisplayText();

        // Assert
        assertTrue(displayText.contains("[E]"));
        assertTrue(displayText.contains("[X]"));
        assertTrue(displayText.contains("Team meeting"));
        assertTrue(displayText.contains("(from:"));
        assertTrue(displayText.contains("to:"));
        assertTrue(displayText.contains(")"));
    }

    @Test
    void toString_notDone_returnsCorrectSerializationFormat() throws GeorgeException {
        // Arrange
        EventTask task = new EventTask("Project review", "2023-12-18 1000", "2023-12-18 1200");

        // Act
        String result = task.toString();

        // Assert
        assertEquals("E | 0 | Project review | Dec 18 2023 10:00 | Dec 18 2023 12:00", result);
    }

    @Test
    void toString_done_returnsCorrectSerializationFormat() throws GeorgeException {
        // Arrange
        EventTask task = new EventTask("Project review", "2023-12-18 1000", "2023-12-18 1200", true);

        // Act
        String result = task.toString();

        // Assert
        assertEquals("E | 1 | Project review | Dec 18 2023 10:00 | Dec 18 2023 12:00", result);
    }

    @Test
    void markAsDone_changesStatusToDone() throws GeorgeException {
        // Arrange
        EventTask task = new EventTask("Workshop", "2023-12-10 0900", "2023-12-10 1700");

        // Act
        task.markAsDone();

        // Assert
        assertTrue(task.isDone());
        assertEquals("[X]", task.getStatus());
    }

    @Test
    void markAsNotDone_changesStatusToNotDone() throws GeorgeException {
        // Arrange
        EventTask task = new EventTask("Workshop", "2023-12-10 0900", "2023-12-10 1700", true);

        // Act
        task.markAsNotDone();

        // Assert
        assertFalse(task.isDone());
        assertEquals("[ ]", task.getStatus());
    }

    @Test
    void getStatus_afterMarkingDone_returnsDoneStatus() throws GeorgeException {
        // Arrange
        EventTask task = new EventTask("Seminar", "2023-12-12 1300", "2023-12-12 1500");

        // Act
        task.markAsDone();
        String status = task.getStatus();

        // Assert
        assertEquals("[X]", status);
    }

    @Test
    void getStatus_afterMarkingNotDone_returnsNotDoneStatus() throws GeorgeException {
        // Arrange
        EventTask task = new EventTask("Seminar", "2023-12-12 1300", "2023-12-12 1500", true);

        // Act
        task.markAsNotDone();
        String status = task.getStatus();

        // Assert
        assertEquals("[ ]", status);
    }

    @Test
    void differentTimeFormats_parsedCorrectly() throws GeorgeException {
        // Test various valid time formats
        String[][] testTimePairs = {
                {"2023-12-15 0900", "2023-12-15 1700"},
                {"2023-12-31 1400", "2023-12-31 1600"},
                {"2024-01-01 0000", "2024-01-01 2359"},
                {"2023-06-15 0830", "2023-06-15 1730"}
        };

        for (String[] times : testTimePairs) {
            EventTask task = new EventTask("Test event", times[0], times[1]);

            // Test that the task was created successfully (no exception)
            assertNotNull(task);

            // Test that toString doesn't throw exception and returns something
            String result = task.toString();
            assertNotNull(result);
            assertFalse(result.isEmpty());

            // Test that the result follows the expected pattern
            assertTrue(result.matches("E \\| [01] \\| Test event \\| "
                    + "[A-Za-z]{3} \\d{1,2} \\d{4} \\d{1,2}:\\d{2} \\| "
                    + "[A-Za-z]{3} \\d{1,2} \\d{4} \\d{1,2}:\\d{2}"));
        }
    }

    @Test
    void equals_sameDescriptionAndTimes_returnsSameDisplay() throws GeorgeException {
        // Arrange
        EventTask task1 = new EventTask("Same event", "2023-12-15 1400", "2023-12-15 1600", true);
        EventTask task2 = new EventTask("Same event", "2023-12-15 1400", "2023-12-15 1600", true);

        // Act & Assert
        assertEquals(task1.toString(), task2.toString());
        assertEquals(task1.getDisplayText(), task2.getDisplayText());
    }

    @Test
    void equals_differentStartTime_returnsDifferentDisplay() throws GeorgeException {
        // Arrange
        EventTask task1 = new EventTask("Same event", "2023-12-15 1400", "2023-12-15 1600");
        EventTask task2 = new EventTask("Same event", "2023-12-15 1500", "2023-12-15 1600");

        // Act & Assert
        assertNotEquals(task1.getDisplayText(), task2.getDisplayText());
        assertNotEquals(task1.toString(), task2.toString());
    }

    @Test
    void equals_differentEndTime_returnsDifferentDisplay() throws GeorgeException {
        // Arrange
        EventTask task1 = new EventTask("Same event", "2023-12-15 1400", "2023-12-15 1600");
        EventTask task2 = new EventTask("Same event", "2023-12-15 1400", "2023-12-15 1700");

        // Act & Assert
        assertNotEquals(task1.getDisplayText(), task2.getDisplayText());
        assertNotEquals(task1.toString(), task2.toString());
    }

    @Test
    void eventWithSameStartEndTime_createsSuccessfully() throws GeorgeException {
        // Arrange & Act - Edge case: start and end time are the same
        EventTask task = new EventTask("Quick call", "2023-12-15 1500", "2023-12-15 1500");

        // Assert
        assertEquals("Quick call", task.getDescription());
        assertNotNull(task.toString());

        // Check for the formatted time output (e.g., "Dec 15 2023 15:00")
        assertTrue(task.toString().contains("Dec 15 2023 15:00"));
        // Or check that both times are the same in the output
        assertTrue(task.toString().contains("15:00 | Dec 15 2023 15:00"));
    }
}
