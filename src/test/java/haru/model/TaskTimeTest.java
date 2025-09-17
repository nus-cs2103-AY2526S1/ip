package haru.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import haru.exception.HaruException;
import haru.exception.InvalidTimeException;

public class TaskTimeTest {
    @Test
    public void toStringTest() throws HaruException {
        assertEquals(
                "10 Dec 2025 23:59",
                new TaskTime(null, "10 Dec 2025 23:59").toString()
        );
        assertEquals(
                "10 Dec 2025 23:59",
                new TaskTime(null, "10/12/2025 23:59").toString()
        );
        assertEquals(
                "10 Dec 2025 23:59",
                new TaskTime(null, "2025-12-10 23:59").toString()
        );
        assertThrows(InvalidTimeException.class, () -> new TaskTime(null, "abc"));
    }
}
