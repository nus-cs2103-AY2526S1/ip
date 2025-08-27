package beebong.parser;

// Import Commands
import beebong.command.AddDeadlineTaskCommand;
import beebong.command.AddEventTaskCommand;
import beebong.command.AddToDoTaskCommand;
import beebong.command.DeleteTaskCommand;
import beebong.command.MarkTaskAsCommand;
import beebong.command.Command;
import beebong.command.ExitCommand;
import beebong.command.HelpCommand;
import beebong.command.ListAllTasksCommand;
import beebong.command.NullCommand;
// Import Exceptions
import beebong.exception.BBongException;
import beebong.exception.InvalidTaskDetailsException;
import beebong.exception.UnknownCommandException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    private final Parser parser = new Parser();

    // Exit Command
    @Test
    public void parseCommand_byeCommand_returnsExitCommand() throws BBongException {
        Command result = parser.parseCommand("bye");
        assertInstanceOf(ExitCommand.class, result);
    }

    // Help Command
    @Test
    public void parseCommand_helpCommand_returnsHelpCommand() throws BBongException {
        Command result = parser.parseCommand("help");
        assertInstanceOf(HelpCommand.class, result);
    }

    // List Command
    @Test
    public void parseCommand_listCommand_returnsListAllTasksCommand() throws BBongException {
        Command result = parser.parseCommand("list");
        assertInstanceOf(ListAllTasksCommand.class, result);
    }

    // ToDo Command
    @Test
    public void parseCommand_toDoTaskWithDetails_returnsAddToDoTaskCommand() throws BBongException {
        Command result = parser.parseCommand("todo read book");
        assertInstanceOf(AddToDoTaskCommand.class, result);
    }

    @Test
    public void parseCommand_toDoTaskWithoutDetails_throwsInvalidTaskDetailsException() {
        assertThrows(InvalidTaskDetailsException.class, () -> parser.parseCommand("todo"));
    }

    // Deadline Command
    @Test
    public void parseCommand_deadlineTaskWithValidDate_returnsAddDeadlineTaskCommand() throws BBongException {
        Command result = parser.parseCommand("deadline return book /by 20/08/2025 18:00");
        assertInstanceOf(AddDeadlineTaskCommand.class, result);
    }

    @Test
    public void parseCommand_deadlineTaskWithValidDate2_returnsAddDeadlineTaskCommand() throws BBongException {
        Command result = parser.parseCommand("deadline return book 2 /by 1/1/2025 9:00");
        assertInstanceOf(AddDeadlineTaskCommand.class, result);
    }

    @Test
    public void parseCommand_deadlineTaskWithInvalidDate_throwsInvalidTaskDetailsException() {
        assertThrows(InvalidTaskDetailsException.class,
                () -> parser.parseCommand("deadline return book /by hi"));
    }

    // Event Command
    @Test
    public void parseCommand_eventTaskWithValidDates_returnsAddEventTaskCommand() throws BBongException {
        Command result = parser.parseCommand("event project meeting /from 21/08/2025 14:00 /to 21/08/2025 16:00");
        assertInstanceOf(AddEventTaskCommand.class, result);
    }

    @Test
    public void parseCommand_eventTaskWithInvalidDatesStartDateAfterEndDate_throwsInvalidTaskDetailsException() {
        assertThrows(InvalidTaskDetailsException.class,
                () -> parser.parseCommand("event hackathon /from 22/08/2025 18:00 /to 22/08/2025 16:00"));
    }

    @Test
    public void parseCommand_eventTaskWithInvalidDates_throwsInvalidTaskDetailsException() {
        assertThrows(InvalidTaskDetailsException.class,
                () -> parser.parseCommand("event party /from hi /to hi again"));
    }

    // Mark/Unmark Command
    @Test
    public void parseCommand_markTaskWithValidNumber_returnsMarkTaskAsCommand() throws BBongException {
        Command result = parser.parseCommand("mark 1");
        assertInstanceOf(MarkTaskAsCommand.class, result);
    }

    @Test
    public void parseCommand_markTaskWithoutNumber_throwsInvalidTaskDetailsException() {
        assertThrows(InvalidTaskDetailsException.class, () -> parser.parseCommand("mark"));
    }

    @Test
    public void parseCommand_markTaskWithInvalidNumber_throwsInvalidTaskDetailsException() {
        assertThrows(InvalidTaskDetailsException.class, () -> parser.parseCommand("mark hi"));
    }

    // Delete Command
    @Test
    public void parseCommand_deleteTaskWithValidNumber_returnsDeleteTaskCommand() throws BBongException {
        Command result = parser.parseCommand("delete 2");
        assertInstanceOf(DeleteTaskCommand.class, result);
    }

    @Test
    public void parseCommand_deleteTaskWithoutNumber_throwsInvalidTaskDetailsException() {
        assertThrows(InvalidTaskDetailsException.class, () -> parser.parseCommand("delete"));
    }

    @Test
    public void parseCommand_deleteTaskWithInvalidNumber_throwsInvalidTaskDetailsException() {
        assertThrows(InvalidTaskDetailsException.class, () -> parser.parseCommand("delete abc"));
    }

    // Unknown Command
    @Test
    public void parseCommand_unknownCommand_returnsNullCommand() throws BBongException {
        assertThrows(UnknownCommandException.class, () -> parser.parseCommand("idk"));
    }
}
