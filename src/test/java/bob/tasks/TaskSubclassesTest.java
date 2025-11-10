package bob.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Junit tests for unit testing of Task subclasses
 * Tests created by an LLM
 */
class TaskSubclassesTest {

    @Test
    void toDo_toStringFormat() {
        ToDo todo = new ToDo("Read book");
        assertTrue(todo.toString().startsWith("[T]"));
    }

    @Test
    void deadline_validDate() {
        LocalDate date = LocalDate.of(2025, 9, 18);
        Deadline d = new Deadline("Submit assignment", false, date);
        assertEquals(date, d.getDueDate());
    }

    @Test
    void event_invalidDates_throwsException() {
        LocalDate start = LocalDate.of(2025, 9, 18);
        LocalDate end = LocalDate.of(2025, 9, 17); // before start
        assertThrows(IllegalArgumentException.class,
                () -> new Event("Conference", false, start, end));
    }

    @Test
    void containsString_caseInsensitive() {
        ToDo todo = new ToDo("Read book");
        assertTrue(todo.containsString("BOOK"));
        assertFalse(todo.containsString("Movie"));
    }
}
