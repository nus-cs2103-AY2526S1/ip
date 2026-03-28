package karl.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {

    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

    @Test
    public void constructor_validDateString_parsesDateCorrectly() {
        Deadline deadline = new Deadline("return book", "2025-12-25");
        assertEquals(LocalDate.of(2025, 12, 25), deadline.getBy());
        assertEquals("return book", deadline.getDescription());
    }

    @Test
    public void markAsDone_marksTaskAsDone() {
        Deadline deadline = new Deadline("return book", "2025-01-01");
        assertFalse(deadline.isDone());
        deadline.markAsDone();
        assertTrue(deadline.isDone());
    }

    @Test
    public void markAsNotDone_marksTaskAsNotDone() {
        Deadline deadline = new Deadline("return book", "2025-01-01");
        deadline.markAsDone();
        deadline.markAsNotDone();
        assertFalse(deadline.isDone());
    }

    @Test
    public void toString_formatsCorrectly() {
        Deadline deadline = new Deadline("return book", "2025-01-01");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String expected = String.format("[D][ ] return book (by: %s)", LocalDate.of(2025, 1, 1).format(outputFormat));
        assertEquals(expected, deadline.toString());

        deadline.markAsDone();
        expected = String.format("[D][X] return book (by: %s)", LocalDate.of(2025, 1, 1).format(outputFormat));
        assertEquals(expected, deadline.toString());
    }
}

// Used AI to help clean up code and naming conventions etc, mainly small issues brought up through code reviews