package pepe.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ToDosTest {

    @Test
    void testToString_unmarked() {
        ToDos todo = new ToDos("Test task");
        // Initially unmarked
        assertEquals("[T][ ] Test task", todo.toString());
    }

    @Test
    void testToString_marked() {
        ToDos todo = new ToDos("Test task");
        todo.markTask(); // mark as done
        assertEquals("[T][X] Test task", todo.toString());
    }

    @Test
    void testToFileFormat_unmarked() {
        ToDos todo = new ToDos("Test task");
        assertEquals("T | 0 | Test task", todo.toFileFormat());
    }

    @Test
    void testToFileFormat_marked() {
        ToDos todo = new ToDos("Test task");
        todo.markTask();
        assertEquals("T | 1 | Test task", todo.toFileFormat());
    }
}
