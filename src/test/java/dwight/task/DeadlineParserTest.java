package dwight.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dwight.storage.CorruptSaveException;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link DeadlineParser} class. Verifies correct parsing of user input and file
 * data into {@link Deadline} tasks, as well as proper exception handling for invalid input.
 */
public class DeadlineParserTest {

    /**
     * Tests that a valid description string is successfully parsed into a {@link Deadline} task
     * with the expected string representation.
     */
    @Test
    public void testParseValidDescriptionReturnsDeadlineTask() throws IncompleteTaskException {
        DeadlineParser parser = new DeadlineParser();
        String description = "submit project proposal /by 20 Feb 2025";
        Task task = parser.parse(description);

        assertTrue(task instanceof Deadline, "Parser should return a Deadline instance");

        String expectedToString = "[D][ ] submit project proposal (by: 20 Feb)";
        assertEquals(
                expectedToString,
                task.toString(),
                "Unmarked deadline should display with [ ] and correct date");
    }

    /**
     * Tests that parsing a description without a {@code /by} section throws an {@link
     * IncompleteTaskException}.
     */
    @Test
    public void testParseInvalidFormatThrowsIncompleteTaskException() {
        DeadlineParser parser = new DeadlineParser();
        String invalidDescription = "submit project proposal";

        Exception exception =
                assertThrows(IncompleteTaskException.class, () -> parser.parse(invalidDescription));

        assertEquals(
                "The 'deadline' command requires a description and a '/by' date. Format:"
                        + " deadline <description> /by <date e.g. 14 Feb 2025>",
                exception.getMessage());
    }

    /**
     * Tests that a valid saved description string is successfully parsed from file into a {@link
     * Deadline} task with the expected string representation.
     */
    @Test
    public void testParseFromFileValidDescriptionReturnsDeadlineTask() throws CorruptSaveException {
        DeadlineParser parser = new DeadlineParser();
        String savedDescription = "submit report | 25 Dec 2024";
        Task task = parser.parseFromFile(savedDescription);

        assertTrue(task instanceof Deadline, "Parser should return a Deadline instance");

        String expectedToString = "[D][ ] submit report (by: 25 Dec)";
        assertEquals(
                expectedToString,
                task.toString(),
                "Unmarked deadline should display with [ ] and correct date");
    }

    /**
     * Tests that parsing a saved description without the expected format throws a {@link
     * CorruptSaveException}.
     */
    @Test
    public void testParseFromFileInvalidFormat_throwsCorruptSaveException() {
        DeadlineParser parser = new DeadlineParser();
        String invalidSavedDescription = "incomplete description";

        Exception exception =
                assertThrows(
                        CorruptSaveException.class,
                        () -> parser.parseFromFile(invalidSavedDescription));

        assertEquals(
                "Save file is corrupted at line '" + invalidSavedDescription + "'",
                exception.getMessage());
    }
}
