package nacho.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {
    @Test
    public void testStringConversion() {
        TodoTask testTask = new TodoTask("Buy groceries");

        // Standard String Conversion Format
        assertEquals("[T][ ] Buy groceries", testTask.toString());

        // Storage String Conversion Format
        assertEquals("T | 0 | Buy groceries", testTask.getStorageRepresentation());
    }

    @Test
    public void testMarkAndUnmark() {
        TodoTask testTask = new TodoTask("Study for exam");

        testTask.markCompleted();
        assertEquals("[T][X] Study for exam", testTask.toString());
        assertEquals(1, testTask.isCompleted());

        testTask.unmarkCompleted();
        assertEquals("[T][ ] Study for exam", testTask.toString());
        assertEquals(0, testTask.isCompleted());
    }

    @Test
    public void testMarkedStorageRepresentation() {
        TodoTask testTask = new TodoTask("Read a book");

        // Mark Completed Storage Representation
        testTask.markCompleted();
        assertEquals("T | 1 | Read a book", testTask.getStorageRepresentation());

        // Unmark completion Storage Representation
        testTask.unmarkCompleted();
        assertEquals("T | 0 | Read a book", testTask.getStorageRepresentation());
    }
}
