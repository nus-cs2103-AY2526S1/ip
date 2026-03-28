package cattis.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import cattis.CattisInterface;
import cattis.CattisStub;
import cattis.exception.CattisException;
import cattis.task.Task;

public class AddDeadlineTaskCommandTest {

    @Test
    public void addDeadlineTask_missingEndDate_exceptionThrown() {
        try {
            AddTaskCommand cmd = new AddDeadlineTaskCommand("task 1 /by ");
            CattisInterface cattis = new CattisStub();
            cmd.execute(cattis);
            fail();
        } catch (CattisException err) {
            assertEquals(CattisException.EMPTY_FIELD, err.getMessage());
        }
    }

    @Test
    public void addDeadlineTask_invalidEndDate_exceptionThrown() {
        try {
            AddTaskCommand cmd = new AddDeadlineTaskCommand("task 1 /by july 19");
            CattisInterface cattis = new CattisStub();
            cmd.execute(cattis);
            fail();
        } catch (CattisException err) {
            String errMsg = "⚠ OOPS! Failed to parse time for format " + Task.DATE_TIME_INPUT_FORMATTER;
            assertEquals(errMsg, err.toString());
        }
    }
}
