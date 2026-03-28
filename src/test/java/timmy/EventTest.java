package timmy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.TimmyDateParsingException;

/**
 * Unit tests for {@link Event}.
 */
public class EventTest {

    @Test
    void creation_parsesDatesCorrectly() throws TimmyDateParsingException {
        Event event = new Event("Conference", "20/9/2025", "22/9/2025");

        assertEquals("Conference", event.toString());
        assertEquals("[E][ ] Conference (from: Sep 20 2025 to: Sep 22 2025)",
                event.toCompleteString());
        assertEquals("E | 0 | Conference | 20/9/2025 | 22/9/2025",
                event.toFileString());
    }

    @Test
    void markAsDone_reflectsInOutput() throws TimmyDateParsingException {
        Event event = new Event("Hackathon", "10/10/2025", "12/10/2025");
        event.markAsDone();

        assertTrue(event.isDone);
        assertEquals("[E][X] Hackathon (from: Oct 10 2025 to: Oct 12 2025)",
                event.toCompleteString());
        assertEquals("E | 1 | Hackathon | 10/10/2025 | 12/10/2025",
                event.toFileString());
    }

    @Test
    void invalidDate_throwsException() {
        assertThrows(TimmyDateParsingException.class,
                () -> new Event("Invalid event", "2025/10/10", "2025/10/12"));
    }
}
