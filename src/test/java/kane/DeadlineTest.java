package kane;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    /**
     * Tests the Deadline constructor with a valid date and time string.
     * It verifies that the object is created successfully and that the
     * toString() and toFileString() methods format the date correctly.
     */
    @Test
    public void deadlineConstructor_validDateTimeFormat_createsSuccessfully() {
        // Define a valid description and a date string in the expected format.
        String description = "Submit IP project";
        String byString = "2024-03-15 2359";

        // Create a new Deadline object. This should not throw an exception.
        Deadline deadline = new Deadline(description, byString);

        // Verify the state of the newly created object.
        assertNotNull(deadline); // Ensure the object was created.
        assertEquals(description, deadline.description); // Check if the description is set correctly.

        // Check the user-friendly string format (using OUTPUT_FORMAT)
        // This confirms the date was parsed and can be formatted correctly.
        String expectedToString = "[D] [ ] Submit IP project (by: Mar 15 2024, 11:59 PM)";
        assertEquals(expectedToString, deadline.toString());

        // Check the file storage string format (using INPUT_FORMAT)
        String expectedToFileString = "D | 0 | Submit IP project | 2024-03-15 2359";
        assertEquals(expectedToFileString, deadline.toFileString());
    }

    /**
     * Tests that the Deadline constructor throws an IllegalArgumentException
     * when given a date string in an invalid format (e.g., plain text).
     */
    @Test
    public void deadlineConstructor_invalidDateTimeFormat_throwsIllegalArgumentException() {
        // Define a date string that does not match the required format.
        String description = "Finish homework";
        String invalidByString = "next Friday";

        // Verify that an IllegalArgumentException is thrown.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Deadline(description, invalidByString);
        });

        // Verify that the exception message is the one we expect.
        String expectedMessage = "Invalid date format! Please use yyyy-MM-dd HHmm, e.g. 2019-12-02 1800.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests that the Deadline constructor throws an IllegalArgumentException
     * for a date string that is structured but contains invalid values (e.g., month 13).
     */
    @Test
    public void deadlineConstructor_dateTimeWithInvalidValues_throwsIllegalArgumentException() {
        // Define a date string that has the right pattern but invalid values.
        String description = "Plan event";
        String invalidByString = "2024-13-01 1200";

        // The same assertThrows check as the previous test.
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline(description, invalidByString);
        });
    }
}