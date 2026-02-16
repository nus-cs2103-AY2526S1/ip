package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import commands.CommandsEnum;
import ineffaexceptions.IneffaException;

/**
 * Test for the task command: event
 */
public class EventsTest {
    /**
     * Test that Event task created successfully
     *
     * @throws IneffaException If error encountered during parsing of task
     */
    @Test
    public void parseTask_createEventTask_success() throws IneffaException {
        try {
            Task event = Task.parseTask(
                    CommandsEnum.EVENT, false, "project meeting /from Mon 2pm /to 4pm"
            );

            assertEquals("[E][ ] project meeting (from: Mon 2pm to: 4pm)", event.toString());
        } catch (IneffaException e) {
            throw new IneffaException("exception thrown in parseTask_createDeadlineTask_success: " + e.getMessage());
        }
    }

    /** Test that Event task created with invalid format throws IneffaException */
    @Test
    public void parseTask_eventTaskInvalidFormat_throwsIneffaException() {
        String[] testFormatStrings = {"project meeting from Mon 2pm to 4pm",
            "project meeting Mon 2pm /to 4pm"
        };

        String invalidFormatMessage = "Invalid format for event task.";
        for (String s: testFormatStrings) {
            IneffaException e = assertThrows(IneffaException.class, () -> Task.parseTask(CommandsEnum.EVENT, s));

            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(invalidFormatMessage));
        }
    }

    /** Test that Event task created with invalid date format throws IneffaException */
    @Test
    public void parseTask_eventTaskInvalidDateFormat_throwsIneffaException() {
        String[] testDateStrings = {"project meeting /from Mon 2pm to 4pm",
            "project meeting /from Mon 2pm-3pm",
        };

        String invalidDateMessage = "Invalid format for start and end date/timings.";
        for (String s: testDateStrings) {
            IneffaException e = assertThrows(IneffaException.class, () -> Task.parseTask(CommandsEnum.EVENT, s));

            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(invalidDateMessage));
        }
    }
}
