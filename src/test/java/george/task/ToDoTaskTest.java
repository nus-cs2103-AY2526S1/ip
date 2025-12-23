package george.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import george.exceptions.GeorgeException;


class ToDoTaskTest {

    @Test
    void constructor_validDescription_createsTask() throws GeorgeException {
        // Arrange & Act
        ToDoTask task = new ToDoTask("Buy bananas");

        // Assert
        assertEquals("Buy bananas", task.getDescription());
        assertFalse(task.isDone());
        assertEquals("[T]", task.getType());
    }

    @Test
    void constructor_withIsDoneTrue_createsDoneTask() throws GeorgeException {
        // Arrange & Act
        ToDoTask task = new ToDoTask("Complete assignment", true);

        // Assert
        assertEquals("Complete assignment", task.getDescription());
        assertTrue(task.isDone());
    }

    @Test
    void constructor_withIsDoneFalse_createsNotDoneTask() throws GeorgeException {
        // Arrange & Act
        ToDoTask task = new ToDoTask("Read book", false);

        // Assert
        assertEquals("Read book", task.getDescription());
        assertFalse(task.isDone());
    }

    @Test
    void constructor_nullDescription_throwsGeorgeException() {
        // Arrange, Act & Assert
        assertThrows(GeorgeException.class, () -> {
            new ToDoTask(null);
        });
    }

    @Test
    void getType_returnsCorrectType() throws GeorgeException {
        // Arrange
        ToDoTask task = new ToDoTask("Test task");

        // Act
        String type = task.getType();

        // Assert
        assertEquals("[T]", type);
    }

    @Test
    void getDisplayText_notDone_returnsCorrectFormat() throws GeorgeException {
        // Arrange
        ToDoTask task = new ToDoTask("Buy milk");

        // Act
        String displayText = task.getDisplayText();

        // Assert
        assertEquals("[T][ ] Buy milk", displayText);
        assertTrue(displayText.contains("[T]"));
        assertTrue(displayText.contains("[ ]"));
        assertTrue(displayText.contains("Buy milk"));
    }

    @Test
    void getDisplayText_done_returnsCorrectFormat() throws GeorgeException {
        // Arrange
        ToDoTask task = new ToDoTask("Buy milk", true);

        // Act
        String displayText = task.getDisplayText();

        // Assert
        assertEquals("[T][X] Buy milk", displayText);
        assertTrue(displayText.contains("[T]"));
        assertTrue(displayText.contains("[X]"));
        assertTrue(displayText.contains("Buy milk"));
    }

    @Test
    void toString_notDone_returnsCorrectSerializationFormat() throws GeorgeException {
        // Arrange
        ToDoTask task = new ToDoTask("Study for exam");

        // Act
        String result = task.toString();

        // Assert
        assertEquals("T | 0 | Study for exam", result);
    }

    @Test
    void toString_done_returnsCorrectSerializationFormat() throws GeorgeException {
        // Arrange
        ToDoTask task = new ToDoTask("Study for exam", true);

        // Act
        String result = task.toString();

        // Assert
        assertEquals("T | 1 | Study for exam", result);
    }

    @Test
    void markAsDone_changesStatusToDone() throws GeorgeException {
        // Arrange
        ToDoTask task = new ToDoTask("Clean room");

        // Act
        task.markAsDone();

        // Assert
        assertTrue(task.isDone());
        assertEquals("[X]", task.getStatus());
    }

    @Test
    void markAsNotDone_changesStatusToNotDone() throws GeorgeException {
        // Arrange
        ToDoTask task = new ToDoTask("Clean room", true);

        // Act
        task.markAsNotDone();

        // Assert
        assertFalse(task.isDone());
        assertEquals("[ ]", task.getStatus());
    }

    @Test
    void getStatus_afterMarkingDone_returnsDoneStatus() throws GeorgeException {
        // Arrange
        ToDoTask task = new ToDoTask("Test task");

        // Act
        task.markAsDone();
        String status = task.getStatus();

        // Assert
        assertEquals("[X]", status);
    }

    @Test
    void getStatus_afterMarkingNotDone_returnsNotDoneStatus() throws GeorgeException {
        // Arrange
        ToDoTask task = new ToDoTask("Test task", true);

        // Act
        task.markAsNotDone();
        String status = task.getStatus();

        // Assert
        assertEquals("[ ]", status);
    }

    @Test
    void equals_sameDescriptionAndStatus_returnsTrue() throws GeorgeException {
        // Arrange
        ToDoTask task1 = new ToDoTask("Same task", true);
        ToDoTask task2 = new ToDoTask("Same task", true);

        // Act & Assert
        assertEquals(task1.toString(), task2.toString());
        assertEquals(task1.getDisplayText(), task2.getDisplayText());
    }

    @Test
    void equals_differentStatus_returnsDifferentDisplay() throws GeorgeException {
        // Arrange
        ToDoTask doneTask = new ToDoTask("Same task", true);
        ToDoTask notDoneTask = new ToDoTask("Same task", false);

        // Act & Assert
        assertNotEquals(doneTask.getDisplayText(), notDoneTask.getDisplayText());
        assertNotEquals(doneTask.toString(), notDoneTask.toString());
    }
}
