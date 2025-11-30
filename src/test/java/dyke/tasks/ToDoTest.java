package dyke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    private Todo task = new Todo("running this test");
    @Test
    public void todoString() {
        assertEquals("[T] [ ] running this test", task.toString());
    }

    @Test
    public void todoEncode() {
        assertEquals("T | 0 | running this test", task.encode());
    }
}
