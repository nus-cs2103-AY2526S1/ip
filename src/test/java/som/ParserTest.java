package som;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import som.command.AddCommand;
import som.command.Command;
import som.command.DeleteCommand;
import som.command.ExitCommand;
import som.command.FindCommand;
import som.command.ListCommand;
import som.command.MarkCommand;
import som.command.UnmarkCommand;
import som.task.Deadline;
import som.task.Event;
import som.task.Task;
import som.task.Todo;

public class ParserTest {

    @Test
    void parseTaskTodoCreatesTodo() throws SomException {
        // Act
        Command c = Parser.parse("todo read book");

        // Assert
        assertInstanceOf(AddCommand.class, c);
        AddCommand addCommand = (AddCommand) c;
        Task t = addCommand.getTask();
        assertInstanceOf(Todo.class, t);
        assertEquals(" [T]  [ ] read book", t.toString());
    }

    @Test
    void parseDeadlineReturnsAddCommand() throws SomException {
        // Act
        Command c = Parser.parse("deadline read book /by 2025-03-15 2359");

        //Assert
        assertInstanceOf(AddCommand.class, c);
        AddCommand addCommand = (AddCommand) c;
        Task task = addCommand.getTask();
        assertInstanceOf(Deadline.class, task);
        assertEquals(2025, ((Deadline) task).getBy().getYear());
        assertEquals(3, ((Deadline) task).getBy().getMonthValue());
        assertEquals(15, ((Deadline) task).getBy().getDayOfMonth());
        assertEquals(23, ((Deadline) task).getBy().getHour());
        assertEquals(59, ((Deadline) task).getBy().getMinute());
    }

    @Test
    void deadlineMissingByThrowsSomException() {
        // Act & Assert
        assertThrows(SomException.class, () ->
                Parser.parse("deadline submit report")
        );
    }

    @Test
    void deadlineEmptyDescThrowsSomException() {
        // Act & Assert
        assertThrows(SomException.class, () ->
                Parser.parse("deadline /by 2025-03-15 2359")
        );
    }

    @Test
    void parse_validEvent_returnsAddCommandWithEvent() throws SomException {
        // Act
        Command c = Parser.parse("event Team Sync /from 2025-03-16 1000 /to 2025-03-16 1100");

        // Assert
        assertInstanceOf(AddCommand.class, c);
        AddCommand addCommand = (AddCommand) c;
        Task task = addCommand.getTask();
        assertInstanceOf(Event.class, task);
        assertEquals(10, ((Event) task).getFrom().getHour());
        assertEquals(11, ((Event) task).getTo().getHour());
    }

    @Test
    void eventMissingFromThrowsSomException() {
        // Act & Assert
        assertThrows(SomException.class, () ->
                Parser.parse("event Team Sync /to 2025-03-16 1100")
        );
    }

    @Test
    void eventMissingToThrowsSomException() {
        // Act & Assert
        assertThrows(SomException.class, () ->
                Parser.parse("event Team Sync /from 2025-03-16 1000")
        );
    }

    @Test
    void eventFromAfterToThrowsSomException() {
        // Act & Assert
        assertThrows(SomException.class, () ->
                Parser.parse("event Test /from 2025-03-17 1200 /to 2025-03-17 1100")
        );
    }

    @Test
    void markValidIndexReturnsMarkCommand() throws SomException {
        // Act
        Command c = Parser.parse("mark 1");

        // Assert
        assertInstanceOf(MarkCommand.class, c);
        MarkCommand cmd = (MarkCommand) c;
        assertEquals(0, cmd.getIndex());
    }

    @Test
    void markMissingIndexThrowsSomException() {
        // Act & Assert
        assertThrows(SomException.class, () ->
                Parser.parse("mark")
        );
    }

    @Test
    void unmarkValidIndexReturnsUnmarkCommand() throws SomException {
        // Act
        Command c = Parser.parse("unmark 3");

        // Assert
        assertInstanceOf(UnmarkCommand.class, c);
        UnmarkCommand cmd = (UnmarkCommand) c;
        assertEquals(2, cmd.getIndex());
    }

    @Test
    void markInvalidIndexThrowsSomException() {
        // Act & Assert
        assertThrows(SomException.class, () ->
                Parser.parse("mark abc")
        );
    }

    @Test
    void deleteValidIndexReturnsDeleteCommand() throws SomException {
        // Act
        Command c = Parser.parse("delete 2");

        // Assert
        assertInstanceOf(DeleteCommand.class, c);
        DeleteCommand cmd = (DeleteCommand) c;
        assertEquals(1, cmd.getIndex());
    }

    @Test
    void findValidDate_returnsFindCommand() throws SomException {
        // Act
        Command c = Parser.parse("find 2025-03-18");

        // Assert
        assertInstanceOf(FindCommand.class, c);
        FindCommand cmd = (FindCommand) c;
        assertEquals("2025-03-18", cmd.getKeyword());
    }

    @Test
    void findMissingDateThrowsSomException() {
        // Act & Assert
        assertThrows(SomException.class, () ->
                Parser.parse("find")
        );
    }

    @Test
    void uiCommandReturnsUiCommand() throws SomException {
        // Act
        Command list = Parser.parse("list");
        Command bye = Parser.parse("bye");

        // Assert
        assertInstanceOf(ListCommand.class, list);
        assertInstanceOf(ExitCommand.class, bye);
    }

    @Test
    void unknownCommandThrowsSomException() {
        // Act & Assert
        assertThrows(SomException.class, () ->
                Parser.parse("unknowncommand hello")
        );
    }

    @Test
    void emptyCommandThrowsSomException() {
        // Act & Assert
        assertThrows(SomException.class, () ->
                Parser.parse("")
        );
    }



}
