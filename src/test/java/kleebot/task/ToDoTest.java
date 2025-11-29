package kleebot.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {

    @Test
    void testGetType() {
        ToDo todo = new ToDo("Buy milk");
        assertEquals("T", todo.getType());
    }

    @Test
    void testToString() {
        ToDo todo = new ToDo("Buy milk");
        assertEquals("[T][ ]Buy milk", todo.toString());
    }
}