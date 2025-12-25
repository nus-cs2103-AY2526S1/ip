package khat.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void constructor_givenDescriptionAndIsDone_setsFieldsCorrectly() {
        Todo todo = new Todo("Read book", false);
        assertEquals("Read book", todo.description);
        assertFalse(todo.isDone);
    }

    @Test
    public void toSaveString_doneTask_returnsCorrectFormat() {
        Todo todo = new Todo("Read book", true);
        assertEquals("T | 1 | Read book", todo.toSaveString());
    }

    @Test
    public void toString_notDoneTask_returnsCorrectFormat() {
        Todo todo = new Todo("Read book", false);
        assertEquals("[T][ ] Read book", todo.toString());
    }
}
