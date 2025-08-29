package piper.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeadlineTest {

    @Test
    void toSerializedLine_success() {
        Deadline d = new Deadline("return book", "2025-09-01");
        // New deadlines are not done
        String line = d.toSerializedLine();
        assertEquals("D | 0 | return book | 2025-09-01", line);
    }

    @Test
    void toString_reformatsDate() {
        Deadline d = new Deadline("return book", "2025-09-01");
        String s = d.toString();
        assertTrue(s.contains("Sep"));
        assertTrue(s.contains("2025"));
    }

    @Test
    void toString_keepsRawText() {
        Deadline d = new Deadline("return book", "next Friday");
        String s = d.toString();
        assertTrue(s.contains("next Friday")); // fallback to raw text
    }
}