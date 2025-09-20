package piper.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import piper.PiperException;

class DeadlineTest {

    @Test
    void toSerializedLine_success() throws PiperException {
        Deadline d = new Deadline("return book", "2025-09-01");
        // New deadlines are not done
        String line = d.toSerializedLine();
        assertEquals("D | 0 | return book | 2025-09-01", line);
    }

    @Test
    void toString_reformatsDate() throws PiperException {
        Deadline d = new Deadline("return book", "2025-09-01");
        String s = d.toString();
        assertTrue(s.contains("Sep"));
        assertTrue(s.contains("2025"));
    }

    @Test
    void toString_keepsRawText() throws PiperException {
        Deadline d = new Deadline("return book", "next Friday");
        String s = d.toString();
        assertTrue(s.contains("next Friday")); // fallback to raw text
    }
}
