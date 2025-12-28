package thecoolerduke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static thecoolerduke.main.Command.parseInput;

import org.junit.jupiter.api.Test;

import thecoolerduke.exceptions.InvalidFormatException;

public class CommandTest {

    @Test
    public void testValidCommand() {
        assertEquals(Command.LIST_TASK, Command.validateCommand("list"));
    }

    @Test
    public void testInvalidCommand() {
        assertNull(Command.validateCommand("ls"));
    }

    @Test
    public void testCaseSensitivity() {
        assertNull(Command.validateCommand("LIST"));
    }

    @Test
    public void testParseTodo() throws Exception {
        String input = "do homework /p high";
        String[] result = parseInput(input, new String[]{"/p"});
        assertEquals("do homework", result[0]);
        assertEquals("high", result[1]);
    }

    @Test
    public void testParseDeadline() throws Exception {
        String input = "finish project /p medium /by 12/09/2025 18:00";
        String[] result = parseInput(input, new String[]{"/p", "/by"});
        assertEquals("finish project", result[0]);
        assertEquals("medium", result[1]);
        assertEquals("12/09/2025 18:00", result[2]);
    }

    @Test
    public void testParseEvent() throws Exception {
        String input = "meeting /p low /from 12/09/2025 14:00 /to 12/09/2025 16:00";
        String[] result = parseInput(input, new String[]{"/p", "/from", "/to"});
        assertEquals("meeting", result[0]);
        assertEquals("low", result[1]);
        assertEquals("12/09/2025 14:00", result[2]);
        assertEquals("12/09/2025 16:00", result[3]);
    }

    @Test
    public void testParseInputWithExtraSpaces() throws Exception {
        String input = "task    /p   high   /by   12/09/2025 18:00";
        String[] result = parseInput(input, new String[]{"/p", "/by"});
        assertEquals("task", result[0]);
        assertEquals("high", result[1]);
        assertEquals("12/09/2025 18:00", result[2]);
    }

    @Test
    //Used ChatGPT to refine testing of invalid modifiers, e.g. asserting InvalidFormatException's message
    public void testMissingMultipleModifiers() {
        String modifierString = "finish project only";
        String [] acceptedModifiers = new String[]{"/p", "/by"};
        InvalidFormatException e = assertThrowsExactly(
                InvalidFormatException.class, () -> parseInput(modifierString, acceptedModifiers));
        assertTrue(e.getMessage().contains("/p"));
        assertTrue(e.getMessage().contains("/by"));
    }

    @Test
    //Used ChatGPT to refine testing of invalid modifiers, e.g. checking for empty parameters,
    //including whitespaces
    public void testEmptyParameterAfterModifier() {
        String modifierString = "finish project /p /by 12/09/2025 18:00";
        String [] acceptedModifiers = new String[]{"/p", "/by"};
        assertThrowsExactly(InvalidFormatException.class, () -> parseInput(modifierString, acceptedModifiers));
    }
}
