package pepe.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import pepe.exception.PepeExceptions;

class EventsTest {

    @Test
    void testToString_unmarked() throws PepeExceptions {
        Events event = new Events("Meeting", "2099-01-01", "2099-01-02");
        assertEquals("[E][ ] Meeting (from: Jan 1 2099 to: Jan 2 2099)", event.toString());
    }

    @Test
    void testToString_marked() throws PepeExceptions {
        Events event = new Events("Meeting", "2099-01-01", "2099-01-02");
        event.markTask();
        assertEquals("[E][X] Meeting (from: Jan 1 2099 to: Jan 2 2099)", event.toString());
    }

    @Test
    void testToFileFormat_unmarked() throws PepeExceptions {
        Events event = new Events("Meeting", "2099-01-01", "2099-01-02");
        assertEquals("E | 0 | Meeting | Jan 1 2099 - Jan 2 2099", event.toFileFormat());
    }

    @Test
    void testToFileFormat_marked() throws PepeExceptions {
        Events event = new Events("Meeting", "2099-01-01", "2099-01-02");
        event.markTask();
        assertEquals("E | 1 | Meeting | Jan 1 2099 - Jan 2 2099", event.toFileFormat());
    }

    @Test
    void testConstructor_invalidDateFormat() {
        PepeExceptions exception = assertThrows(PepeExceptions.class, () ->
                new Events("Bad Event", "01-01-2099", "02-01-2099"));
        assertTrue(exception.getMessage().contains("Invalid Input: Please check the format of your dates"));
    }

    @Test
    void testConstructor_endBeforeToday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        PepeExceptions exception = assertThrows(PepeExceptions.class, () ->
                new Events("Past Event", tomorrow.toString(), yesterday.toString()));
        assertTrue(exception.getMessage().contains("End Date cannot be before today"));
    }

    @Test
    void testConstructor_startAfterEnd() {
        LocalDate start = LocalDate.now().plusDays(5);
        LocalDate end = LocalDate.now().plusDays(2);

        PepeExceptions exception = assertThrows(PepeExceptions.class, () ->
                new Events("Invalid Event", start.toString(), end.toString()));
        assertTrue(exception.getMessage().contains("Start Date cannot be after End Date"));
    }
}
