import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.GenieweenieException;
import task.Deadline;


/**
 * Tests for {@link task.Deadline}.
 */
public class DeadlineTest {


    /**
     * Ensures toString() renders the expected date format for a valid ISO date.
     */
    @Test
    public void toString_validDate_success() throws GenieweenieException {
        Deadline d = new Deadline("submit ip", "2026-08-28");
        assertEquals("[D][ ] submit ip (by: Aug 28 2026)", d.toString());
    }


    /**
     * Ensures constructor throws when given an invalid date string.
     */
    @Test
    public void constructor_invalidDate_exceptionThrown() {
        assertThrows(GenieweenieException.class, () -> new Deadline("submit ip", "28-08-2025"));
    }
}
