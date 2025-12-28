package thecoolerduke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import thecoolerduke.exceptions.InvalidFormatException;

public class PriorityTest {

    @Test
    void testFromStringValidLevels() throws InvalidFormatException {
        assertEquals(Priority.HIGH, Priority.fromString("3"));
        assertEquals(Priority.MEDIUM, Priority.fromString("2"));
        assertEquals(Priority.LOW, Priority.fromString("1"));
    }

    @Test
    void testFromStringInvalidNumber() {
        InvalidFormatException e = assertThrows(InvalidFormatException.class, () -> {
            Priority.fromString("5"); // not 1,2,3
        });
        assertTrue(e.getMessage().contains("Not in range"));
    }

    @Test
    void testFromStringNonInteger() {
        InvalidFormatException e = assertThrows(InvalidFormatException.class, () -> {
            Priority.fromString("high"); // non-integer input
        });
        assertTrue(e.getMessage().contains("Not a number"));
    }

    @Test
    void testGetLevel() {
        assertEquals(3, Priority.HIGH.getLevel());
        assertEquals(2, Priority.MEDIUM.getLevel());
        assertEquals(1, Priority.LOW.getLevel());
    }

    @Test
    void testGetLabel() {
        assertEquals("HIGH", Priority.HIGH.getLabel());
        assertEquals("MEDIUM", Priority.MEDIUM.getLabel());
        assertEquals("LOW", Priority.LOW.getLabel());
    }
}
