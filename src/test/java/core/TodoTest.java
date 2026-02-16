package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void sampleTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testToString_done() {
        Todo todo = new Todo("eat supper at fong seng", true);
        assertEquals("[T] [X] eat supper at fong seng", todo.toString());
    }

    @Test
    public void testToString_notDone() {
        Todo todo = new Todo("go for lunch at deck", false);
        assertEquals("[T] [ ] go for lunch at deck", todo.toString());
    }

    @Test
    public void testToStorageString_done() {
        Todo todo = new Todo("sleep", true);
        assertEquals("T | 1 | sleep", todo.toStorageString());
    }

    @Test
    public void testToStorageString_notDone() {
        Todo todo = new Todo("code iP", false);
        assertEquals("T | 0 | code iP", todo.toStorageString());
    }
}
