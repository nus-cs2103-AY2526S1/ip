package dwight.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dwight.storage.CorruptSaveException;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link EventParser} class. Verifies correct parsing of user input and file
 * data into {@link Event} tasks, as well as proper exception handling for invalid input.
 */
public class EventParserTest {

    /**
     * Tests that a valid description string is successfully parsed into an {@link Event} task with
     * the expected string representation.
     */
    @Test
    void testParseValidDescriptionReturnsEventTask() throws IncompleteTaskException {
        EventParser parser = new EventParser();
        String description = "project meeting /from 10 Feb 2025 /to 12 Feb 2025";
        Task task = parser.parse(description);
        assertTrue(task instanceof Event);
        String expectedToString = "[E][ ] project meeting (from: 10 Feb to: 12 Feb)";
        assertEquals(expectedToString, task.toString());
    }

    /**
     * Tests that parsing a description missing the {@code /to} part throws an {@link
     * IncompleteTaskException}.
     */
    @Test
    void testParseNoToThrowsIncompleteTaskException() {
        EventParser parser = new EventParser();
        String invalidDescription = "project meeting /from 10 Feb 2025";
        Exception exception =
                assertThrows(IncompleteTaskException.class, () -> parser.parse(invalidDescription));
        assertEquals(
                "The 'event' command requires a description, a '/from' date, and a '/to' date."
                        + " Format: event <description> /from <date e.g. 14 Feb 2025> /to <date"
                        + " e.g. 14 Feb 2025>",
                exception.getMessage());
    }

    /**
     * Tests that parsing a description missing the {@code /from} part throws an {@link
     * IncompleteTaskException}.
     */
    @Test
    void testParseNoFromThrowsIncompleteTaskException() {
        EventParser parser = new EventParser();
        String invalidDescription = "project meeting /to 12 Feb 2025";
        Exception exception =
                assertThrows(IncompleteTaskException.class, () -> parser.parse(invalidDescription));
        assertEquals(
                "The 'event' command requires a description, a '/from' date, and a '/to' date."
                        + " Format: event <description> /from <date e.g. 14 Feb 2025> /to <date"
                        + " e.g. 14 Feb 2025>",
                exception.getMessage());
    }

    /**
     * Tests that a valid saved description string is successfully parsed from file into an {@link
     * Event} task with the expected string representation.
     */
    @Test
    void testParseFromFileValidDescriptionReturnsEventTask() throws CorruptSaveException {
        EventParser parser = new EventParser();
        String savedDescription = "project meeting | 10 Feb 2025 | 12 Feb 2025";
        Task task = parser.parseFromFile(savedDescription);
        assertTrue(task instanceof Event);
        String expectedToString = "[E][ ] project meeting (from: 10 Feb to: 12 Feb)";
        assertEquals(expectedToString, task.toString());
    }

    /**
     * Tests that parsing a saved description missing the end date throws a {@link
     * CorruptSaveException}.
     */
    @Test
    void testParseFromFileIncompleteDescriptionThrowsCorruptSaveException() {
        EventParser parser = new EventParser();
        String invalidSavedDescription = "project meeting | 10 Feb 2025";
        Exception exception =
                assertThrows(
                        CorruptSaveException.class,
                        () -> parser.parseFromFile(invalidSavedDescription));
        assertEquals(
                "Save file is corrupted at line '" + invalidSavedDescription + "'",
                exception.getMessage());
    }
}
