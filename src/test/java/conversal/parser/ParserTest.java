package conversal.parser;

import conversal.command.ByeCommand;
import conversal.command.Command;
import conversal.command.DeadlineCommand;
import conversal.command.DeleteCommand;
import conversal.command.DoWithinCommand;
import conversal.command.EventCommand;
import conversal.command.ListCommand;
import conversal.command.MarkAsCompleteCommand;
import conversal.command.MarkAsIncompleteCommand;
import conversal.command.TodoCommand;
import conversal.exception.ConversalException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link Parser}.
 * Ensures that valid user inputs return the correct command.
 * and invalid inputs throw a {@link ConversalException}.
 */
class ParserTest {

    /** Positive routing tests. */
    @Test
    void parse_bye_returnsByeCommand() throws ConversalException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    void parse_list_returnsListCommand() throws ConversalException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    void parse_mark_returnsMarkAsCompleteCommand() throws ConversalException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkAsCompleteCommand);
    }

    @Test
    void parse_unmark_returnsMarkAsIncompleteCommand() throws ConversalException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof MarkAsIncompleteCommand);
    }

    @Test
    void parse_delete_returnsDeleteCommand() throws ConversalException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    void parse_todo_returnsTodoCommand() throws ConversalException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof TodoCommand);
    }

    @Test
    void parse_deadline_returnsDeadlineCommand() throws ConversalException {
        Command command = Parser.parse("deadline return book /by 2025-09-01");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    void parse_event_returnsEventCommand() throws ConversalException {
        Command command = Parser.parse("event project meeting /from Mon 2pm /to 4pm");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    void parse_doWithin_returnsDoWithinCommand() throws ConversalException {
        Command command = Parser.parse("do-within finish homework /within 2 days");
        assertTrue(command instanceof DoWithinCommand);
    }

    /** Negative routing tests. */
    @Test
    void parse_unknown_throwsConversalException() {
        assertThrows(ConversalException.class, () -> Parser.parse("Blah"));
    }

    @Test
    void parse_empty_throwsConversalException() {
        assertThrows(ConversalException.class, () -> Parser.parse(""));
    }

    @Test
    void parse_whitespaceOnly_throwsConversalException() {
        assertThrows(ConversalException.class, () -> Parser.parse("   "));
    }
}
