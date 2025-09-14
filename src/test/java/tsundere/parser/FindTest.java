package tsundere.parser;

import org.junit.jupiter.api.Test;
import tsundere.TsundereException;
import tsundere.command.AbstractCommand;
import tsundere.command.FindCommand;
import tsundere.command.InvalidFormatCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTest {
    @Test
    public void parse_validFindInput_returnsFindCommand() throws TsundereException {
        String findWord = "find name";
        String findMultipleWords = "find long task name";

        AbstractCommand expected1 = new FindCommand("name");
        AbstractCommand output1 = CommandParser.parse(findWord);

        AbstractCommand expected2 = new FindCommand("long task name");
        AbstractCommand output2 =CommandParser.parse(findMultipleWords);

        assertEquals(expected1, output1,
                "valid find command should return FindCommand");
        assertEquals(expected2, output2,
                "valid find command should return FindCommand");
    }

    @Test
    public void parse_invalidFindInput_returnsInvalidFormatCommand() throws TsundereException {
        String noArgument = "find";

        AbstractCommand expected = new InvalidFormatCommand(InvalidFormatCommand.Format.FIND);
        AbstractCommand output = CommandParser.parse(noArgument);

        assertEquals(expected, output,
                "valid find command should return FindCommand");
    }
}
