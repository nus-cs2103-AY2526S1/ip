package smartstelle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link ToDo} class.
 * <p>
 * Tests the constructor and behavior of ToDo objects, including validation for
 * proper initialization and handling of invalid input.
 */
public class ToDoTest {

    /**
     * Tests that a ToDo object is correctly constructed with valid parameters.
     * <p>
     * Verifies that the object is not null, the name is set correctly,
     * and the completion status is false when specified.
     *
     * @throws StelleException if the ToDo constructor throws for invalid input (not
     *                         expected here)
     */
    @Test
    public void testConstructorValid() throws StelleException {
        ToDo todo = new ToDo("Ate", false);
        assertNotNull(todo);
        assertEquals("Ate", todo.getName());
        assertFalse(todo.getIsCompleted());
    }

    /**
     * Tests that constructing a ToDo object with an empty name
     * throws a {@link StelleException}.
     */
    @Test
    public void testConstructorInvalidName() {
        assertThrows(StelleException.class, () -> new ToDo("", false));
    }
}
