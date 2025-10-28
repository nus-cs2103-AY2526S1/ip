package parse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.EventTimelineInvalid;
import exceptions.InvalidInput;
import exceptions.NoDeadlineProvided;
import storetasks.TaskList;

/**
 * This function tests the method validityOfWords in the Parser class.
 * It has a variety of inputs, both some of which can for there to be an error
 * and valid inputs.
 */
public class ParserTest {


    // Solution below inspired by
    // https://stackoverflow.com/questions/10148101/junit-testing-assertequals-for-exception
    // I used it to get ideas on how to compare an error message vs
    // just valid input.
    @Test
    public void blankSpace() throws Exception {
        try {
            TaskList e = new TaskList();
            e.addToList("todo happy birthday");
            e.addToList("deadline eat potates /by 2025-04-03");
            e.addToList("event eat lunch /from 2025-04-03 /to 2025-04-05");
            String actual = Parser.validityOfWords("", e);
            assertEquals("", actual);
        } catch (InvalidInput e) {
            String expectedErrorOutput = "The input is invalid.";
            assertEquals(expectedErrorOutput, e.getMessage());
        }
    }

    @Test
    public void invalidInput() throws Exception {
        try {
            TaskList e = new TaskList();
            e.addToList("todo happy birthday");
            e.addToList("deadline eat potates /by 2025-04-03");
            e.addToList("event eat lunch /from 2025-04-03 /to 2025-04-05");
            String actual = Parser.validityOfWords("hiiiiiii", e);
            assertEquals("", actual);
        } catch (InvalidInput e) {
            String expectedErrorOutput = "The input is invalid.";
            assertEquals(expectedErrorOutput, e.getMessage());
        }
    }

    @Test
    public void invalidEventTime() throws Exception {
        try {
            TaskList e = new TaskList();
            e.addToList("todo happy birthday");
            e.addToList("deadline eat potates /by 2025-04-03");
            e.addToList("event eat lunch /from 2025-04-03 /to 2025-04-05");
            String actual = Parser.validityOfWords("event eat lunch "
                            + "/from 2025-04-03 /to 2025-04-02", e);
            assertEquals("", actual);
        } catch (EventTimelineInvalid e) {
            String expectedErrorOutput = "Your starting date is after your ending date";
            assertEquals(expectedErrorOutput, e.getMessage());
        }
    }

    @Test
    public void noDeadlineProvided() throws Exception {
        try {
            TaskList e = new TaskList();
            e.addToList("todo happy birthday");
            e.addToList("deadline eat potates /by 2025-04-03");
            e.addToList("event eat lunch /from 2025-04-03 /to 2025-04-05");
            String actual = Parser.validityOfWords("deadline do CS2100 homework", e);
            assertEquals("", actual);
        } catch (NoDeadlineProvided e) {
            String expectedErrorOutput = "No deadline is provided, please add one.";
            assertEquals(expectedErrorOutput, e.getMessage());
        }
    }

    @Test
    public void noEndingTimeForEvent() throws Exception {
        try {
            TaskList e = new TaskList();
            e.addToList("todo happy birthday");
            e.addToList("deadline eat potates /by 2025-04-03");
            e.addToList("event eat lunch /from 2025-04-03 /to 2025-04-02");
            String actual = Parser.validityOfWords("event do CS2100 "
                    + "homework /from 2025-04-03", e);
            assertEquals("", actual);
        } catch (EventTimelineInvalid e) {
            String expectedErrorOutput = "Your starting date is after your ending date";
            assertEquals(expectedErrorOutput, e.getMessage());
        }
    }

    @Test
    public void addingValidTodo() throws Exception {
        TaskList e = new TaskList();
        e.addToList("todo happy birthday");
        e.addToList("deadline eat potates /by 2025-04-03");
        e.addToList("event eat lunch /from 2025-04-03 /to 2025-04-05");
        String actual = Parser.validityOfWords("todo do CS2100 homework", e);
        assertEquals("Got it, I have added this to my list!\n"
                + "[ToDo] [O] do CS2100 homework \n"
                + "Now, you have 38 objects in your list right now!", actual);
    }

    @Test
    public void addingValidDeadline() throws Exception {
        TaskList e = new TaskList();
        e.addToList("todo happy birthday");
        e.addToList("deadline eat potates /by 2025-04-03");
        e.addToList("event eat lunch /from 2025-04-03 /to 2025-04-05");
        String actual = Parser.validityOfWords("deadline do CS2100 "
                + "homework /by 2025-05-04", e);
        assertEquals("Got it, I have added this to my list!\n"
                + "[Deadline] [O] do CS2100 homework  (by: May 4 2025) \n"
                + "Now, you have 50 objects in your list right now!", actual);
    }

    @Test
    public void addingValidEvent() throws Exception {
        TaskList e = new TaskList();
        e.addToList("todo happy birthday");
        e.addToList("deadline eat potates /by 2025-04-03");
        String actual = Parser.validityOfWords("event eat lunch /from "
                + "2025-04-03 /to 2025-04-05", e);
        assertEquals("Got it, I have added this to my list!\n"
                + "[Events] [O] eat lunch  (from: Apr 3 2025 to: Apr 5 2025) \n"
                + "Now, you have 46 objects in your list right now!", actual);
    }
}
