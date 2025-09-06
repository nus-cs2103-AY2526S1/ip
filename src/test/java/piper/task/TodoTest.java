package piper.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void toSerializedLine_success() {
        Todo t = new Todo("read book");
        String line = t.toSerializedLine();
        assertEquals("T | 0 | read book", line);
    }

    @Test
    void markDone_reformatsToSerializedLine() {
        Todo t = new Todo("do laundry");
        t.markDone();
        String line = t.toSerializedLine();
        assertEquals("T | 1 | do laundry", line);
    }

    @Test
    void toString_includesDescription() {
        Todo t = new Todo("walk the dog");
        String s = t.toString();
        assertTrue(s.contains("walk the dog"));
        // shows task type
        assertTrue(s.startsWith("[T]"));
    }
}
