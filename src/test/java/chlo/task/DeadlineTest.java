package chlo.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import chlo.exception.ChloException;

public class DeadlineTest {

    @Test
    public void constructor_validInput_createsDeadline() throws ChloException {
        String description = "submit report";
        String byStr = "20/09/2025 2359";

        Deadline deadline = new Deadline(description, byStr);

        assertEquals(description, deadline.getDescription());
        assertNotNull(deadline.getBy());

        // Optionally verify parsed date is correct
        LocalDateTime expectedDate = deadline.getBy(); // Or parse separately if known
        assertEquals(expectedDate, deadline.getBy());

        String expectedRaw = "deadline submit report /by 20/09/2025 2359";
        assertEquals(expectedRaw, deadline.getRaw());
    }

    @Test
    public void toString_containsDescriptionAndBy() throws ChloException {
        String description = "submit report";
        String byStr = "20/09/2025 2359";

        Deadline deadline = new Deadline(description, byStr);

        String output = deadline.toString();

        assertTrue(output.contains(description));
        assertTrue(output.contains("20/09/2025") || output.contains("2025")); // Check date presence
        assertTrue(output.startsWith("[D]"));
    }

    @Test
    public void constructor_invalidDate_throwsException() {
        String description = "test task";
        String invalidBy = "invalid-date-string";

        assertThrows(ChloException.class, () -> {
            new Deadline(description, invalidBy);
        });
    }
}
