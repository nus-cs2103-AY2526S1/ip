package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import commands.CommandsEnum;
import ineffaexceptions.IneffaException;

/**
 * Test for task commands.
 */
public class TaskTest {
    /**
     * Test that tasks created with correct status
     *
     * @throws IneffaException If error encountered during parsing of task
     */
    @Test
    public void parseTask_createsCorrectTaskStatus_success() throws IneffaException {
        String input = "project meeting /from 2025-02-11 2pm /to 4pm";
        Task doneEvent = Task.parseTask(CommandsEnum.EVENT, true, input);
        Task notDoneEvent = Task.parseTask(CommandsEnum.EVENT, false, input);

        assertEquals(
                "[E][X] project meeting (from: 2025-02-11 2pm to: 4pm)",
                doneEvent.toString());

        assertEquals(
                "[E][ ] project meeting (from: 2025-02-11 2pm to: 4pm)",
                notDoneEvent.toString());
    }

    /**
     * Test that IneffaException thrown when task info is blank
     */
    @Test
    public void parseTask_taskInfoIsBlank_throwsIneffaException() {
        IneffaException e = assertThrows(IneffaException.class, () -> Task.parseTask(CommandsEnum.EVENT, ""));

        String expectedMessage = "Please enter a description for a EVENT task.";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
