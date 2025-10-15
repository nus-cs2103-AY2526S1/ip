package bro.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void constructor_setsDescriptionAndIsDone() {
        Todo t = new Todo("desc");
        assertEquals("desc", t.getDescription());
        assertFalse(t.isDone);
    }

    @Test
    public void toDataString_returnsCorrectFormat() {
        Todo t = new Todo("desc", true);
        assertEquals("T|1|desc", t.toDataString());
    }

    @Test
    public void toString_returnsCorrectFormat() {
        Todo t = new Todo("desc", false);
        assertTrue(t.toString().contains("[T] [ "));
        assertTrue(t.toString().contains("desc"));
    }
}
