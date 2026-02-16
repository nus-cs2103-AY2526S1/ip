package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import commands.CommandsEnum;
import ineffaexceptions.IneffaException;

/**
 * Test for the task command: deadline
 */
public class DeadlinesTest {
    /**
     * Test that Deadline task created successfully
     *
     * @throws IneffaException If error encountered during parsing of task
     */
    @Test
    public void parseTask_createDeadlineTask_success() throws IneffaException {
        try {
            Task deadline = Task.parseTask(
                    CommandsEnum.DEADLINE, true, "return book /by 01-11-2022 1pm"
            );

            assertEquals("[D][X] return book (by: 2022-11-01 1pm)", deadline.toString());
        } catch (IneffaException e) {
            throw new IneffaException("exception thrown in parseTask_createDeadlineTask_success: " + e.getMessage());
        }
    }

    /** Test that Deadline task created with invalid format throws IneffaException */
    @Test
    public void parseTask_deadlineInvalidFormat_throwsIneffaException() {
        String[] testFormatStrings = {"return book", // not /by keyword
            "return book /by" // no dates
        };

        String invalidFormatMessage = "Invalid format for deadline task.";
        for (String s: testFormatStrings) {
            IneffaException e = assertThrows(IneffaException.class, () -> Task.parseTask(CommandsEnum.DEADLINE, s));

            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(invalidFormatMessage));
        }
    }

    /** Test that Deadline task created with invalid date format throws IneffaException */
    @Test
    public void parseTask_deadlineInvalidDateFormat_throwsIneffaException() {
        String[] testDateStrings = {"return book /by Monday 11pm",
            "return book /by 2022-11-01"
        };

        String invalidDateMessage = "Invalid format for date and time.";
        for (String s: testDateStrings) {
            IneffaException e = assertThrows(IneffaException.class, () -> Task.parseTask(CommandsEnum.DEADLINE, s));

            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(invalidDateMessage));
        }
    }
}
