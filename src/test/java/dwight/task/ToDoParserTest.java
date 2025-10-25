package dwight.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dwight.storage.CorruptSaveException;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link ToDoParser} class. Verifies correct parsing of user input and file data
 * into {@link ToDo} tasks, as well as proper exception handling for invalid input.
 */
public class ToDoParserTest {

    /**
     * Tests that a valid description string is successfully parsed into a {@link ToDo} task with
     * the expected string representation.
     */
    @Test
    public void testParseValidDescriptionReturnsToDoTask() throws IncompleteTaskException {
        ToDoParser parser = new ToDoParser();
        Task task = parser.parse("read book");

        assertTrue(task instanceof ToDo, "Parser should return a ToDo instance");
        assertEquals(
                "[T][ ] read book",
                task.toString(),
                "Unmarked ToDo should display with [ ] and correct description");
    }

    /** Tests that parsing an empty description throws an {@link IncompleteTaskException}. */
    @Test
    public void testParseEmptyDescriptionThrowsIncompleteTaskException() {
        ToDoParser parser = new ToDoParser();
        Exception exception = assertThrows(IncompleteTaskException.class, () -> parser.parse(""));
        assertEquals("The description for a 'todo' task cannot be empty.", exception.getMessage());
    }

    /**
     * Tests that a valid saved description string is successfully parsed from file into a {@link
     * ToDo} task with the expected string representation.
     */
    @Test
    public void testParseFromFileValidDescriptionReturnsToDoTask() throws CorruptSaveException {
        ToDoParser parser = new ToDoParser();
        Task task = parser.parseFromFile("attend meeting");

        assertTrue(task instanceof ToDo, "Parser should return a ToDo instance");
        assertEquals(
                "[T][ ] attend meeting",
                task.toString(),
                "Unmarked ToDo should display with [ ] and correct description");
    }
}
