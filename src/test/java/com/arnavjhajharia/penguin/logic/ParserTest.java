package com.arnavjhajharia.penguin.logic;

import com.arnavjhajharia.penguin.common.exceptions.MissingArgumentException;
import com.arnavjhajharia.penguin.common.exceptions.UnknownCommandException;
import com.arnavjhajharia.penguin.logic.commands.AddCommand;
import com.arnavjhajharia.penguin.logic.commands.ByeCommand;
import com.arnavjhajharia.penguin.logic.commands.Command;
import com.arnavjhajharia.penguin.logic.commands.DeleteCommand;
import com.arnavjhajharia.penguin.logic.commands.ListCommand;
import com.arnavjhajharia.penguin.logic.commands.MarkCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private final Parser parser = new Parser();

    // ---------- Error cases ----------
    @Test
    void parse_null_throwsUnknownCommand() {
        UnknownCommandException ex = assertThrows(
                UnknownCommandException.class,
                () -> parser.parse(null)
        );
        assertTrue(ex.getMessage().contains("(null)"));
    }

    @Test
    void parse_empty_throwsUnknownCommand() {
        UnknownCommandException ex = assertThrows(
                UnknownCommandException.class,
                () -> parser.parse("   \t  ")
        );
        assertTrue(ex.getMessage().contains("(empty)"));
    }

    @Test
    void parse_unknownCommand_throwsUnknownCommand() {
        UnknownCommandException ex = assertThrows(
                UnknownCommandException.class,
                () -> parser.parse("gibberish foo bar")
        );
        // Parser throws with the unknown token as message
        assertTrue(ex.getMessage().contains("gibberish"));
    }

    @Test
    void parse_mark_withoutArg_throwsMissingArgument() {
        MissingArgumentException ex = assertThrows(
                MissingArgumentException.class,
                () -> parser.parse("mark")
        );
        assertTrue(ex.getMessage().contains("mark <index>"));
    }

    @Test
    void parse_unmark_withoutArg_throwsMissingArgument() {
        MissingArgumentException ex = assertThrows(
                MissingArgumentException.class,
                () -> parser.parse("unmark   ")
        );
        assertTrue(ex.getMessage().contains("unmark <index>"));
    }

    @Test
    void parse_delete_withoutArg_throwsMissingArgument() {
        MissingArgumentException ex = assertThrows(
                MissingArgumentException.class,
                () -> parser.parse("delete")
        );
        assertTrue(ex.getMessage().contains("delete <index>"));
    }

    // ---------- Happy paths ----------
    @Test
    void parse_list_returnsListCommand() throws Exception {
        Command c = parser.parse("list");
        assertInstanceOf(ListCommand.class, c);
    }

    @Test
    void parse_bye_returnsByeCommand() throws Exception {
        Command c = parser.parse("bye");
        assertInstanceOf(ByeCommand.class, c);
    }

    @Test
    void parse_todo_withArg_returnsAddCommand() throws Exception {
        Command c = parser.parse("todo read book");
        assertInstanceOf(AddCommand.class, c);
    }

    @Test
    void parse_deadline_withArg_returnsAddCommand() throws Exception {
        Command c = parser.parse("deadline submit iP /by tomorrow 5pm");
        assertInstanceOf(AddCommand.class, c);
    }

    @Test
    void parse_event_withArg_returnsAddCommand() throws Exception {
        Command c = parser.parse("event meeting /at Mon 2pm to 3pm");
        assertInstanceOf(AddCommand.class, c);
    }

    @Test
    void parse_mark_withIndex_returnsMarkCommand() throws Exception {
        Command c = parser.parse("mark 3");
        assertInstanceOf(MarkCommand.class, c);
    }

    @Test
    void parse_unmark_withIndex_returnsMarkCommand() throws Exception {
        Command c = parser.parse("unmark 10");
        assertInstanceOf(MarkCommand.class, c);
    }

    @Test
    void parse_delete_withIndex_returnsDeleteCommand() throws Exception {
        Command c = parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, c);
    }

    // ---------- Whitespace & arg-splitting ----------
    @Test
    void parse_trimsLeadingAndTrailingWhitespace() throws Exception {
        Command c = parser.parse("   list   ");
        assertInstanceOf(ListCommand.class, c);
    }

    @Test
    void parse_splitsOnFirstWhitespaceOnly_leavesRestAsArg() throws Exception {
        // We can't inspect private fields of AddCommand here,
        // but we can at least ensure it parses as AddCommand and doesn't throw.
        Command c = parser.parse("deadline  finish project   /by   next Friday 5pm ");
        assertInstanceOf(AddCommand.class, c);
    }

    @Test
    void parse_todo_withoutArg_stillReturnsAddCommand_parseLevelDoesNotValidate() throws Exception {
        // Parser defers arg validation for AddCommand (todo/deadline/event),
        // unlike mark/unmark/delete which are validated at parse-time.
        Command c = parser.parse("todo");
        assertInstanceOf(AddCommand.class, c);
    }
}
