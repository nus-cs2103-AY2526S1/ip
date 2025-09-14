package tsundere.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tsundere.TsundereException;
import tsundere.command.AbstractCommand;
import tsundere.command.DeleteCommand;
import tsundere.command.FindCommand;
import tsundere.command.InvalidFormatCommand;
import tsundere.task.TsundereOutOfBoundsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTest {
    @Test
    public void parse_validDeleteInput_returnsDeleteCommand() throws TsundereException {
        String deleteZero = "delete 1";
        String deleteTen = "delete 11";

        AbstractCommand expected1 = new DeleteCommand(0);
        AbstractCommand output1 = CommandParser.parse(deleteZero);

        AbstractCommand expected2 =new DeleteCommand(10);
        AbstractCommand output2 =CommandParser.parse(deleteTen);

        assertEquals(expected1, output1,
                "valid delete command should return DeleteCommand");
        assertEquals(expected2, output2,
                "valid delete command should return DeleteCommand");
    }

    @Test
    public void parse_negativeDeleteInput_throwsOutOfBoundsException() throws TsundereException {
        String negativeIndex = "delete 0";

        TsundereException outOfBounds = new TsundereOutOfBoundsException();
        try {
            CommandParser.parse(negativeIndex);
            Assertions.fail("exception should have been thrown");
        } catch (TsundereException e) {
            assertEquals(outOfBounds, e, "negative index should throw out of bounds exception");
        }
    }

    @Test
    public void parse_notANumberDeleteInput_returnsInvalidFormatCommand() throws TsundereException {
        String deleteNotANumber = "delete abc";

        AbstractCommand expected = new InvalidFormatCommand(InvalidFormatCommand.Format.DELETE);
        AbstractCommand output = CommandParser.parse(deleteNotANumber);

        assertEquals(expected, output, "not an integer index input should return invalid format command");
    }
}
