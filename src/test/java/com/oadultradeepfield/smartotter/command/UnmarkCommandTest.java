package com.oadultradeepfield.smartotter.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.oadultradeepfield.smartotter.SmartOtterException;

class UnmarkCommandTest {
    @Test
    void testFromInputValidNumber() throws SmartOtterException {
        String input = "1";

        UnmarkCommand command = (UnmarkCommand) UnmarkCommand.fromInput(input);

        assertNotNull(command);
        assertEquals(1, command.taskNumber());
    }

    @Test
    void testFromInputValidLargerNumber() throws SmartOtterException {
        String input = "25";

        UnmarkCommand command = (UnmarkCommand) UnmarkCommand.fromInput(input);

        assertNotNull(command);
        assertEquals(25, command.taskNumber());
    }

    @Test
    void testFromInputInvalidFormat() {
        String input = "abc";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> UnmarkCommand.fromInput(input));

        assertEquals("😵‍💫 Oops! - Invalid task number: abc", exception.getMessage());
    }

    @Test
    void testFromInputDecimalNumber() {
        String input = "1.5";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> UnmarkCommand.fromInput(input));

        assertEquals("😵‍💫 Oops! - Invalid task number: 1.5", exception.getMessage());
    }

    @Test
    void testFromInputEmptyString() {
        String input = "";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> UnmarkCommand.fromInput(input));

        assertEquals("😵‍💫 Oops! - Invalid task number: ", exception.getMessage());
    }

    @Test
    void testFromInputNegativeNumber() throws SmartOtterException {
        String input = "-1";

        UnmarkCommand command = (UnmarkCommand) UnmarkCommand.fromInput(input);

        assertEquals(-1, command.taskNumber());
    }
}
