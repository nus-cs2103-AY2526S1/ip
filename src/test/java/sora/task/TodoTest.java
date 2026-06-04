package sora.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void toString_notDone_returnsCorrectFormat() {
        Todo todo = new Todo("Do something");
        assertEquals("[T][ ] Do something", todo.toString());
    }

    @Test
    public void toString_markedDone_returnsCorrectFormat() {
        Todo todo = new Todo("Do something");
        todo.markAsDone();
        assertEquals("[T][X] Do something", todo.toString());
    }

    @Test
    public void toFormat_todoTask_returnsCorrectFormat() {
        Todo todo = new Todo("Do something");
        assertEquals("T | 0 | Do something", todo.toFormat());
    }

    @Test
    public void toFormat_doneTodoTask_returnsCorrectFormat() {
        Todo todo = new Todo("Do something");
        todo.markAsDone();
        assertEquals("T | 1 | Do something", todo.toFormat());
    }
}
