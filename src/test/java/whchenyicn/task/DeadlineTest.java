package whchenyicn.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    void toSaveTest() {
        Deadline d = new Deadline("read book", " 2025-10-01 ");
        assertEquals("D | 0 | read book | 2025-10-01", d.toSave());
    }

    @Test
    void toStringTest() {
        Deadline d = new Deadline("submit report", "2025-12-31");
        String s = d.toString();
        assertTrue(s.startsWith("[D]"));
        assertTrue(s.contains("submit report"));
        assertTrue(s.contains("(by: 2025-12-31)"));
    }

    @Test
    void illegalArgumentTest() {
        assertThrows(IllegalArgumentException.class, () -> new Deadline("x", "31-12-2025"));
        assertThrows(IllegalArgumentException.class, () -> new Deadline("x", "2025/12/31"));
        assertThrows(IllegalArgumentException.class, () -> new Deadline("x", "asbdajbk"));
    }

}
