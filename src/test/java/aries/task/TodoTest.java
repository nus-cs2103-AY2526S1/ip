package aries.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testToString() {
        Todo todo = new Todo("Read a book");
        assertEquals("[T] [ ] Read a book", todo.toString());
    }

    @Test
    public void testMark() {
        Todo todo = new Todo("Read a book");
        todo.markAsDone();
        assertEquals("[T] [X] Read a book", todo.toString());
    }

    @Test
    public void testUnmark() {
        Todo todo = new Todo("Read a book");
        todo.markAsDone();
        todo.unmark();
        assertEquals("[T] [ ] Read a book", todo.toString());
    }
}
