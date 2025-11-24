package shaduke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * JUnit tests for the Deadline class.
 */
public class DeadlineTest {
    @Test
    public void input_validDate_success() {
        Deadline deadline = new Deadline("Run UTown Loops", "2025-04-17");
        assertEquals("[D][ ] Run UTown Loops (by: Apr 17 2025) ", deadline.toString());
    }

    @Test
    public void input_wrongDateFormat_exceptionThrown() {
        try {
            Deadline dead = new Deadline("name", "17 April");
            fail();
        } catch (Exception e) {
            assertEquals("Sorry! Please use yyyy-mm-dd format for deadlines!", e.getMessage());
        }
    }

    @Test
    public void store_notDone_correctFormat() {
        Deadline deadline = new Deadline("Run UTown Loops", "2025-04-17");
        assertEquals("D | 0 | Run UTown Loops | 2025-04-17", deadline.store());
    }

    @Test
    public void store_done_correctFormat() {
        Deadline deadline = new Deadline("Submit report", "2025-09-20", true);
        assertEquals("D | 1 | Submit report | 2025-09-20", deadline.store());
    }

    @Test
    public void toString_doneTask_correctFormat() {
        Deadline deadline = new Deadline("Submit report", "2025-09-20", true);
        assertEquals("[D][X] Submit report (by: Sep 20 2025) ", deadline.toString());
    }

    @Test
    public void input_multipleValidDates_success() {
        Deadline d1 = new Deadline("Task 1", "2025-01-01");
        Deadline d2 = new Deadline("Task 2", "2025-12-31");

        assertEquals("[D][ ] Task 1 (by: Jan 01 2025) ", d1.toString());
        assertEquals("[D][ ] Task 2 (by: Dec 31 2025) ", d2.toString());
    }
}
