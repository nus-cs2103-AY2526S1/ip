package jaiden.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void isByTest() {
        assertTrue(new Deadline("test", LocalDate.parse("2025-08-22")).isBy(LocalDate.parse("2025-08-22")));
    }

    @Test
    public void saveTest() {
        assertEquals("D | 0 | test | 2025-08-22", new Deadline("test", LocalDate.parse("2025-08-22")).save());
    }

    @Test
    public void toStringTest() {
        assertEquals("[D][ ] test (by: Aug 22 2025)", new Deadline("test", LocalDate.parse("2025-08-22")).toString());
    }
}
