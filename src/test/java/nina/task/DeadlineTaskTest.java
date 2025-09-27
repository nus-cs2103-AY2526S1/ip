package nina.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeParseException;

import nina.InvalidInputException;

public class DeadlineTaskTest {
    @Test
    public void testToSaveLineStoresDateCorrectly() {
        DeadlineTask d = new DeadlineTask("return book", "2019-12-02");
        String expected = "D | 0 | return book | 2019-12-02";
        assertEquals(expected, d.toSaveableLine());
    }

    @Test
    public void testToStringFormatsDateCorrectly() {
        DeadlineTask d = new DeadlineTask("return book", "2019-12-02");
        String expected = "[D][ ] return book (by: Dec 02 2019)";
        assertEquals(expected, d.toString());
    }

    @Test
    public void testEmptyDescription() {
        DeadlineTask d = new DeadlineTask("", "2025-05-01");
        assertTrue(d.toString().contains("(by: May 01 2025)"));
    }

    @Test
    public void testInvalidDate() {
        assertThrows(InvalidInputException.class,
                () -> new DeadlineTask("return book", "Tue"));
    }
}
