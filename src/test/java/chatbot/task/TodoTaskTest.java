package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import chatbot.exception.BotException;

class TodoTaskTest {

    @Test
    public void testPrintCompleteStatus_notDone() {
        ToDoTask task = new ToDoTask("read book");
        String expected = "[T][ ] ";
        assertEquals(expected, task.stringFormatCompleteStatus());
    }

    @Test
    public void testPrintCompleteStatus_done() throws BotException {
        ToDoTask task = new ToDoTask("read book");
        task.markTaskComplete();
        String expected = "[T][X] ";
        assertEquals(expected, task.stringFormatCompleteStatus());
    }

    @Test
    public void testToSaveFormat_notDone() {
        ToDoTask task = new ToDoTask("read book");
        String expected = "T | [ ] | read book";
        assertEquals(expected, task.toSaveFormat());
    }

    @Test
    public void testToSaveFormat_done() throws BotException {
        ToDoTask task = new ToDoTask("read book");
        task.markTaskComplete();
        String expected = "T | [X] | read book";
        assertEquals(expected, task.toSaveFormat());
    }

    // AI was used to come up with more tests for the DeadlineTasks beyond what was written
    // It generated some method descriptions and functionalities such as testToString_notDone
    @Test
    public void testToString_notDone() {
        ToDoTask task = new ToDoTask("read book");
        String expected = "[T][ ] read book";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testToString_done() throws BotException {
        ToDoTask task = new ToDoTask("read book");
        task.markTaskComplete();
        String expected = "[T][X] read book";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testExistsInTaskDescription_exactMatch() {
        ToDoTask task = new ToDoTask("read book");
        assertTrue(task.existsInTaskDescription("read"));
    }

    @Test
    public void testExistsInTaskDescription_caseInsensitive() {
        ToDoTask task = new ToDoTask("read book");
        assertTrue(task.existsInTaskDescription("BOOK"));
    }

    @Test
    public void testExistsInTaskDescription_noMatch() {
        ToDoTask task = new ToDoTask("read book");
        assertFalse(task.existsInTaskDescription("exercise"));
    }

    @Test
    public void testExistsInTaskDescription_emptyKeyword() {
        ToDoTask task = new ToDoTask("read book");
        assertFalse(task.existsInTaskDescription(""));
    }

    @Test
    public void testMarkCompletedTaskAsComplete() throws BotException {
        ToDoTask task = new ToDoTask("read book");
        task.markTaskComplete();
        assertThrows(BotException.class, task::markTaskComplete);
    }

    @Test
    public void testUnmarkIncompleteTaskAsComplete() {
        ToDoTask task = new ToDoTask("read book");
        assertThrows(BotException.class, task::unmarkTaskComplete);
    }

    @Test
    public void testMarkAndUnmarkTaskComplete() throws BotException {
        ToDoTask task = new ToDoTask("read book");
        task.markTaskComplete();
        assertTrue(task.isComplete());
        task.unmarkTaskComplete();
        assertFalse(task.isComplete());
    }
}
