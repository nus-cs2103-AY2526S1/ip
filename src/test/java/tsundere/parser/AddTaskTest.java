package tsundere.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tsundere.command.InvalidFormatCommand.Format;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import tsundere.TsundereException;
import tsundere.command.*;
import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.TodoTask;



public class AddTaskTest {
    @Test
    public void parse_emptyInput_returnsHelpCommand() throws TsundereException {
        String userInput = "";
        AbstractCommand expected = new InvalidFormatCommand(Format.HELP);

        AbstractCommand output = CommandParser.parse(userInput);

        assertEquals(expected, output, "empty input should return InvalidFormatCommand for help");
    }

    @Test
    public void parse_validTodoInput_returnsTodoCommand() throws TsundereException {
        String userInput = "todo task";
        AbstractCommand expected = new AddTaskCommand(new TodoTask("task"));

        AbstractCommand output = CommandParser.parse(userInput);

        assertEquals(expected, output, "valid input should return AddTaskCommand with todo task");
    }

    @Test
    public void parse_invalidTodoInput_returnsInvalidFormatCommand() throws TsundereException {
        String userInput = "todo";
        AbstractCommand expected = new InvalidFormatCommand(Format.TODO);

        AbstractCommand output = CommandParser.parse(userInput);

        assertEquals(expected, output, "no name supplied should return InvalidFormatCommand for todo");
    }

    @Test
    public void parse_validDeadlineInput_returnsDeadlineCommand() throws TsundereException {
        String normalInput = "deadline task /by 2025-05-25T23:59";
        String longNameInput = "deadline super important task /by 2025-05-25T23:59";

        AddTaskCommand expectedNormalOutput = new AddTaskCommand(
                new DeadlineTask("task", LocalDateTime.parse("2025-05-25T23:59")));
        AddTaskCommand expectedLongNameOutput = new AddTaskCommand(
                new DeadlineTask("super important task", LocalDateTime.parse("2025-05-25T23:59")));

        AbstractCommand normalOutput = CommandParser.parse(normalInput);
        AbstractCommand longNameOutput = CommandParser.parse(longNameInput);

        assertEquals(expectedNormalOutput, normalOutput,
                "valid input should return AddTaskCommand with deadline task");
        assertEquals(expectedLongNameOutput, longNameOutput,
                "valid input should return AddTaskCommand with deadline task");
    }

    @Test
    public void parse_invalidDeadlineInput_returnsInvalidFormatCommand() throws TsundereException {
        String emptyNameInput = "deadline /by 2025-05-25T23:59";
        String emptyByInput = "deadline task /by";
        String missingByInput = "deadline task";
        String invalidDateInput = "deadline task /by today";

        AbstractCommand expected = new InvalidFormatCommand(Format.DEADLINE);

        AbstractCommand output1 = CommandParser.parse(emptyNameInput);
        AbstractCommand output2 = CommandParser.parse(emptyByInput);
        AbstractCommand output3 = CommandParser.parse(missingByInput);
        AbstractCommand output4 = CommandParser.parse(invalidDateInput);

        assertEquals(expected, output1, "empty name input should return InvalidFormatCommand for event");
        assertEquals(expected, output2, "empty by input should return InvalidFormatCommand for event");
        assertEquals(expected, output3, "missing by input should return InvalidFormatCommand for event");
        assertEquals(expected, output4, "invalid date input should return InvalidFormatCommand for event");
    }

    @Test
    public void parse_validEventInput_returnsEventCommand() throws TsundereException {
        String userInput = "event task /from 2025-05-25T23:59 /to 2025-05-26T23:59";
        String name = "task";
        String from = "2025-05-25T23:59";
        String to = "2025-05-26T23:59";
        AddTaskCommand expected = new AddTaskCommand(
                new EventTask(name, LocalDateTime.parse(from), LocalDateTime.parse(to)));

        AbstractCommand output = CommandParser.parse(userInput);

        assertEquals(expected, output, "valid input should return AddTaskCommand with event task");
    }

    @Test
    public void parse_invalidEventInput_returnsInvalidFormatCommand() throws TsundereException {
        String emptyNameInput = "event /from 2025-05-25T23:59 /to 2025-05-26T23:59";
        String emptyFromInput = "event task /from /to 2025-05-26T23:59";
        String emptyToInput = "event task /from 2025-05-25T23:59 /to";
        String missingFromInput = "event task /to 2025-05-26T23:59";
        String missingToInput = "event task /from 2025-05-25T23:59";
        String wrongOrderInput = "event task /to 2025-05-26T23:59 /from 2025-05-25T23:59";

        InvalidFormatCommand expected = new InvalidFormatCommand(Format.EVENT);

        AbstractCommand output1 = CommandParser.parse(emptyNameInput);
        AbstractCommand output2 = CommandParser.parse(emptyFromInput);
        AbstractCommand output3 = CommandParser.parse(emptyToInput);
        AbstractCommand output4 = CommandParser.parse(missingFromInput);
        AbstractCommand output5 = CommandParser.parse(missingToInput);
        AbstractCommand output6 = CommandParser.parse(wrongOrderInput);

        assertEquals(expected, output1, "empty name input should return InvalidFormatCommand for event");
        assertEquals(expected, output2, "empty from input should return InvalidFormatCommand for event");
        assertEquals(expected, output3, "empty to input should return InvalidFormatCommand for event");
        assertEquals(expected, output4, "missing from input should return InvalidFormatCommand for event");
        assertEquals(expected, output5, "missing to input should return InvalidFormatCommand for event");
        assertEquals(expected, output6, "wrong order input should return InvalidFormatCommand for event");
    }

    @Test
    public void parse_invalidDatesEventInput_returnsInvalidFormatCommand() throws TsundereException {
        String invalidMinutes = "event task /from 2025-05-25T23:60 /to 2025-05-26T23:60";

        InvalidFormatCommand expected = new InvalidFormatCommand(Format.EVENT);

        AbstractCommand output = CommandParser.parse(invalidMinutes);

        assertEquals(expected, output, "wrong date should return InvalidFormatCommand for event");
    }

    @Test
    public void parse_fromDateAfterToDateEventInput_returnsInvalidFormatCommand() throws TsundereException {
        String toDateBeforeFromDate = "event task /from 2025-05-26T23:59 /to 2025-05-25T23:59";

        InvalidFormatCommand expected = new InvalidFormatCommand(Format.EVENT_DATE);

        AbstractCommand output = CommandParser.parse(toDateBeforeFromDate);

        assertEquals(expected, output,
                "invalid date range should return InvalidFormatCommand for event_date");
    }
}
