package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    @Test
    public void toString_correctFormat() {
        Deadline d = new Deadline("submit assignment", LocalDate.of(2025, 9, 30));
        assertEquals("[D][ ] submit assignment (by: Sep 30 2025)", d.toString());
    }

    @Test
    public void toFileString_correctFormat() {
        Deadline d = new Deadline("submit assignment", LocalDate.of(2025, 9, 30));
        assertEquals("D | 0 | submit assignment | 2025-09-30", d.toFileString());
    }

    @Test
    public void equals_sameDeadline_true() {
        Deadline d1 = new Deadline("submit assignment", LocalDate.of(2025, 9, 30));
        Deadline d2 = new Deadline("submit assignment", LocalDate.of(2025, 9, 30));
        assertEquals(d1, d2);
    }

    @Test
    public void equals_differentDeadline_false() {
        Deadline d1 = new Deadline("submit assignment", LocalDate.of(2025, 9, 30));
        Deadline d2 = new Deadline("submit assignment", LocalDate.of(2025, 10, 1));
        assertNotEquals(d1, d2);
    }

    @Test
    void toString_containsTypeDescriptionAndDate() {
        LocalDate date = LocalDate.of(2025, 10, 21);
        Deadline d = new Deadline("submit report", date);

        String s = d.toString();
        assertTrue(s.contains("[D]"), "toString should include task type [D]");
        assertTrue(s.contains("submit report"), "toString should include description");
        assertTrue(s.contains(Task.dateToString(date)), "toString should include formatted date");
    }

    @Test
    void toFileString_startsWithD_containsSeparatorDescriptionAndIsoDate() {
        LocalDate date = LocalDate.of(2025, 10, 21);
        Deadline d = new Deadline("submit report", date);

        String f = d.toFileString();
        assertTrue(f.startsWith("D"), "toFileString should start with 'D'");
        assertTrue(f.contains(" | "), "toFileString should contain separator ' | '");
        assertTrue(f.contains("submit report"), "toFileString should include description");
        assertTrue(f.contains(date.toString()), "toFileString should include ISO date");
    }

    @Test
    void equals_behaviour() {
        LocalDate date = LocalDate.of(2025, 10, 21);
        Deadline a = new Deadline("task", date);
        Deadline b = new Deadline("task", date);
        Deadline c = new Deadline("task", date.plusDays(1));
        Object other = "not a deadline";

        assertEquals(a, b, "Deadlines with same description and date should be equal");
        assertNotEquals(a, c, "Deadlines with different dates should not be equal");
        assertNotEquals(a, other, "Deadline should not equal an object of different type");
        assertNotEquals(a, null, "Deadline should not equal null");
    }
}
