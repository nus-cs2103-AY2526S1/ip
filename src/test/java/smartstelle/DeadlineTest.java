package smartstelle;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link Deadline} class.
 * <p>
 * Tests the constructor and behavior of Deadline objects, including validation
 * for
 * proper initialization and handling of invalid input.
 */
public class DeadlineTest {

    /**
     * Tests that a Deadline object is correctly constructed with valid parameters.
     * <p>
     * Verifies that the object is not null, the name is set correctly,
     * the completion status is correct, and the date is parsed properly.
     *
     * @throws StelleException if the Deadline constructor throws for invalid input
     *                         (not expected here)
     */
    @Test
    public void testConstructorValid() throws StelleException {
        String date = "2025-08-12T18:00";
        Deadline deadline = new Deadline("Eat", date, false);

        assertNotNull(deadline);
        assertEquals("Eat", deadline.getName());
        assertFalse(deadline.getIsCompleted());
        assertEquals(LocalDateTime.parse(date), deadline.getDate());
    }

    /**
     * Tests that constructing a Deadline object with an empty name
     * throws a {@link StelleException}.
     */
    @Test
    public void testConstructorInvalidName() {
        assertThrows(StelleException.class, () -> {
            new Deadline("", "2025-08-12T18:00", false);
        });
    }
}
