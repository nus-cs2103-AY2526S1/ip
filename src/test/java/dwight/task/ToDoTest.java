package dwight.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link ToDo} class. Verifies behavior for marking, string representation, and
 * serialization.
 */
public class ToDoTest {

    /**
     * Tests that tasks can be marked and unmarked correctly, and that the marked state is returned
     * as expected.
     */
    @Test
    public void testMark() {
        assertEquals(false, new ToDo("Complete iP").isMarked(), "New task should not be marked");
        assertEquals(
                true,
                new ToDo("Submit iP").mark().isMarked(),
                "Task should be marked after calling mark()");
        assertEquals(
                false,
                new ToDo("Delete iP").mark().unmark().isMarked(),
                "Task should be unmarked after calling unmark()");
    }

    /**
     * Tests the string representation of a {@code ToDo} task, ensuring that the type, status, and
     * description are formatted correctly.
     */
    @Test
    public void testToString() {
        assertEquals(
                "[T][ ] TD", new ToDo("TD").toString(), "Unmarked task should display with [ ]");
        assertEquals(
                "[T][X] TD",
                new ToDo("TD").mark().toString(),
                "Marked task should display with [X]");
        assertEquals(
                "[T][ ] TD",
                new ToDo("TD").mark().unmark().toString(),
                "Task should return to [ ] after unmark()");
    }

    /**
     * Tests the serialization of a {@code ToDo} task, ensuring that type, status flag, and
     * description are formatted correctly.
     */
    @Test
    public void testSerialize() {
        assertEquals(
                "T | 0 | TD", new ToDo("TD").serialize(), "Unmarked task should serialize with 0");
        assertEquals(
                "T | 1 | TD",
                new ToDo("TD").mark().serialize(),
                "Marked task should serialize with 1");
        assertEquals(
                "T | 0 | TD",
                new ToDo("TD").mark().unmark().serialize(),
                "Task should serialize back to 0 after unmark()");
    }
}
