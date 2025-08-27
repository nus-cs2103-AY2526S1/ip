package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toFileFormat_notDone_correctFormat() {
        Todo todo = new Todo("read book");
        assertEquals("T | 0 | read book", todo.toFileFormat());
    }

    @Test
    public void toFileFormat_done_correctFormat() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertEquals("T | 1 | read book", todo.toFileFormat());
    }

    @Test
    public void emptyDescription_allowed() {
        Todo todo = new Todo("");
        assertEquals("T | 0 | ", todo.toFileFormat());
        assertEquals("[T][ ] ", todo.toString());
    }

    @Test
    public void specialCharactersInDescription_preserved() {
        Todo todo = new Todo("!@#$%^");
        assertEquals("T | 0 | !@#$%^", todo.toFileFormat());
        assertEquals("[T][ ] !@#$%^", todo.toString());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertEquals("[T][X] read book", todo.toString());
    }
}
