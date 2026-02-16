package betty.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import betty.exception.BettyException;
import betty.parser.Parser;


public class DeadlineTest {

    // Test creation of deadline Task
    @Test
    public void deadline_createTask_success() throws BettyException {
        LocalDate date = LocalDate.of(2025, 9, 1);
        Deadline t = new Deadline("return book", date, false);
        assertEquals("return book", t.getDescription());
        assertFalse(t.isDone());
        assertEquals(date, t.by);
    }

    //Test toString format of deadline Task
    @Test
    public void testToString_formatting() throws BettyException {
        LocalDate date = LocalDate.of(2025, 9, 1);
        Deadline deadline = new Deadline("return book", date, true);

        String expectedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expected = "[D][X] return book (by: " + expectedDate + ")";
        assertEquals(expected, deadline.toString());
    }

    // Test toSaveString formatting for storage
    @Test
    public void testToSaveString_formatting() throws BettyException {
        LocalDate date = LocalDate.of(2025, 12, 31);
        Deadline deadline = new Deadline("submit report", date, true);

        String expectedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expected = String.format("D | 1 | submit report |  | %s", expectedDate);
        assertEquals(expected, deadline.toSaveString());
    }

    // Test invalid date parsing throws exception
    @Test
    public void testDeadline_invalidDate_exception() {
        assertThrows(BettyException.class, () -> {
            LocalDate invalidDate = Parser.parseDate("invalid-date");
            new Deadline("invalid task", invalidDate, false);
        });
    }
    @Test
    public void testDeadline_withPriority_parsingAndSaving() throws BettyException {
        LocalDate date = LocalDate.of(2025, 9, 16);
        Deadline deadline = new Deadline("return book", date, false);
        deadline.setPriority(Priority.HIGH);

        // Check toSaveString includes the priority in the correct position
        String expectedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expectedSave = String.format("D | 0 | return book | HIGH | %s", expectedDate);
        assertEquals(expectedSave, deadline.toSaveString());

        // Now test parsing from storage string
        String storageString = expectedSave;
        Deadline parsed = (Deadline) Parser.parseTask(storageString);
        assertEquals("return book", parsed.getDescription());
        assertEquals(date, parsed.by);
        assertFalse(parsed.isDone());
        assertEquals(Priority.HIGH, parsed.getPriority());
    }
    @Test
    public void testDeadline_withPriority_toString() throws BettyException {
        LocalDate date = LocalDate.of(2025, 9, 16);
        Deadline deadline = new Deadline("return book", date, false);
        deadline.setPriority(Priority.HIGH);

        String expectedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expected = "[D][ ] return book (Priority: HIGH)(by: " + expectedDate + ")";
        assertEquals(expected, deadline.toString());
    }
}
