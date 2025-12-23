package george.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import george.exceptions.GeorgeException;

class DeadlineTaskTest {

    @Test
    void constructor_validDescriptionAndDeadline_createsTask() throws GeorgeException {
        // Arrange & Act
        DeadlineTask task = new DeadlineTask("Submit report", "2023-12-31 2359");

        // Assert
        assertEquals("Submit report", task.getDescription());
        assertFalse(task.isDone());
        assertEquals("[D]", task.getType());
        assertNotNull(task.getDeadline());
    }

    @Test
    void constructor_withIsDoneTrue_createsDoneTask() throws GeorgeException {
        // Arrange & Act
        DeadlineTask task = new DeadlineTask("Pay bills", "2023-12-15 1800", true);

        // Assert
        assertEquals("Pay bills", task.getDescription());
        assertTrue(task.isDone());
        assertNotNull(task.getDeadline());
    }

    @Test
    void constructor_withIsDoneFalse_createsNotDoneTask() throws GeorgeException {
        // Arrange & Act
        DeadlineTask task = new DeadlineTask("Study for exam", "2023-12-20 1400", false);

        // Assert
        assertEquals("Study for exam", task.getDescription());
        assertFalse(task.isDone());
        assertNotNull(task.getDeadline());
    }

    @Test
    void constructor_nullDescription_throwsGeorgeException() {
        // Arrange, Act & Assert
        assertThrows(GeorgeException.class, () -> {
            new DeadlineTask(null, "2023-12-31 2359");
        });
    }

    @Test
    void constructor_invalidDeadlineFormat_throwsGeorgeException() {
        // Arrange, Act & Assert
        assertThrows(GeorgeException.class, () -> {
            new DeadlineTask("Valid task", "invalid-date-format");
        });
    }

    @Test
    void getType_returnsCorrectType() throws GeorgeException {
        // Arrange
        DeadlineTask task = new DeadlineTask("Test task", "2023-12-31 2359");

        // Act
        String type = task.getType();

        // Assert
        assertEquals("[D]", type);
    }

    @Test
    void getDeadline_returnsFormattedDate() throws GeorgeException {
        // Arrange
        DeadlineTask task = new DeadlineTask("Test task", "2023-12-25 1200");

        // Act
        String deadline = task.getDeadline();

        // Assert
        assertNotNull(deadline);
        assertTrue(deadline.matches("[A-Za-z]{3} \\d{2} \\d{4}")); // Matches "MMM dd yyyy" format
    }

    @Test
    void getDisplayText_notDone_returnsCorrectFormat() throws GeorgeException {
        // Arrange
        DeadlineTask task = new DeadlineTask("Submit assignment", "2023-12-31 2359");

        // Act
        String displayText = task.getDisplayText();

        // Assert
        assertTrue(displayText.contains("[D]"));
        assertTrue(displayText.contains("[ ]"));
        assertTrue(displayText.contains("Submit assignment"));
        assertTrue(displayText.contains("(by:"));
        assertTrue(displayText.contains(")"));
    }

    @Test
    void getDisplayText_done_returnsCorrectFormat() throws GeorgeException {
        // Arrange
        DeadlineTask task = new DeadlineTask("Submit assignment", "2023-12-31 2359", true);

        // Act
        String displayText = task.getDisplayText();

        // Assert
        assertTrue(displayText.contains("[D]"));
        assertTrue(displayText.contains("[X]"));
        assertTrue(displayText.contains("Submit assignment"));
        assertTrue(displayText.contains("(by:"));
        assertTrue(displayText.contains(")"));
    }

    @Test
    void toString_notDone_returnsCorrectSerializationFormat() throws GeorgeException {
        // Arrange
        DeadlineTask task = new DeadlineTask("Finish project", "2023-12-15 1700");

        // Act
        String result = task.toString();

        // Assert
        assertEquals("D | 0 | Finish project | " + task.getDeadline(), result);
        assertTrue(result.startsWith("D | 0 | Finish project | "));
    }

    @Test
    void toString_done_returnsCorrectSerializationFormat() throws GeorgeException {
        // Arrange
        DeadlineTask task = new DeadlineTask("Finish project", "2023-12-15 1700", true);

        // Act
        String result = task.toString();

        // Assert
        assertEquals("D | 1 | Finish project | " + task.getDeadline(), result);
        assertTrue(result.startsWith("D | 1 | Finish project | "));
    }

    @Test
    void markAsDone_changesStatusToDone() throws GeorgeException {
        // Arrange
        DeadlineTask task = new DeadlineTask("Clean room", "2023-12-10 1500");

        // Act
        task.markAsDone();

        // Assert
        assertTrue(task.isDone());
        assertEquals("[X]", task.getStatus());
    }

    @Test
    void markAsNotDone_changesStatusToNotDone() throws GeorgeException {
        // Arrange
        DeadlineTask task = new DeadlineTask("Clean room", "2023-12-10 1500", true);

        // Act
        task.markAsNotDone();

        // Assert
        assertFalse(task.isDone());
        assertEquals("[ ]", task.getStatus());
    }

    @Test
    void getStatus_afterMarkingDone_returnsDoneStatus() throws GeorgeException {
        // Arrange
        DeadlineTask task = new DeadlineTask("Test task", "2023-12-31 2359");

        // Act
        task.markAsDone();
        String status = task.getStatus();

        // Assert
        assertEquals("[X]", status);
    }

    @Test
    void getStatus_afterMarkingNotDone_returnsNotDoneStatus() throws GeorgeException {
        // Arrange
        DeadlineTask task = new DeadlineTask("Test task", "2023-12-31 2359", true);

        // Act
        task.markAsNotDone();
        String status = task.getStatus();

        // Assert
        assertEquals("[ ]", status);
    }

    @Test
    void differentDeadlineFormats_parsedCorrectly() throws GeorgeException {
        // Test various valid date formats
        String[] testDates = {
            "2023-12-31 2359",
            "2023-12-31 1200",
            "2023-01-01 0000",
            "2023-06-15 1430"
        };

        for (String date : testDates) {
            DeadlineTask task = new DeadlineTask("Test with " + date, date);
            assertNotNull(task.getDeadline());
            assertTrue(task.getDeadline().matches("[A-Za-z]{3} \\d{2} \\d{4}"));
        }
    }

    @Test
    void equals_sameDescriptionAndDeadline_returnsSameDisplay() throws GeorgeException {
        // Arrange
        DeadlineTask task1 = new DeadlineTask("Same task", "2023-12-31 2359", true);
        DeadlineTask task2 = new DeadlineTask("Same task", "2023-12-31 2359", true);

        // Act & Assert
        assertEquals(task1.toString(), task2.toString());
        assertEquals(task1.getDisplayText(), task2.getDisplayText());
    }

    @Test
    void equals_differentDeadline_returnsDifferentDisplay() throws GeorgeException {
        // Arrange
        DeadlineTask task1 = new DeadlineTask("Same task", "2023-12-31 2359");
        DeadlineTask task2 = new DeadlineTask("Same task", "2023-12-30 1800");

        // Act & Assert
        assertNotEquals(task1.getDisplayText(), task2.getDisplayText());
        assertNotEquals(task1.toString(), task2.toString());
    }
}
