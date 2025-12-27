package fatty.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ToDoTaskTest {

    @Test
    public void testToString() {
        ToDoTask task = new ToDoTask("read book");
        assertEquals("[T][ ] read book", task.toString());
    }

    @Test
    public void testToDataString() {
        ToDoTask task = new ToDoTask("read book");
        assertEquals("T | 0 | read book", task.toDataString());
    }
}
