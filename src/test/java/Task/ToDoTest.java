package Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    void toString_notDone_formatsCorrectly() {
        Todo t = new Todo("read book", false);
        assertEquals("[T][ ] read book", t.toString());
    }

    @Test
    void toString_done_formatsCorrectly() {
        Todo t = new Todo("read book", true);
        assertEquals("[T][X] read book", t.toString());
    }

    @Test
    void toSaveString_matchesSpec() {
        Todo t = new Todo("read book", true);
        assertEquals("T|true|read book", t.toSaveString());
    }
}
