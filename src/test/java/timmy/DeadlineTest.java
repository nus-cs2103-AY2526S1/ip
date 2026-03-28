package timmy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.TimmyDateParsingException;

/**
 * Unit tests for {@link Deadline}.
 */
public class DeadlineTest {

    @Test
    void creation_parsesDateCorrectly() throws TimmyDateParsingException {
        Deadline deadline = new Deadline("Submit report", "18/9/2025");

        assertEquals("Submit report", deadline.toString());
        assertEquals("[D][ ] Submit report (by: Sep 18 2025)", deadline.toCompleteString());
        assertEquals("D | 0 | Submit report | 18/9/2025", deadline.toFileString());
    }

    @Test
    void markAsDone_reflectsInOutput() throws TimmyDateParsingException {
        Deadline deadline = new Deadline("Finish assignment", "01/01/2026");
        deadline.markAsDone();

        assertTrue(deadline.isDone);
        assertEquals("[D][X] Finish assignment (by: Jan 01 2026)", deadline.toCompleteString());
        assertEquals("D | 1 | Finish assignment | 1/1/2026", deadline.toFileString());
    }

    @Test
    void invalidDate_throwsException() {
        assertThrows(TimmyDateParsingException.class, () -> new Deadline("Bad date", "2025-09-18"));
    }
}
