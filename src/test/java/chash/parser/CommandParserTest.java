package chash.parser;

import chash.command.AddCommand;
import chash.command.Command;
import chash.command.ExitCommand;
import chash.exception.ChashException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class CommandParserTest {
    /*
    * AI Tool: ChatGPT 5 Free
    *
    * The tool helped to analyze some of the more complex source files and help me write sample
    * test cases. I then cross referenced it to write my test cases as I have a better context
    * compared to the tool.
    * */

    @Test
    void parse_bye_returnsExitCommand() throws Exception {
        Command cmd = CommandParser.parse("bye");

        assertInstanceOf(ExitCommand.class, cmd, "Parsing 'bye' should return an ExitCommand");
        assertTrue(cmd.isExit(), "ExitCommand should signal exit");
    }

    @Test
    void parse_todo_returnsAddCommand() throws Exception {
        Command cmd = CommandParser.parse("todo read book");

        assertInstanceOf(AddCommand.class, cmd, "Parsing 'todo' should return an AddCommand");
    }

    @Test
    void parse_invalidCommand_throwsException() {
        assertThrows(ChashException.class, () -> CommandParser.parse("abc123"),
            "Unknown commands should throw ChashException");
    }
}
