package whchenyicn.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    void constructorTest() {
        ToDo todo = new ToDo("read book");
        assertEquals("T | 0 | read book", todo.toSave());
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    void markDoneTest() {
        ToDo todo = new ToDo("do homework");
        todo.markDone();
        assertEquals("T | 1 | do homework", todo.toSave());
        assertEquals("[T][X] do homework", todo.toString());
    }

    @Test
    void unmarkTest() {
        ToDo todo = new ToDo("buy milk");
        todo.markDone();
        todo.unmark();
        assertEquals("T | 0 | buy milk", todo.toSave());
        assertEquals("[T][ ] buy milk", todo.toString());
    }
}
