import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guibot.task.Todo;

public class TodoTest {
    @Test
    public void testStorageStringConversion() {
        Todo t = Todo.of("hello");
        assertEquals("t//false//hello", t.toStorageString());

        t.mark();
        assertEquals("t//true//hello", t.toStorageString());
    }

    @Test
    public void testStringConversion() {
        Todo t = Todo.of("hello");
        assertEquals("[T][ ] hello", t.toString());

        t.mark();
        assertEquals("[T][X] hello", t.toString());
    }
}
