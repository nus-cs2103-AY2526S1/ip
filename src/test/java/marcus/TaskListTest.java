package marcus;

import marcus.exception.InvalidIndexError;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void testMark() {
        try {
            TaskList taskList = new TaskList();
            taskList.add("sleep", LocalDate.parse("2025-09-03"));
            taskList.add("add JUnit tests", "yesterday", "today");
            assertEquals("A brand new chapter complete!\n" +
                    "[D][X] sleep (by: Sep 3 2025)", taskList.mark(1));
        } catch (InvalidIndexError e) {
            fail();
        }
    }

    @Test
    public void testUnmark() {
        try {
            TaskList taskList = new TaskList();
            taskList.add("sleep", LocalDate.parse("2025-09-03"));
            taskList.add("add JUnit tests", "yesterday", "today");
            taskList.mark(1);
            assertEquals("You forgot about this chapter...\n" +
                    "[D][ ] sleep (by: Sep 3 2025)", taskList.unmark(1));
        } catch (InvalidIndexError e) {
            fail();
        }
    }

    @Test
    public void testUnmarkLargeInvalidIndex() {
        try {
            TaskList taskList = new TaskList();
            taskList.unmark(1000);
            fail();
        } catch (InvalidIndexError e) {
            assertEquals("That chapter does not exist in your story", e.getMessage());
        }
    }

    @Test
    public void testMarkLargeInvalidIndex() {
        try {
            TaskList taskList = new TaskList();
            taskList.mark(1000);
            fail();
        } catch (InvalidIndexError e) {
            assertEquals("That chapter does not exist in your story", e.getMessage());
        }
    }

    @Test
    public void testMarkNegativeInvalidIndex() {
        try {
            TaskList taskList = new TaskList();
            taskList.mark(-1);
            fail();
        } catch (InvalidIndexError e) {
            assertEquals("That chapter does not exist in your story", e.getMessage());
        }
    }

    @Test
    public void testUnmarkNegativeInvalidIndex() {
        try {
            TaskList taskList = new TaskList();
            taskList.unmark(-1);
            fail();
        } catch (InvalidIndexError e) {
            assertEquals("That chapter does not exist in your story", e.getMessage());
        }
    }
}
