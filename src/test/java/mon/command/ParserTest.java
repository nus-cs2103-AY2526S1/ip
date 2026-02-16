package mon.command;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class ParserTest {
    @Test
    void testParseExitCommand() throws Exception {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void testParseListCommand() throws Exception {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void testParseMarkCommand() throws Exception {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    void testParseUnmarkCommand() throws Exception {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    void testParseDeleteCommand() throws Exception {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void testParseTodoCommand() throws Exception {
        Command command = Parser.parse("todo Read book");
        assertInstanceOf(AddTodoCommand.class, command);
    }

    @Test
    void testParseDeadlineCommand() throws Exception {
        Command command = Parser.parse("deadline Submit assignment /by 2023-12-25");
        assertInstanceOf(AddDeadlineCommand.class, command);
    }

    @Test
    void testParseEventCommand() throws Exception {
        Command command = Parser.parse("event Conference /from 2023-12-25 /to 2023-12-26");
        assertInstanceOf(AddEventCommand.class, command);
    }

    @Test
    void testParseEmptyInput() {
        assertThrows(Exception.class, () -> {
            Parser.parse("");
        });
    }

    @Test
    void testParseNullInput() {
        assertThrows(Exception.class, () -> {
            Parser.parse(null);
        });
    }

    @Test
    void testParseUnknownCommand() {
        assertThrows(Exception.class, () -> {
            Parser.parse("unknown");
        });
    }

    @Test
    void testParseMarkCommandWithoutNumber() {
        assertThrows(Exception.class, () -> {
            Parser.parse("mark");
        });
    }

    @Test
    void testParseMarkCommandWithInvalidNumber() {
        assertThrows(Exception.class, () -> {
            Parser.parse("mark abc");
        });
    }

    @Test
    void testParseUnmarkCommandWithoutNumber() {
        assertThrows(Exception.class, () -> {
            Parser.parse("unmark");
        });
    }

    @Test
    void testParseUnmarkCommandWithInvalidNumber() {
        assertThrows(Exception.class, () -> {
            Parser.parse("unmark abc");
        });
    }

    @Test
    void testParseDeleteCommandWithoutNumber() {
        assertThrows(Exception.class, () -> {
            Parser.parse("delete");
        });
    }

    @Test
    void testParseDeleteCommandWithInvalidNumber() {
        assertThrows(Exception.class, () -> {
            Parser.parse("delete abc");
        });
    }

    @Test
    void testParseTodoCommandWithoutDescription() {
        assertThrows(Exception.class, () -> {
            Parser.parse("todo");
        });
    }

    @Test
    void testParseDeadlineCommandWithoutBy() {
        assertThrows(Exception.class, () -> {
            Parser.parse("deadline Submit assignment");
        });
    }

    @Test
    void testParseEventCommandWithoutFrom() {
        assertThrows(Exception.class, () -> {
            Parser.parse("event Conference");
        });
    }

    @Test
    void testParseEventCommandWithoutTo() {
        assertThrows(Exception.class, () -> {
            Parser.parse("event Conference /from 2023-12-25");
        });
    }
}
