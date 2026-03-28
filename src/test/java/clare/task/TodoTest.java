package clare.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void testToString() {
        Todo todo = new Todo("read book", false);
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    void testToSaveString() {
        Todo todo = new Todo("read book", false);
        assertEquals("T|0|read book", todo.toSaveString());
    }

    @Test
    void testToString_done() {
        Todo todo = new Todo("finish homework", true);
        assertEquals("[T][X] finish homework", todo.toString());
    }

    @Test
    void testToSaveString_done() {
        Todo todo = new Todo("finish homework", true);
        assertEquals("T|1|finish homework", todo.toSaveString());
    }
}
