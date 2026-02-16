package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import chatbot.exception.BotException;

public class DeadlineTaskTest {
    private static final String DESCRIPTION = "submit report";
    private static final String VALID_DATE = "2025-09-01";

    @Test
    public void testPrintCompleteStatus_incompleteTask() throws BotException {
        DeadlineTask task = new DeadlineTask(DESCRIPTION, VALID_DATE);
        assertEquals("[D][ ] ", task.stringFormatCompleteStatus());
        assertFalse(task.isComplete());
    }

    @Test
    public void testPrintCompleteStatus_completedTask() throws BotException {
        DeadlineTask task = new DeadlineTask(DESCRIPTION, VALID_DATE);
        task.markTaskComplete();
        assertEquals("[D][X] ", task.stringFormatCompleteStatus());
        assertTrue(task.isComplete());
    }

    @Test
    public void testToSaveFormat_incompleteTask() throws BotException {
        DeadlineTask task = new DeadlineTask(DESCRIPTION, VALID_DATE);
        assertEquals("D | [ ] | submit report | 2025-09-01", task.toSaveFormat());
    }

    @Test
    public void testToSaveFormat_completedTask() throws BotException {
        DeadlineTask task = new DeadlineTask(DESCRIPTION, VALID_DATE);
        task.markTaskComplete();
        assertEquals("D | [X] | submit report | 2025-09-01", task.toSaveFormat());
    }

    @Test
    public void testToString_incompleteTask() throws BotException {
        DeadlineTask task = new DeadlineTask(DESCRIPTION, VALID_DATE);
        assertEquals("[D][ ] submit report (by: Sep 1 2025)", task.toString());
    }

    @Test
    public void testToString_completedTask() throws BotException {
        DeadlineTask task = new DeadlineTask(DESCRIPTION, VALID_DATE);
        task.markTaskComplete();
        assertEquals("[D][X] submit report (by: Sep 1 2025)", task.toString());
    }

    // AI was used to come up with more tests for the DeadlineTasks beyond what was written.
    // It generated some method descriptions and functionalities such as existsInTaskDescription_matchesTaskName.
    @Test
    public void existsInTaskDescription_matchesTaskName() throws BotException {
        DeadlineTask task = new DeadlineTask(DESCRIPTION, VALID_DATE);
        assertTrue(task.existsInTaskDescription("submit"));
        assertTrue(task.existsInTaskDescription("REPORT")); // case-insensitive
    }

    @Test
    public void existsInTaskDescription_matchesDeadline() throws BotException {
        DeadlineTask task = new DeadlineTask(DESCRIPTION, VALID_DATE);
        assertTrue(task.existsInTaskDescription("Sep 1 2025")); // matches display format
        assertTrue(task.existsInTaskDescription("2025-09-01")); // matches exact date
    }

    @Test
    public void existsInTaskDescription_invalidKeyword_returnsFalse() throws BotException {
        DeadlineTask task = new DeadlineTask(DESCRIPTION, VALID_DATE);
        assertFalse(task.existsInTaskDescription("other"));
        assertFalse(task.existsInTaskDescription(""));
        assertFalse(task.existsInTaskDescription(null));
    }

    @Test
    public void markAndUnmarkTaskComplete_changesStateCorrectly() throws BotException {
        DeadlineTask task = new DeadlineTask(DESCRIPTION, VALID_DATE);
        task.markTaskComplete();
        assertTrue(task.isComplete());
        task.unmarkTaskComplete();
        assertFalse(task.isComplete());
    }

    @Test
    public void markTaskComplete_alreadyCompleted_throwsException() throws BotException {
        DeadlineTask task = new DeadlineTask(DESCRIPTION, VALID_DATE);
        task.markTaskComplete();
        assertThrows(BotException.class, task::markTaskComplete);
    }

    @Test
    public void unmarkTaskComplete_notCompleted_throwsException() throws BotException {
        DeadlineTask task = new DeadlineTask(DESCRIPTION, VALID_DATE);
        assertThrows(BotException.class, task::unmarkTaskComplete);
    }

    @Test
    public void testInvalidDate_throwsException() {
        assertThrows(BotException.class, () -> new DeadlineTask(DESCRIPTION, "invalid-date"));
    }
}
