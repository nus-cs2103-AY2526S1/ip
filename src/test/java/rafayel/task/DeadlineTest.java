package rafayel.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void validDeadline_success() {
        String description = "return book";
        LocalDateTime deadlineDate = LocalDateTime.of(2024, 2, 29, 12, 0);
        Deadline deadline = new Deadline(description, deadlineDate);

        assertEquals(description, deadline.getDescription());
        assertFalse(deadline.isDone);
        assertEquals(deadlineDate, deadline.deadlineDate);
    }

    @Test
    public void testSaveTaskName_notDoneTask_returnsCorrectFormat() {
        // check SaveTaskName() and markAsUndone()
        Deadline deadline = new Deadline("Submit report",
                LocalDateTime.of(2023, 10, 15, 14, 30));
        String result = deadline.saveTaskName();

        assertEquals("D | 0 | Submit report | Oct 15 2023 14:30", result);
    }

    @Test
    public void testSaveTaskName_doneTask_returnsCorrectFormat() {
        // check SaveTaskName() and markAsDone()
        Deadline deadline = new Deadline("Submit report",
                LocalDateTime.of(2023, 10, 15, 14, 30));
        deadline.markAsDone();
        String result = deadline.saveTaskName();

        assertEquals("D | 1 | Submit report | Oct 15 2023 14:30", result);
    }

}
