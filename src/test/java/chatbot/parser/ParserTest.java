package chatbot.parser;

import chatbot.exception.EmptyArgumentException;
import chatbot.exception.InvalidCommandException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


public class ParserTest {

    @Test
    public void parse_todo_returnsAddCommand() throws Exception {
        String input = "todo Finish homework";
        Object command = Parser.parse(input);
        assertEquals("AddCommand", command.getClass().getSimpleName());
    }

    @Test
    public void parse_todoEmpty_throwsEmptyArgumentException() {
        EmptyArgumentException ex = assertThrows(
                EmptyArgumentException.class, () -> Parser.parse("todo")
        );
        assertEquals("The description of a todo cannot be empty.", ex.getMessage());
    }

    @Test
    public void parse_deadline_returnsAddCommand() {
        // Use a valid format your Deadline.parse(...) accepts
        String input = "deadline Submit report /by 2019-12-02 1800";
        assertDoesNotThrow(() -> {
            Object command = Parser.parse(input);
            assertEquals("AddCommand", command.getClass().getSimpleName());
        });
    }

    @Test
    public void parse_deadlineEmpty_throwsEmptyArgumentException() {
        EmptyArgumentException ex = assertThrows(
                EmptyArgumentException.class, () -> Parser.parse("deadline")
        );
        assertEquals("The description of a deadline cannot be empty.", ex.getMessage());
    }

    @Test
    public void parse_event_returnsAddCommand() {
        String input = "event Party /from 2019-12-02 1800 /to 2019-12-02 2000";
        assertDoesNotThrow(() -> {
            Object command = Parser.parse(input);
            assertEquals("AddCommand", command.getClass().getSimpleName());
        });
    }

    @Test
    public void parse_eventEmpty_throwsEmptyArgumentException() {
        EmptyArgumentException ex = assertThrows(
                EmptyArgumentException.class, () -> Parser.parse("event")
        );
        assertEquals("The description of an event cannot be empty.", ex.getMessage());
    }

    @Test
    public void parse_delete_returnsDeleteCommand() throws Exception {
        String input = "delete 3";
        Object command = Parser.parse(input);
        assertEquals("DeleteCommand", command.getClass().getSimpleName());
    }

    @Test
    public void parse_mark_returnsMarkCommand() throws Exception {
        String input = "mark 1";
        Object command = Parser.parse(input);
        assertEquals("MarkCommand", command.getClass().getSimpleName());
    }

    @Test
    public void parse_unmark_returnsMarkCommand() throws Exception {
        String input = "unmark 2";
        Object command = Parser.parse(input);
        assertEquals("MarkCommand", command.getClass().getSimpleName());
    }

    @Test
    public void parse_find_returnsFindCommand() throws Exception {
        String input = "find report";
        Object command = Parser.parse(input);
        assertEquals("FindCommand", command.getClass().getSimpleName());
    }

    @Test
    public void parse_findEmpty_throwsEmptyArgumentException() {
        EmptyArgumentException ex = assertThrows(
                EmptyArgumentException.class, () -> Parser.parse("find")
        );
        assertEquals("Provide a keyword to find.", ex.getMessage());
    }

    @Test
    public void parse_help_returnsHelpCommand() throws Exception {
        String input = "help";
        Object command = Parser.parse(input);
        assertEquals("HelpCommand", command.getClass().getSimpleName());
    }

    @Test
    public void parse_reset_returnsResetCommand() throws Exception {
        String input = "reset";
        Object command = Parser.parse(input);
        assertEquals("ResetCommand", command.getClass().getSimpleName());
    }

    @Test
    public void parse_list_returnsListCommand() throws Exception {
        String input = "list";
        Object command = Parser.parse(input);
        assertEquals("ListCommand", command.getClass().getSimpleName());
    }

    @Test
    public void parse_save_returnsSaveCommand() throws Exception {
        String input = "save";
        Object command = Parser.parse(input);
        assertEquals("SaveCommand", command.getClass().getSimpleName());
    }

    @Test
    public void parse_bye_returnsExitCommand() throws Exception {
        String input = "bye";
        Object command = Parser.parse(input);
        assertEquals("ExitCommand", command.getClass().getSimpleName());
    }

    @Test
    public void parse_invalidCommand_throwsInvalidCommandException() {
        InvalidCommandException ex = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("wassup")
        );
        // Matches your current InvalidCommandException(String) message (with BEEP line)
        assertEquals(
                "Invalid command: \"wassup\". Please enter a valid command. \nBEEP B00P",
                ex.getMessage()
        );
    }

    @Test
    public void parse_blankInput_throwsEmptyArgumentException() {
        EmptyArgumentException ex = assertThrows(
                EmptyArgumentException.class, () -> Parser.parse("   ")
        );
        assertEquals("Please enter a command.", ex.getMessage());
    }

    @Test
    public void parse_nullInput_throwsEmptyArgumentException() {
        EmptyArgumentException ex = assertThrows(
                EmptyArgumentException.class, () -> Parser.parse(null)
        );
        assertEquals("Please enter a command.", ex.getMessage());
    }
}
