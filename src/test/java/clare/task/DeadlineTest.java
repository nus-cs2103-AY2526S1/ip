package clare.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import clare.exception.StringConvertExceptions;

public class DeadlineTest {

    @Test
    void testConstructor_validInput_noTime() throws StringConvertExceptions {
        Deadline deadline = new Deadline("submit report", "2023-10-26", false);
        assertEquals("submit report", deadline.getTitle());
        assertEquals("0", deadline.getIsDoneInt());
        assertEquals("D", deadline.getTypeString());
        assertEquals(TaskType.D, deadline.getType());
    }

    @Test
    void testConstructor_validInput_withTime() throws StringConvertExceptions {
        Deadline deadline = new Deadline("attend meeting", "2023-11-15 14:30", false);
        assertEquals("attend meeting", deadline.getTitle());
        assertEquals("0", deadline.getIsDoneInt());
        assertEquals("D", deadline.getTypeString());
        assertEquals(TaskType.D, deadline.getType());
    }

    @Test
    void testConstructor_nowKeyword() throws StringConvertExceptions {
        Deadline deadline = new Deadline("finish now", "now", false);
        assertEquals("finish now", deadline.getTitle());
        assertEquals("0", deadline.getIsDoneInt());
        assertEquals("D", deadline.getTypeString());
        assertEquals(TaskType.D, deadline.getType());
    }

    @Test
    void testConstructor_invalidDateFormat() {
        assertThrows(StringConvertExceptions.class, () -> new Deadline("invalid deadline", "26-10-2023", false));
    }

    @Test
    void testConstructor_invalidDateTimeFormat() {
        assertThrows(StringConvertExceptions.class, () -> new Deadline("invalid time", "2023-10-26 14-30", false));
    }

    @Test
    void testCheckDeadline_sameDate() throws StringConvertExceptions {
        Deadline deadline = new Deadline("pay bills", "2023-12-01", false);
        assertTrue(deadline.checkDeadline(LocalDate.parse("2023-12-01")));
    }

    @Test
    void testCheckDeadline_differentDate() throws StringConvertExceptions {
        Deadline deadline = new Deadline("buy groceries", "2023-12-02", false);
        assertFalse(deadline.checkDeadline(LocalDate.parse("2023-12-01")));
    }

    @Test
    void testCompareDeadline_laterDate() throws StringConvertExceptions {
        Deadline deadline1 = new Deadline("later task", "2024-01-01", false);
        Deadline deadline2 = new Deadline("earlier task", "2023-12-31", false);
        assertEquals(1, deadline1.compareDeadline(deadline2));
    }

    @Test
    void testCompareDeadline_earlierDate() throws StringConvertExceptions {
        Deadline deadline1 = new Deadline("earlier task", "2023-12-31", false);
        Deadline deadline2 = new Deadline("later task", "2024-01-01", false);
        assertEquals(-1, deadline1.compareDeadline(deadline2));
    }

    @Test
    void testCompareDeadline_sameDate() throws StringConvertExceptions {
        Deadline deadline1 = new Deadline("same date task", "2024-01-01", false);
        Deadline deadline2 = new Deadline("another same date task", "2024-01-01", false);
        assertEquals(0, deadline1.compareDeadline(deadline2));
    }

    @Test
    void testToString_noTime() throws StringConvertExceptions {
        Deadline deadline = new Deadline("read book", "2023-10-28", false);
        assertEquals("[D][ ] read book (by: Oct 28 2023)", deadline.toString());
    }

    @Test
    void testToString_withTime() throws StringConvertExceptions {
        Deadline deadline = new Deadline("project due", "2023-10-29 17:00", false);
        assertEquals("[D][ ] project due (by: Oct 29 2023 17:00)", deadline.toString());
    }

    @Test
    void testToSaveString_noTime() throws StringConvertExceptions {
        Deadline deadline = new Deadline("no time", "2023-10-30", false);
        assertEquals("D|0|no time|2023-10-30", deadline.toSaveString());
    }

    @Test
    void testToSaveString_withTime() throws StringConvertExceptions {
        Deadline deadline = new Deadline("with time", "2023-10-31 09:00", false);
        assertEquals("D|0|with time|2023-10-31 09:00", deadline.toSaveString());
    }
}
