import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {

    @Test
    public void testDeadlineWithDateTime() {
        Deadline deadline = new Deadline("return book", "2019-12-02 1800");
        assertEquals("return book", deadline.description);
        assertFalse(deadline.isDone);
        assertEquals("Dec 02 2019, 6:00 pm", deadline.toString().substring(deadline.toString().indexOf("by: ") + 4, deadline.toString().indexOf(")")));
    }

    @Test
    public void testDeadlineWithDateOnly() {
        Deadline deadline = new Deadline("submit assignment", "2019-12-15");
        assertEquals("submit assignment", deadline.description);
        assertFalse(deadline.isDone);
        assertEquals("Dec 15 2019, 11:59 pm", deadline.toString().substring(deadline.toString().indexOf("by: ") + 4, deadline.toString().indexOf(")")));
    }

    @Test
    public void testDeadlineWithMDYFormat() {
        Deadline deadline = new Deadline("return book", "2/12/2019 1800");
        assertEquals("return book", deadline.description);
        assertFalse(deadline.isDone);
        assertEquals("Feb 12 2019, 6:00 pm", deadline.toString().substring(deadline.toString().indexOf("by: ") + 4, deadline.toString().indexOf(")")));
    }

    @Test
    public void testDeadlineWithMDYFormatDateOnly() {
        Deadline deadline = new Deadline("submit report", "12/25/2019");
        assertEquals("submit report", deadline.description);
        assertFalse(deadline.isDone);
        assertEquals("Dec 25 2019, 11:59 pm", deadline.toString().substring(deadline.toString().indexOf("by: ") + 4, deadline.toString().indexOf(")")));
    }

    @Test
    public void testDeadlineWithLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 12, 2, 18, 0);
        Deadline deadline = new Deadline("return book", dateTime);
        assertEquals("return book", deadline.description);
        assertFalse(deadline.isDone);
        assertEquals(dateTime, deadline.getBy());
    }

    @Test
    public void testDeadlineMarkAsDone() {
        Deadline deadline = new Deadline("return book", "2019-12-02 1800");
        assertFalse(deadline.isDone);
        deadline.markAsDone();
        assertTrue(deadline.isDone);
        assertTrue(deadline.toString().contains("[X]"));
    }

    @Test
    public void testDeadlineMarkAsNotDone() {
        Deadline deadline = new Deadline("return book", "2019-12-02 1800");
        deadline.markAsDone();
        assertTrue(deadline.isDone);
        deadline.markAsNotDone();
        assertFalse(deadline.isDone);
        assertTrue(deadline.toString().contains("[ ]"));
    }

    @Test
    public void testDeadlineToString() {
        Deadline deadline = new Deadline("return book", "2019-12-02 1800");
        String expected = "[D][ ] return book (by: Dec 02 2019, 6:00 pm)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testDeadlineToStringMarked() {
        Deadline deadline = new Deadline("return book", "2019-12-02 1800");
        deadline.markAsDone();
        String expected = "[D][X] return book (by: Dec 02 2019, 6:00 pm)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testDeadlineInvalidDateFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("return book", "invalid date");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("return book", "32/13/2019");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("return book", "2019-13-45");
        });
    }

    @Test
    public void testDeadlineEdgeCases() {
        Deadline deadline1 = new Deadline("task 1", "1/1/2019 0000");
        assertEquals("Jan 01 2019, 12:00 am", deadline1.toString().substring(deadline1.toString().indexOf("by: ") + 4, deadline1.toString().indexOf(")")));
        
        Deadline deadline2 = new Deadline("task 2", "12/31/2019 2359");
        assertEquals("Dec 31 2019, 11:59 pm", deadline2.toString().substring(deadline2.toString().indexOf("by: ") + 4, deadline2.toString().indexOf(")")));
        
        Deadline deadline3 = new Deadline("task 3", "2019-01-01 0000");
        assertEquals("Jan 01 2019, 12:00 am", deadline3.toString().substring(deadline3.toString().indexOf("by: ") + 4, deadline3.toString().indexOf(")")));
    }

    @Test
    public void testDeadlineGetBy() {
        LocalDateTime expected = LocalDateTime.of(2019, 12, 2, 18, 0);
        Deadline deadline = new Deadline("return book", "2019-12-02 1800");
        assertEquals(expected, deadline.getBy());
    }
}
