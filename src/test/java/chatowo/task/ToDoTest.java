package chatowo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toDataString_returnsCorrectFormat() {
        ToDo todo = new ToDo("read book");
        assertEquals("T | 0 | read book", todo.toDataString());
        todo.setDone();
        assertEquals("T | 1 | read book", todo.toDataString());
    }

    @Test
    public void toString_returnsCorrectFormat() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
        todo.setDone();
        assertEquals("[T][X] read book", todo.toString());
    }
}
