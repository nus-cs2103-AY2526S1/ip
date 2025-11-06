package yappy.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import yappy.command.ExitCommand;
import yappy.command.ListCommand;
import yappy.exception.YappyException;



public class ParserTest {

    private static final String COMMANDS_HELP =
            """
            Sorry I do not understand what you're yapping about.
            \t
            Available commands:
            - bye
            - list
            - find <query1,query2,...>
            - mark <index>
            - unmark <index>
            - delete <index>
            - todo <description>
            - deadline <description> /by <date>
            - event <description> /from <date> /to <date>
            - reschedule <index> /from <date> /to <date>
            - postpone <index> /by <date>
            Note: dates must use the format yyyy-MM-dd HH:mm""";

    @Test
    public void testParse_exitCommand_returnExitCommand() throws YappyException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
    }

    @Test
    public void testParse_listCommand_returnListCommand() throws YappyException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
    }

    @Test
    public void testParseMarkCommand_missingIndex_throwsException() {
        YappyException e = assertThrows(YappyException.class, () -> Parser.parse("mark"));
        assertEquals("Please specify the index for 'mark'!", e.getMessage());
    }

    @Test
    public void testParseUnknownCommand() {
        YappyException e = assertThrows(YappyException.class, () -> Parser.parse("foobar"));
        assertEquals(COMMANDS_HELP, e.getMessage());
    }
}
