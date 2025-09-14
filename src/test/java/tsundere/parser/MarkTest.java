package tsundere.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tsundere.command.InvalidFormatCommand.Format;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tsundere.TsundereException;
import tsundere.command.*;
import tsundere.task.TsundereOutOfBoundsException;

public class MarkTest {
    @Test
    public void parse_validMarkInput_returnsMarkCommand() throws TsundereException {
        String markZero = "mark 1";

        AbstractCommand expected = new MarkCommand(0);
        AbstractCommand output = CommandParser.parse(markZero);

        assertEquals(expected, output,
                "valid mark command should return MarkCommand");
    }

    @Test
    public void parse_negativeIndexMarkInput_throwsOutOfBoundsException() throws TsundereException {
        String markNegativeNumber = "mark -1";

        TsundereException expected = new TsundereOutOfBoundsException();
        try {
            AbstractCommand output = CommandParser.parse(markNegativeNumber);
            assert false : "should not reach here";
        } catch (TsundereException e) {
            assertEquals(expected, e, "negative index input should throw exception");
        }
    }

    @Test
    public void parse_notANumberMarkInput_returnsInvalidFormatCommand() throws TsundereException {
        String markNotANumber = "mark abc";

        AbstractCommand expected = new InvalidFormatCommand(Format.MARK);
        AbstractCommand output = CommandParser.parse(markNotANumber);

        assertEquals(expected, output, "non an integer index input should return invalid format command");
    }

    @Test
    public void parse_validUnmarkInput_returnsUnmarkCommand() throws TsundereException {
        String unmarkZero = "unmark 1";
        String unmarkTwo = "unmark 3";

        AbstractCommand expected1 = new UnmarkCommand(0);
        AbstractCommand output1 = CommandParser.parse(unmarkZero);

        AbstractCommand expected2 = new UnmarkCommand(2);
        AbstractCommand output2 = CommandParser.parse(unmarkTwo);

        assertEquals(expected1, output1,
                "valid unmark command should return UnmarkCommand");
        assertEquals(expected2, output2,
                "valid unmark command should return UnmarkCommand");
    }

    @Test
    public void parse_negativeIndexUnmarkInput_throwsOutOfBoundsException() throws TsundereException {
        String unmarkNegativeNumber = "unmark 0";

        TsundereException expected = new TsundereOutOfBoundsException();
        try {
            AbstractCommand output = CommandParser.parse(unmarkNegativeNumber);
            Assertions.fail("exception should have been thrown");
        } catch (TsundereException e) {
            assertEquals(expected, e, "negative index input should throw exception");
        }
    }

    @Test
    public void parse_notANumberUnmarkInput_returnsInvalidFormatCommand() throws TsundereException {
        String unmarkNotANumber = "unmark abc";

        AbstractCommand expected = new InvalidFormatCommand(Format.UNMARK);
        AbstractCommand output = CommandParser.parse(unmarkNotANumber);

        assertEquals(expected, output, "not an integer index input should return invalid format command");
    }
}
