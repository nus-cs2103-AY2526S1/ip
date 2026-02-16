package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import chatbot.exception.BotException;
import chatbot.exception.InvalidArgumentException;
import chatbot.exception.InvalidEventEndDateException;

// Ai was used to generate this class to speed up writing unit tests.
// Generation was based on the unit tests I had written for Deadline Task and To Do Task, following similar structure
// and test type.
class EventTaskTest {
    private static final String DESCRIPTION = "team meeting";
    private static final String START = "2025-09-01 10:00";
    private static final String END = "2025-09-01 12:00";

    @Test
    public void testPrintCompleteStatus_incompleteTask() throws BotException {
        EventTask task = new EventTask(DESCRIPTION, START, END);
        assertEquals("[E][ ] ", task.stringFormatCompleteStatus());
        assertFalse(task.isComplete());
    }

    @Test
    public void testPrintCompleteStatus_completedTask() throws BotException {
        EventTask task = new EventTask(DESCRIPTION, START, END);
        task.markTaskComplete();
        assertEquals("[E][X] ", task.stringFormatCompleteStatus());
        assertTrue(task.isComplete());
    }

    @Test
    public void testToSaveFormat_incompleteTask() throws BotException {
        EventTask task = new EventTask(DESCRIPTION, START, END);
        assertEquals("E | [ ] | team meeting | 2025-09-01 10:00 to 2025-09-01 12:00", task.toSaveFormat());
    }

    @Test
    public void testToSaveFormat_completedTask() throws BotException {
        EventTask task = new EventTask(DESCRIPTION, START, END);
        task.markTaskComplete();
        assertEquals("E | [X] | team meeting | 2025-09-01 10:00 to 2025-09-01 12:00", task.toSaveFormat());
    }

    @Test
    public void testToString_incompleteTask() throws BotException {
        EventTask task = new EventTask(DESCRIPTION, START, END);
        assertEquals("[E][ ] team meeting (from: Sep 1 2025 10:00 to: Sep 1 2025 12:00)", task.toString());
    }

    @Test
    public void testToString_completedTask() throws BotException {
        EventTask task = new EventTask(DESCRIPTION, START, END);
        task.markTaskComplete();
        assertEquals("[E][X] team meeting (from: Sep 1 2025 10:00 to: Sep 1 2025 12:00)", task.toString());
    }

    @Test
    public void existsInTaskDescription_matchesTaskName() throws BotException {
        EventTask task = new EventTask(DESCRIPTION, START, END);
        assertTrue(task.existsInTaskDescription("team"));
        assertTrue(task.existsInTaskDescription("MEETING")); // case-insensitive
    }

    @Test
    public void existsInTaskDescription_matchesFromOrTo() throws BotException {
        EventTask task = new EventTask(DESCRIPTION, START, END);
        assertTrue(task.existsInTaskDescription("Sep 1 2025 10:00")); // start datetime display format
        assertTrue(task.existsInTaskDescription("Sep 1 2025 12:00")); // end datetime display format
    }

    @Test
    public void existsInTaskDescription_invalidKeyword_returnsFalse() throws BotException {
        EventTask task = new EventTask(DESCRIPTION, START, END);
        assertFalse(task.existsInTaskDescription("other"));
        assertFalse(task.existsInTaskDescription(""));
        assertFalse(task.existsInTaskDescription(null));
    }

    @Test
    public void markTaskComplete_alreadyCompleted_throwsException() throws BotException {
        EventTask task = new EventTask(DESCRIPTION, START, END);
        task.markTaskComplete();
        assertThrows(BotException.class, task::markTaskComplete);
    }

    @Test
    public void unmarkTaskComplete_notCompleted_throwsException() throws BotException {
        EventTask task = new EventTask(DESCRIPTION, START, END);
        assertThrows(BotException.class, task::unmarkTaskComplete);
    }

    @Test
    public void markAndUnmarkTaskComplete_changesStateCorrectly() throws BotException {
        EventTask task = new EventTask(DESCRIPTION, START, END);
        task.markTaskComplete();
        assertTrue(task.isComplete());
        task.unmarkTaskComplete();
        assertFalse(task.isComplete());
    }

    @Test
    public void constructor_invalidStartDate_throwsException() {
        assertThrows(InvalidArgumentException.class, () -> new EventTask(DESCRIPTION, "invalid", END));
    }

    @Test
    public void constructor_invalidEndDate_throwsException() {
        assertThrows(InvalidArgumentException.class, () -> new EventTask(DESCRIPTION, START, "invalid"));
    }

    @Test
    public void constructor_endBeforeStart_throwsException() {
        assertThrows(InvalidEventEndDateException.class, () -> new EventTask(DESCRIPTION, START, "2025-09-01 09:00"));
    }
}
