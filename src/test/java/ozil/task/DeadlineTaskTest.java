package ozil.task;

import org.junit.jupiter.api.Test;
import ozil.exception.OzilException;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    @Test
    void deadlineTaskTest() throws OzilException, ParseException {
        //Deadline task without proper date
        DeadlineTask taskWithoutDate = new DeadlineTask("test", "Monday");
        assertEquals(taskWithoutDate.hasDate(), false);
        assertEquals(taskWithoutDate.toString(), "[D] [ ] test by: Monday");
        taskWithoutDate.markAsDone();
        assertEquals(taskWithoutDate.toString(), "[D] [X] test by: Monday");
        taskWithoutDate.markAsUndone();
        assertEquals(taskWithoutDate.toString(), "[D] [ ] test by: Monday");
        assertEquals(taskWithoutDate.convertToStorageFormat(), "D | 0 | test | Monday ");
        //Now for a deadline task with proper date
        DeadlineTask taskWithDate = new DeadlineTask("test2", "2025-09-22 1800");
        assertEquals(taskWithDate.hasDate(), true);
        assertEquals(taskWithDate.toString(), "[D] [ ] test2 by: 22-09-2025 1800 ");
        assertEquals(taskWithDate.convertToStorageFormat(), "D | 0 | test2 | 2025-09-22 1800 ");
        assertEquals(taskWithDate.getTaskDate(), taskWithDate.parseDateTime("2025-09-22 1800"));
    }
}
