package betty.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import betty.exception.BettyException;
import betty.parser.Parser;

public class EventTest {

    // Test creation of event Task
    @Test
    public void event_createTask_success() throws BettyException {
        LocalDate from = LocalDate.of(2025, 9, 1);
        LocalDate to = LocalDate.of(2025, 9, 1);
        Event event = new Event("go library", from, to, false);
        assertEquals("go library", event.getDescription());
        assertFalse(event.isDone());
        assertEquals(from, event.from);
        assertEquals(to, event.to);
    }

    //Test toString format of deadline Task
    @Test
    public void testToString_formatting() throws BettyException {
        LocalDate from = LocalDate.of(2025, 9, 1);
        LocalDate to = LocalDate.of(2025, 9, 1);
        Event event = new Event("go library", from, to, true);

        String expectedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expectedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expected = "[E][X] go library (from: " + expectedFrom + " to: " + expectedTo + ")";
        assertEquals(expected, event.toString());
    }

    // Test toSaveString formatting for storage
    @Test
    public void testToSaveString_formatting() throws BettyException {
        LocalDate from = LocalDate.of(2025, 9, 1);
        LocalDate to = LocalDate.of(2025, 9, 5);
        Event event = new Event("go library", from, to, true);

        String expectedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expectedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expected = String.format("E | 1 | go library |  | %s | %s", expectedFrom, expectedTo);
        assertEquals(expected, event.toSaveString());
    }

    // Test invalid date parsing throws exception
    @Test
    public void testEvent_invalidDate_exception() {
        assertThrows(BettyException.class, () -> {
            LocalDate from = Parser.parseDate("invalid-from-date");
            LocalDate to = Parser.parseDate("invalid-to-date");
            new Event("invalid event", from, to, false);
        });
    }
    // Test Event toSaveString with priority
    @Test
    public void testEvent_withPriority_toSaveString() throws BettyException {
        LocalDate from = LocalDate.of(2025, 9, 1);
        LocalDate to = LocalDate.of(2025, 9, 5);
        Event event = new Event("go library", from, to, false);
        event.setPriority(Priority.HIGH);

        String expectedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expectedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expected = String.format("E | 0 | go library | HIGH | %s | %s", expectedFrom, expectedTo);
        assertEquals(expected, event.toSaveString());
    }

    // Test Event toString with priority
    @Test
    public void testEvent_withPriority_toString() throws BettyException {
        LocalDate from = LocalDate.of(2025, 9, 1);
        LocalDate to = LocalDate.of(2025, 9, 5);
        Event event = new Event("go library", from, to, false);
        event.setPriority(Priority.HIGH);

        String expectedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expectedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String expected = "[E][ ] go library (Priority: HIGH)(from: " + expectedFrom + " to: " + expectedTo + ")";
        assertEquals(expected, event.toString());
    }
}
