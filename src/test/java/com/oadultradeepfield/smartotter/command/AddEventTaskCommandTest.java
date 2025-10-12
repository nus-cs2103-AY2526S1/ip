package com.oadultradeepfield.smartotter.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.oadultradeepfield.smartotter.SmartOtterException;

class AddEventTaskCommandTest {
    @Test
    void testFromInputValidFormat() throws SmartOtterException {
        String input = "project meeting /from 12/02/2019 1800 /to 12/02/2019 1900";

        AddEventTaskCommand command = (AddEventTaskCommand) AddEventTaskCommand.fromInput(input);

        assertNotNull(command);
    }

    @Test
    void testFromInputMissingFromKeyword() {
        String input = "project meeting 12/02/2019 1800 /to 12/02/2019 1900";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> AddEventTaskCommand.fromInput(input));

        assertEquals(
            "😵‍💫 Oops! - Incorrect event format! Use: event <taskName> /from <start> /to <end>",
            exception.getMessage());
    }

    @Test
    void testFromInputMissingToKeyword() {
        String input = "project meeting /from 12/02/2019 1800 12/02/2019 1900";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> AddEventTaskCommand.fromInput(input));

        assertEquals(
            "😵‍💫 Oops! - Incorrect event format! Use: event <taskName> /from <start> /to <end>",
            exception.getMessage());
    }

    @Test
    void testFromInputEmptyTaskName() {
        String input = " /from 12/02/2019 1800 /to 12/02/2019 1900";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> AddEventTaskCommand.fromInput(input));

        assertEquals(
            "😵‍💫 Oops! - Task name, start, and end time cannot be empty.", exception.getMessage());
    }

    @Test
    void testFromInputEmptyStartTime() {
        String input = "project meeting /from  /to 12/02/2019 1900";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> AddEventTaskCommand.fromInput(input));

        assertEquals(
            "😵‍💫 Oops! - Task name, start, and end time cannot be empty.", exception.getMessage());
    }

    @Test
    void testFromInputEmptyEndTime() {
        String input = "project meeting /from 12/02/2019 1800 /to ";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> AddEventTaskCommand.fromInput(input));

        assertEquals(
            "😵‍💫 Oops! - Task name, start, and end time cannot be empty.", exception.getMessage());
    }
}
