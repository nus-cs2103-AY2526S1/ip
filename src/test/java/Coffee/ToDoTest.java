package Coffee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void constructor_blankDescription_throws() {
        assertThrows(IllegalArgumentException.class, () -> new ToDo("   "));
        assertThrows(IllegalArgumentException.class, () -> new ToDo(""));
    }

    @Test
    void constructor_default_isNotDone() {
        ToDo t = new ToDo("read book");
        // Verify via file/string formats so we don't rely on internals
        assertTrue(t.toString().startsWith("[T][ ]"));
        assertEquals("T |   | read book", t.toFileString());
    }

    @Test
    void toString_hasPrefixT_andContainsDescription() {
        ToDo t = new ToDo("submit report");
        String s = t.toString();
        assertTrue(s.startsWith("[T]"), "toString should start with [T]");
        assertTrue(s.contains("submit report"));
        // Also sanity-check the not-done status icon in the composite string
        assertTrue(s.contains("[ ]"), "New ToDo should be not done");
    }

    @Test
    void toFileString_hasTypeIconAndDescription_inThatOrder() {
        ToDo t = new ToDo("buy milk");
        // Not done:
        assertEquals("T |   | buy milk", t.toFileString());

        // Mark as done and check again
        t.markAsDone();
        assertEquals("T | X | buy milk", t.toFileString());
    }

    @Test
    void constructor_withIsDoneTrue_setsDoneStatus() {
        ToDo t = new ToDo("pay bills", true);
        // Check via observable outputs
        assertTrue(t.toString().contains("[X]"), "Should be marked done");
        assertEquals("T | X | pay bills", t.toFileString());
    }
}
