package com.oadultradeepfield.smartotter.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.oadultradeepfield.smartotter.SmartOtterException;

class CommandParserFuzzyMatchTest {
    private final CommandParser parser = new CommandParser();

    @Test
    void testFuzzyMatchValidCommand() throws SmartOtterException {
        String input = "lsit";

        Executable command = parser.parse(input);

        assertNotNull(command);
        assertEquals("listcommand", command.getClass().getSimpleName().toLowerCase());
    }

    @Test
    void testFuzzyMatchWithArgument() throws SmartOtterException {
        String input = "delte 5";

        Executable command = parser.parse(input);

        assertNotNull(command);
        assertEquals("deletecommand", command.getClass().getSimpleName().toLowerCase());
    }

    @Test
    void testFuzzyMatchInvalidCommandThrows() {
        String input = "nonsense";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> parser.parse(input));

        assertEquals("😵‍💫 Oops! - Unknown command: 'nonsense'. Please try again.", exception.getMessage());
    }

    @Test
    void testFuzzyMatchNoArgCommandWithExtraArgsThrows() {
        String input = "lsit extra";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> parser.parse(input));

        assertEquals("😵‍💫 Oops! - Command 'list' does not take any arguments", exception.getMessage());
    }
}
