package stewie.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link CommandException} and its subclasses.
 */
class CommandExceptionsTest {

    @Test
    void commandException_withMessage_messageWrappedCorrectly() {
        CommandException ex = new CommandException("test message");
        String expected = "What's this? You've uttered a complete and utter nonsense phrase.\n"
                + "test message";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    void invalidCommandException_withMessage_messageWrappedCorrectly() {
        InvalidCommandException ex = new InvalidCommandException("todo <desc>");
        String expected = "What's this? You've uttered a complete and utter nonsense phrase.\n"
                + "I've provided the correct format for your simpleton mind.\n"
                + "Follow it precisely: todo <desc>";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    void outOfRangeException_withMessage_messageWrappedCorrectly() {
        OutOfRangeException ex = new OutOfRangeException("Index out of bounds");
        String expected = "What's this? You've uttered a complete and utter nonsense phrase.\n"
                + "The numbers you've provided are outside the acceptable parameters.\n"
                + "Index out of bounds";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    void unknownCommandException_withMessage_messageWrappedCorrectly() {
        UnknownCommandException ex = new UnknownCommandException("list, todo, bye");
        String expected = "What's this? You've uttered a complete and utter nonsense phrase.\n"
                + " The available commands are listed for your feeble mind to comprehend: \n"
                + "list, todo, bye";
        assertEquals(expected, ex.getMessage());
    }
}
