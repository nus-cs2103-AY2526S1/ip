package storetasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.InvalidInput;

/**
 * This function tests the method find in the TaskListTest class.
 * It has a variety of inputs, the majority of which do not cause errors
 * since not many errors are thrown in find.
 */
public class TaskListTest {

    @Test
    public void oneStringMatch() throws Exception {
        TaskList e = new TaskList();
        e.addToList("todo happy birthday");
        e.addToList("deadline eat potates /by 2025-04-03");
        e.addToList("event eat lunch /from 2025-04-03 /to 2025-04-05");
        String actual = e.find("lunch");
        assertEquals("Here are the matching tasks in your lists:\n"
                + "[Events] [O] eat lunch  (from: Apr 3 2025 to: Apr 5 2025) \n", actual);
    }

    @Test
    public void allStringsMatch() throws Exception {
        TaskList e = new TaskList();
        e.addToList("todo happy birthday");
        e.addToList("deadline happy birthday /by 2025-04-03");
        e.addToList("event happy birthday /from 2025-04-03 /to 2025-04-05");
        String actual = e.find("happy");
        assertEquals("Here are the matching tasks in your lists:\n"
                + "[ToDo] [O] happy birthday \n"
                + "[Deadline] [O] happy birthday  (by: Apr 3 2025) \n"
                + "[Events] [O] happy birthday  (from: Apr 3 2025 to: Apr 5 2025) \n", actual);
    }

    @Test
    public void noStringsMatch() throws Exception {
        TaskList e = new TaskList();
        e.addToList("todo happy birthday");
        e.addToList("deadline happy birthday /by 2025-04-03");
        e.addToList("event happy birthday /from 2025-04-03 /to 2025-04-05");
        String actual = e.find("today");
        assertEquals("There are no matches.", actual);
    }

    @Test
    public void findingHashTagsMatches() throws Exception {
        TaskList e = new TaskList();
        e.addToList("todo happy birthday #happy");
        e.addToList("deadline happy birthday #tags /by 2025-04-03");
        e.addToList("event happy birthday #tags /from 2025-04-03 /to 2025-04-05");
        String actual = e.find("#tags");
        assertEquals("Here are the matching tasks in your lists:\n"
                + "[Deadline] [O] happy birthday  (by: Apr 3 2025) #tags \n"
                + "[Events] [O] happy birthday  (from: Apr 3 2025 to: "
                + "Apr 5 2025) #tags \n", actual);
    }


    @Test
    public void blankSpaceAsFinder() throws Exception {
        try {
            TaskList e = new TaskList();
            e.addToList("todo happy birthday");
            e.addToList("deadline eat potates /by 2025-04-03");
            e.addToList("event eat lunch /from 2025-04-03 /to 2025-04-05");
            String actual = e.find("");
            assertEquals("", actual);
        } catch (InvalidInput e) {
            String expectedErrorOutput = "The input is invalid.";
            assertEquals(expectedErrorOutput, e.getMessage());
        }
    }
}
