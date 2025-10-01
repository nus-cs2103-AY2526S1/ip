package panda;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import panda.action.AddAction;

public class InputParserTest {

    @Test
    public void parseUserInput_addDeadline_exceptionThrown() {
        String action = "deadline";
        String[] taskInfo = {"test", "2025-09-01 10:00"};
        String input = "deadline test by 2025-09-01 10:00";
        String exceptionMessage = "Please provide the deadline as such: \n"
                + "deadline (insert description) /by (insert deadline dateTime)";
        try {
            assertEquals(new AddAction(action, taskInfo), new InputParser().parseUserInput(input));
        } catch (PandaException e) {
            assertEquals(exceptionMessage, e.getMessage());
        }
    }
}
