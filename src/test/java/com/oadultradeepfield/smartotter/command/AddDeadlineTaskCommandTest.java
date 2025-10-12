package com.oadultradeepfield.smartotter.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.oadultradeepfield.smartotter.SmartOtterException;

class AddDeadlineTaskCommandTest {
    @Test
    void testFromInputValidFormat() throws SmartOtterException {
        String input = "return book /by 12/02/2019 1430";

        AddDeadlineTaskCommand command =
            (AddDeadlineTaskCommand) AddDeadlineTaskCommand.fromInput(input);

        assertNotNull(command);
    }

    @Test
    void testFromInputMissingByKeyword() {
        String input = "return book 12/02/2019 1430";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> AddDeadlineTaskCommand.fromInput(input));

        assertEquals(
            "😵‍💫 Oops! - Incorrect deadline format! Use: deadline <taskName> /by <time>",
            exception.getMessage());
    }

    @Test
    void testFromInputEmptyTaskName() {
        String input = " /by 12/02/2019 1430";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> AddDeadlineTaskCommand.fromInput(input));

        assertEquals("😵‍💫 Oops! - Task name and deadline cannot be empty", exception.getMessage());
    }

    @Test
    void testFromInputEmptyDeadline() {
        String input = "return book /by ";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> AddDeadlineTaskCommand.fromInput(input));

        assertEquals("😵‍💫 Oops! - Task name and deadline cannot be empty", exception.getMessage());
    }
}
