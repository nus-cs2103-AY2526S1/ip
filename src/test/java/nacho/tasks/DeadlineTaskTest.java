package nacho.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import nacho.exceptions.UserInputException;

public class DeadlineTaskTest {
    @Test
    public void testStringConversion() {
        DeadlineTask testTask = new DeadlineTask("Test", "08/12/2025-23:40");

        // Standard String Conversion Format
        assertEquals("[D][ ] Test (by: 08 December 2025 - 11:40 PM)", testTask.toString());

        // Storage String Conversion Format
        assertEquals("D | 0 | Test | 08/12/2025-23:40", testTask.getStorageRepresentation());
    }

    @Test
    public void testMarkAndUnmark() {
        DeadlineTask testTask = new DeadlineTask("Test", "08/12/2025-23:40");

        testTask.markCompleted();
        assertEquals("[D][X] Test (by: 08 December 2025 - 11:40 PM)", testTask.toString());
        assertEquals(1, testTask.isCompleted());

        testTask.unmarkCompleted();
        assertEquals("[D][ ] Test (by: 08 December 2025 - 11:40 PM)", testTask.toString());
        assertEquals(0, testTask.isCompleted());
    }

    @Test
    public void testWrongDateFormat() {
        assertThrows(UserInputException.class, () -> new DeadlineTask("Test", "InproperString"));
    }

}
