package baymax.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import baymax.command.AddCommand;
import baymax.command.ExitCommand;
import baymax.command.ListCommand;
import baymax.command.UpdateCommand;
import baymax.exception.BaymaxException;

public class ParserTest {
    @Test
    public void parseCommand_success() throws BaymaxException {

        assertInstanceOf(ListCommand.class, Parser.parse("list"));

        assertInstanceOf(AddCommand.class, Parser.parse("todo read book"));
        assertInstanceOf(AddCommand.class, Parser.parse("deadline complete homework /by 2025-08-31"));
        assertInstanceOf(AddCommand.class, Parser.parse("event dinner /from 2pm /to 4pm"));

        assertInstanceOf(UpdateCommand.class, Parser.parse("mark 1"));
        assertInstanceOf(UpdateCommand.class, Parser.parse("unmark 1"));
        assertInstanceOf(UpdateCommand.class, Parser.parse("delete 1"));

        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
    }

    @Test
    public void parseCommand_unknownCommand_exceptionThrown() {
        assertThrows(
                BaymaxException.InvalidCommandException.class, () -> Parser.parse("hi")
        );
    }

    @Test
    public void todoCommand_missingDescription_exceptionThrown() {
        assertThrows(
                BaymaxException.MissingDescriptionException.class, () -> Parser.parse("todo")
        );
    }

    @Test
    public void deadlineCommand_missingDescription_exceptionThrown() {
        assertThrows(
                BaymaxException.MissingDescriptionException.class, () -> Parser.parse("deadline")
        );
    }

    @Test
    public void deadlineCommand_missingDeadline_exceptionThrown() {
        assertThrows(
                BaymaxException.MissingDeadlineException.class, () -> Parser.parse("deadline complete homework /by")
        );
    }

    @Test
    public void deadlineCommand_invalidDeadline_exceptionThrown() {
        assertThrows(
                BaymaxException.InvalidDateException.class, () -> Parser.parse(
                        "deadline complete homework /by tomorrow")
        );
    }

    @Test
    public void eventCommand_missingDescription_exceptionThrown() {
        assertThrows(
                BaymaxException.MissingDescriptionException.class, () -> Parser.parse("event")
        );
    }

    @Test
    public void eventCommand_missingArguments_exceptionThrown() {
        assertThrows(
                BaymaxException.MissingEventDetailsException.class, () -> Parser.parse("event dinner /from")
        );
    }
}
