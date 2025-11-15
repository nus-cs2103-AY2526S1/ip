package nami.parser;

import nami.command.AddDeadlineCommand;
import nami.command.AddToDoCommand;
import nami.command.Command;
import nami.command.MarkCommand;
import nami.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void parse_todo_returnsAddTodoCommand() throws Exception {
        Command c = Parser.parse("todo read book");
        assertTrue(c instanceof AddToDoCommand,
                "Expected AddTodoCommand for 'todo ...'");
    }

    @Test
    void parse_mark_returnsMarkCommand() throws Exception {
        Command c = Parser.parse("mark 2");
        assertTrue(c instanceof MarkCommand,
                "Expected MarkCommand for 'mark <index>'");
    }

    @Test
    void parse_deadline_missingBy_throws() {
        DukeException ex = assertThrows(DukeException.class,
                () -> Parser.parse("deadline return book"),
                "Missing /by should throw DukeException");
        assertTrue(ex.getMessage().toLowerCase().contains("deadline"),
                "Message should mention deadline problem");
    }

    @Test
    void parse_deadline_valid_returnsAddDeadlineCommand() throws Exception {
        Command c = Parser.parse("deadline return book /by 2/12/2019 1800");
        assertTrue(c instanceof AddDeadlineCommand,
                "Expected AddDeadlineCommand for correctly formatted deadline");
    }
}
