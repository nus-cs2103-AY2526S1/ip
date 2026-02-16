package momo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import momo.command.AddDeadlineCommand;
import momo.command.AddEventCommand;
import momo.command.AddTodoCommand;
import momo.command.Command;
import momo.command.DeleteCommand;
import momo.command.ExitCommand;
import momo.command.FindCommand;
import momo.command.HelpCommand;
import momo.command.ListCommand;
import momo.command.MarkCommand;
import momo.command.UnmarkCommand;
import momo.exception.MomoException;
import momo.task.Deadline;
import momo.task.Event;
import momo.task.Task;
import momo.task.Todo;

/**
 * Unit tests for the {@link Parser} class.
 *
 * <p>This class verifies that commands and tasks are correctly parsed from
 * user input strings or saved task data. It tests both valid inputs
 * (ensuring correct objects are returned) and invalid inputs
 * (ensuring {@link MomoException} is thrown with the correct message).</p>
 */
public class ParserTest {

    /**
     * Tests that the "help" command is correctly parsed into a {@link HelpCommand}.
     */
    @Test
    public void parseToCommand_help_success() throws MomoException {
        Command command = Parser.parseToCommand("help");
        assertInstanceOf(HelpCommand.class, command);
    }

    /**
     * Tests that the "bye" command is correctly parsed into an {@link ExitCommand}.
     */
    @Test
    public void parseToCommand_bye_success() throws MomoException {
        Command command = Parser.parseToCommand("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    /**
     * Tests that the "list" command is correctly parsed into a {@link ListCommand}.
     */
    @Test
    public void parseToCommand_list_success() throws MomoException {
        Command command = Parser.parseToCommand("list");
        assertInstanceOf(ListCommand.class, command);
    }

    /**
     * Tests that a valid "todo" command is correctly parsed into {@link AddTodoCommand}.
     */
    @Test
    public void parseToCommand_todo_success() throws MomoException {
        Command command = Parser.parseToCommand("todo read book");
        assertInstanceOf(AddTodoCommand.class, command);
    }

    /**
     * Tests that a "todo" command with missing description throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_todoMissingDescription_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("todo"));
        assertTrue(e.getMessage().contains("The description of the todo is empty"));
    }

    /**
     * Tests that a valid "find" command is correctly parsed into {@link FindCommand}.
     */
    @Test
    public void parseToCommand_find_success() throws MomoException {
        Command command = Parser.parseToCommand("find book");
        assertInstanceOf(FindCommand.class, command);
    }

    /**
     * Tests that a "find" command with missing keyword throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_findMissingKeyword_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("find"));
        assertTrue(e.getMessage().contains("The keyword to find is missing"));
    }

    /**
     * Tests that a valid "deadline" command is correctly parsed into {@link AddDeadlineCommand}.
     */
    @Test
    public void parseToCommand_deadline_success() throws MomoException {
        Command command = Parser.parseToCommand("deadline return book /by 2025-12-02 1800");
        assertInstanceOf(AddDeadlineCommand.class, command);
    }

    /**
     * Tests that a "deadline" command with missing description throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_deadlineMissingDescription_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("deadline"));
        assertTrue(e.getMessage().contains("The description of the deadline is empty"));
    }

    /**
     * Tests that a "deadline" command missing the "/by" part throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_deadlineMissingBy_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("deadline return book"));
        assertTrue(e.getMessage().contains("The deadline is missing \"/by\""));
    }

    //CHECKSTYLE.OFF: SeparatorWrap
    /**
     * Tests that a "deadline" command with invalid date-time format throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_deadlineInvalidDateTimeFormat_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class,
                () -> Parser.parseToCommand("deadline return book /by Monday"));
        assertTrue(e.getMessage().contains("The format of date and time entered is invalid"));
    }
    //CHECKSTYLE.ON: SeparatorWrap

    /**
     * Tests that a valid "event" command is correctly parsed into {@link AddEventCommand}.
     */
    @Test
    public void parseToCommand_event_success() throws MomoException {
        String trimmedInput = "event project meeting /from 2025-12-02 1200 /to 2025-12-02 1400";
        Command command = Parser.parseToCommand(trimmedInput);
        assertInstanceOf(AddEventCommand.class, command);
    }

    /**
     * Tests that an "event" command with missing description throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_eventMissingDescription_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("event"));
        assertTrue(e.getMessage().contains("The description of the event is empty"));
    }

    /**
     * Tests that an "event" command missing the "/from" part throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_eventMissingFrom_exceptionThrown() {
        String trimmedInput = "event project meeting /to 2025-12-02 1400";
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand(trimmedInput));
        assertTrue(e.getMessage().contains("The event is missing \"/from\""));
    }

    /**
     * Tests that an "event" command missing the "/to" part throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_eventMissingTo_exceptionThrown() {
        String trimmedInput = "event project meeting /from 2025-12-02 1200";
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand(trimmedInput));
        assertTrue(e.getMessage().contains("The event is missing \"/to\""));
    }

    /**
     * Tests that an "event" command with invalid date-time format throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_eventInvalidDateTimeFormat_exceptionThrown() {
        String trimmedInput = "event project meeting /from Monday 12pm /to 2pm";
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand(trimmedInput));
        assertTrue(e.getMessage().contains("The format of date and time entered is invalid"));
    }

    /**
     * Tests that a valid "delete" command is correctly parsed into {@link DeleteCommand}.
     */
    @Test
    public void parseToCommand_delete_success() throws MomoException {
        String trimmedInput = "delete 2";
        Command command = Parser.parseToCommand(trimmedInput);
        assertInstanceOf(DeleteCommand.class, command);
    }

    /**
     * Tests that a "delete" command with missing task number throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_deleteMissingTaskNumber_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("delete"));
        assertTrue(e.getMessage().contains("The task number to delete is not provided"));
    }

    /**
     * Tests that a "delete" command with a non-integer task number throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_deleteTaskNumberNotInteger_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("delete two"));
        assertTrue(e.getMessage().contains("The task number provided is not an integer"));
    }

    /**
     * Tests that a valid "mark" command is correctly parsed into {@link MarkCommand}.
     */
    @Test
    public void parseToCommand_mark_success() throws MomoException {
        String trimmedInput = "mark 2";
        Command command = Parser.parseToCommand(trimmedInput);
        assertInstanceOf(MarkCommand.class, command);
    }

    /**
     * Tests that a "mark" command with missing task number throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_markMissingTaskNumber_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("mark"));
        assertTrue(e.getMessage().contains("The task number to mark is not provided"));
    }

    /**
     * Tests that a "mark" command with a non-integer task number throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_markTaskNumberNotInteger_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("mark two"));
        assertTrue(e.getMessage().contains("The task number provided is not an integer"));
    }

    /**
     * Tests that a valid "unmark" command is correctly parsed into {@link UnmarkCommand}.
     */
    @Test
    public void parseToCommand_unmark_success() throws MomoException {
        String trimmedInput = "unmark 2";
        Command command = Parser.parseToCommand(trimmedInput);
        assertInstanceOf(UnmarkCommand.class, command);
    }

    /**
     * Tests that an "unmark" command with missing task number throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_unmarkMissingTaskNumber_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("unmark"));
        assertTrue(e.getMessage().contains("The task number to unmark is not provided"));
    }

    /**
     * Tests that an "unmark" command with a non-integer task number throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_unmarkTaskNumberNotInteger_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("unmark two"));
        assertTrue(e.getMessage().contains("The task number provided is not an integer"));
    }

    /**
     * Tests that an invalid command string throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_invalidCommand_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand("blah"));
        assertTrue(e.getMessage().contains("is not a valid command"));
    }

    /**
     * Tests that an empty input string throws a {@link MomoException}.
     */
    @Test
    public void parseToCommand_emptyInput_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToCommand(""));
        assertTrue(e.getMessage().contains("is not a valid command"));
    }

    /**
     * Tests that a saved "todo" task string is correctly parsed into {@link Todo}.
     */
    @Test
    public void parseToTask_todo_success() throws MomoException {
        Task task = Parser.parseToTask("T | 0 | read book");
        assertInstanceOf(Todo.class, task);
        assertEquals("read book", task.getDescription());
        assertFalse(task.isDone());
    }

    //CHECKSTYLE.OFF: SeparatorWrap
    /**
     * Tests that a saved "deadline" task string is correctly parsed into {@link Deadline}.
     */
    @Test
    public void parseToTask_deadline_success() throws MomoException {
        Task task = Parser.parseToTask("D | 1 | return book | 2025-12-02 1800");
        assertInstanceOf(Deadline.class, task);
        assertEquals("return book", task.getDescription());
        assertTrue(task.isDone());
        assertEquals(LocalDateTime.of(2025, 12, 2, 18, 0),
                ((Deadline) task).getBy());
    }
    //CHECKSTYLE.ON: SeparatorWrap

    //CHECKSTYLE.OFF: SeparatorWrap
    /**
     * Tests that a saved "event" task string is correctly parsed into {@link Event}.
     */
    @Test
    public void parseToTask_event_success() throws MomoException {
        Task task = Parser.parseToTask("E | 0 | meeting | 2025-12-02 1200 | 2025-12-02 1400");
        assertInstanceOf(Event.class, task);
        assertEquals("meeting", task.getDescription());
        assertFalse(task.isDone());
        assertEquals(LocalDateTime.of(2025, 12, 2, 12, 0),
                ((Event) task).getFrom());
        assertEquals(LocalDateTime.of(2025, 12, 2, 14, 0),
                ((Event) task).getTo());
    }
    //CHECKSTYLE.ON: SeparatorWrap

    /**
     * Tests that a task string with an invalid type throws a {@link MomoException}.
     */
    @Test
    void parseToTask_invalidTaskType_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToTask("L | 0 | meeting"));
        assertTrue(e.getMessage().contains("Invalid task type found"));
    }

    /**
     * Tests that a task string missing required data throws a {@link MomoException}.
     */
    @Test
    void parseToTask_missingData_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToTask("E | 0 | meeting"));
        assertTrue(e.getMessage().contains("Missing data from tasks"));
    }

    //CHECKSTYLE.OFF: SeparatorWrap
    /**
     * Tests that a task string with invalid date-time format throws a {@link MomoException}.
     */
    @Test
    void parseToTask_invalidDateTimeFormat_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class,
                () -> Parser.parseToTask("E | 0 | meeting | Monday 2pm | 4pm"));
        assertTrue(e.getMessage().contains("Format of date and time is invalid"));
    }
    //CHECKSTYLE.ON: SeparatorWrap

    /**
     * Tests that an empty task string throws a {@link MomoException}.
     */
    @Test
    public void parseToTask_emptyInput_exceptionThrown() {
        MomoException e = assertThrows(MomoException.class, () -> Parser.parseToTask(""));
        assertTrue(e.getMessage().contains("Missing data from tasks"));
    }
}
