package jimbot.command;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import jimbot.exception.InvalidInputException;
import jimbot.exception.InvalidToDoException;
import jimbot.exception.JimbotException;

/**
 * Unit tests for the Commands enum, which maps string inputs into
 * concrete Command objects using a factory pattern.
 * Note: AI assistance was used to aid in the creation of this test class.
 *
 * @author limjimin-nus
 */
class CommandsTest {

    @Test
    void testHiCommand() throws JimbotException {
        Command cmd = Commands.fromString("hi", "hi");
        assertNotNull(cmd);
        assertInstanceOf(GreetCommand.class, cmd);
    }

    @Test
    void testByeCommand() throws JimbotException {
        Command cmd = Commands.fromString("bye", "bye");
        assertNotNull(cmd);
        assertInstanceOf(ExitCommand.class, cmd);
    }

    @Test
    void testTodoCommand() throws JimbotException {
        Command cmd = Commands.fromString("todo read book", "todo");
        assertNotNull(cmd);
        assertInstanceOf(AddToDoCommand.class, cmd);
    }

    @Test
    void testDeadlineCommand() throws JimbotException {
        Command cmd = Commands.fromString("deadline submit report /by tomorrow", "deadline");
        assertNotNull(cmd);
        assertInstanceOf(AddDeadlineCommand.class, cmd);
    }

    @Test
    void testEventCommand() throws JimbotException {
        Command cmd = Commands.fromString("event meeting /at office", "event");
        assertNotNull(cmd);
        assertInstanceOf(AddEventCommand.class, cmd);
    }

    @Test
    void testDateCommandWithSlash() throws JimbotException {
        Command cmd = Commands.fromString("2025/09/18", "2025/09/18");
        assertNotNull(cmd);
        assertInstanceOf(FindDateCommand.class, cmd);
    }

    @Test
    void testEmptyInputThrowsInvalidToDoException() {
        assertThrows(InvalidToDoException.class, () -> Commands.fromString("", ""));
    }

    @Test
    void testUnknownCommandThrowsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> Commands.fromString("foobar", "foobar"));
    }
}

