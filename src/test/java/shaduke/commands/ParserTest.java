package shaduke.commands;

import org.junit.jupiter.api.Test;
import shaduke.ShadukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    void parse_exitCommand_returnsExitCommand() throws ShadukeException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    void parse_listCommand_returnsListCommand() throws ShadukeException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    void parse_todoCommand_returnsAddCommand() throws ShadukeException {
        assertTrue(Parser.parse("todo Buy milk") instanceof AddCommand);
    }

    @Test
    void parse_todoCommandWithoutDescription_throwsException() {
        ShadukeException ex = assertThrows(ShadukeException.class, () -> Parser.parse("todo"));
        assertEquals("Sorry! Don't forget to add a tasty curry recipe to your todo list!", ex.getMessage());
    }

    @Test
    void parse_deadlineCommand_returnsAddCommand() throws ShadukeException {
        assertTrue(Parser.parse("deadline Submit report /by 2025-09-20") instanceof AddCommand);
    }

    @Test
    void parse_deadlineCommandMissingDate_throwsException() {
        ShadukeException ex = assertThrows(ShadukeException.class,
                () -> Parser.parse("deadline Submit report"));
        assertEquals("Sorry! Missing description or date!", ex.getMessage());
    }

    @Test
    void parse_eventCommand_returnsAddCommand() throws ShadukeException {
        assertTrue(Parser.parse("event Party /from 2025-09-20 /to 2025-09-21") instanceof AddCommand);
    }

    @Test
    void parse_unknownCommand_throwsException() {
        ShadukeException ex = assertThrows(ShadukeException.class, () -> Parser.parse("hello"));
        assertEquals("Sorry! I don't understand like when Shohib talks to me", ex.getMessage());
    }

    @Test
    void parse_markCommand_returnsMarkCommand() throws ShadukeException {
        assertTrue(Parser.parse("mark 2") instanceof MarkCommand);
    }

    @Test
    void parse_unmarkCommand_returnsUnmarkCommand() throws ShadukeException {
        assertTrue(Parser.parse("unmark 3") instanceof UnmarkCommand);
    }

    @Test
    void parse_deleteCommand_returnsDeleteCommand() throws ShadukeException {
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    void parse_findCommand_returnsFindCommand() throws ShadukeException {
        assertTrue(Parser.parse("find milk") instanceof FindCommand);
    }
}
