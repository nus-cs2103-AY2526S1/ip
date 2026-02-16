package parser;

import command.*;
import exceptions.AlfredException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void parse_todo_returnsAddTodoCommand() throws AlfredException {
        Command c = Parser.parse("todo read book");
        assertEquals(AddTodoCommand.class, c.getClass());
    }

    @Test
    void parse_deadline_returnsAddDeadlineCommand() throws AlfredException {
        Command c = Parser.parse("deadline return book /by 2025-12-31");
        assertEquals(AddDeadlineCommand.class, c.getClass());
    }

    @Test
    void parse_event_returnsAddEventCommand_withFlexibleWhitespace() throws AlfredException {
        Command c = Parser.parse("event party   /from   2025-01-01   /to   2025-01-02");
        assertEquals(AddEventCommand.class, c.getClass());
    }

    @Test
    void parse_mark_returnsMarkCommand() throws AlfredException {
        Command c = Parser.parse("mark 3");
        assertEquals(MarkCommand.class, c.getClass());
    }

    @Test
    void parse_unmark_returnsMarkCommand() throws AlfredException {
        Command c = Parser.parse("unmark 1");
        assertEquals(MarkCommand.class, c.getClass());
    }

    @Test
    void parse_delete_returnsDeleteCommand() throws AlfredException {
        Command c = Parser.parse("delete 2");
        assertEquals(DeleteCommand.class, c.getClass());
    }

    @Test
    void parse_list_returnsListCommand() throws AlfredException {
        Command c = Parser.parse("list");
        assertEquals(ListCommand.class, c.getClass());
    }

    @Test
    void parse_bye_returnsExitCommand() throws AlfredException {
        Command c = Parser.parse("bye");
        assertEquals(ExitCommand.class, c.getClass());
    }

    @Test
    void parse_isCaseInsensitiveForCommandWord() throws AlfredException {
        Command c = Parser.parse("ToDo something");
        assertEquals(AddTodoCommand.class, c.getClass());
    }

    @Test
    void parse_trimsLeadingAndTrailingWhitespace() throws AlfredException {
        Command c = Parser.parse("   deadline   x   /by    2025-01-01  ");
        assertEquals(AddDeadlineCommand.class, c.getClass());
    }

    // -------- error paths (exact messages) --------

    @Test
    void parse_emptyInput_throwsWithExactMessage() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("   "));
        assertEquals("Empty input, Master Bruce.", ex.getMessage());
    }

    @Test
    void parse_unknownCommand_throwsWithExactMessage() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("dance"));
        // Note: the source uses a curly apostrophe in “don’t”
        assertEquals("I don’t understand that command: \"dance\"", ex.getMessage());
    }

    @Test
    void parse_mark_missingIndex_throwsWithExactMessage() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("mark   "));
        assertEquals("Please specify a task number, e.g., \"mark 1\".", ex.getMessage());
    }

    @Test
    void parse_mark_nonIntegerIndex_throwsWithExactMessage() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("mark abc"));
        assertEquals("Task number must be a positive integer.", ex.getMessage());
    }

    @Test
    void parse_mark_zeroIndex_throwsWithExactMessage() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("mark 0"));
        assertEquals("Task number must be a positive integer.", ex.getMessage());
    }

    @Test
    void parse_delete_negativeIndex_throwsWithExactMessage() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("delete -5"));
        assertEquals("Task number must be a positive integer.", ex.getMessage());
    }

    @Test
    void parse_todo_emptyDescription_throwsWithExactMessage() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("todo    "));
        assertEquals("Usage: todo <description>", ex.getMessage());
    }

    @Test
    void parse_deadline_missingBy_throwsWithExactMessage() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("deadline return book"));
        assertEquals("Missing /by. Example: /by YYYY-MM-DD", ex.getMessage());
    }

    @Test
    void parse_deadline_badDate_throwsWithExactMessage() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("deadline x /by 2025/12/31"));
        assertEquals("Usage: deadline <desc> /by YYYY-MM-DD (date must be YYYY-MM-DD)", ex.getMessage());
    }

    @Test
    void parse_event_missingFrom_throwsWithExactMessage() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("event party tonight"));
        assertEquals("Missing /from. Example: /from YYYY-MM-DD", ex.getMessage());
    }

    @Test
    void parse_event_missingTo_throwsWithExactMessage() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("event party /from 2025-01-01"));
        assertEquals("Missing /to. Example: /to YYYY-MM-DD", ex.getMessage());
    }

    @Test
    void parse_event_badDates_throwsWithExactMessage_forFrom() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("event party /from 01-01-2025 /to 2025-01-02"));
        assertEquals("Usage: event <desc> /from YYYY-MM-DD /to YYYY-MM-DD (date must be YYYY-MM-DD)", ex.getMessage());
    }

    @Test
    void parse_event_badDates_throwsWithExactMessage_forTo() {
        AlfredException ex = assertThrows(AlfredException.class, () -> Parser.parse("event party /from 2025-01-01 /to 02-01-2025"));
        assertEquals("Usage: event <desc> /from YYYY-MM-DD /to YYYY-MM-DD (date must be YYYY-MM-DD)", ex.getMessage());
    }
}
