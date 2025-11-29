package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link ToDo}.
 */
public class ToDoTest {

    @Test
    public void toString_notDone_correctFormat() {
        final ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void typeCode_returnsT() {
        final ToDo todo = new ToDo("read book");
        assertEquals("T", todo.typeCode());
    }

    @Test
    public void toSaveString_containsTypeDoneAndName() {
        final ToDo todo = new ToDo("read book");
        assertEquals("T|0|read book", todo.toSaveString());
    }

    @Test
    public void toString_done_correctFormat() {
        final ToDo todo = new ToDo("read book");
        todo.setDone(true);
        assertEquals("[T][X] read book", todo.toString());
    }
}
