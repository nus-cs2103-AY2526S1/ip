package itachi.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import itachi.task.Deadline;



public class DeadlineTest {

    @Test
    public void constructor_validDateString_parsesDateCorrectly() {
        Deadline d = new Deadline("submit assignment", "2025-08-30");
        assertEquals(LocalDate.of(2025, 8, 30), d.getBy());
    }

    @Test
    public void toString_taskNotDone_returnsUnmarkedFormat() {
        Deadline d = new Deadline("submit assignment", "2025-08-30");
        String expected = "[D][ ] submit assignment (by: Aug 30 2025)";
        assertEquals(expected, d.toString());
    }

    @Test
    public void toString_taskMarkedDone_returnsMarkedFormat() {
        Deadline d = new Deadline("submit assignment", "2025-08-30");
        d.markAsDone();
        String expected = "[D][X] submit assignment (by: Aug 30 2025)";
        assertEquals(expected, d.toString());
    }

    @Test
    public void toSaveFormat_taskNotDone_returnsUnmarkedSaveFormat() {
        Deadline d = new Deadline("submit assignment", "2025-08-30");
        String expected = "D | 0 | submit assignment | 2025-08-30";
        assertEquals(expected, d.toSaveFormat());
    }

    @Test
    public void toSaveFormat_taskMarkedDone_returnsMarkedSaveFormat() {
        Deadline d = new Deadline("submit assignment", "2025-08-30");
        d.markAsDone();
        String expected = "D | 1 | submit assignment | 2025-08-30";
        assertEquals(expected, d.toSaveFormat());
    }

    @Test
    public void markAsNotDone_afterMarkingDone_unmarksCorrectly() {
        Deadline deadline = new Deadline("submit assignment", "2025-08-30");
        deadline.markAsDone();
        deadline.markAsNotDone();
        assertEquals("[D][ ] submit assignment (by: Aug 30 2025)", deadline.toString());
        assertEquals("D | 0 | submit assignment | 2025-08-30", deadline.toSaveFormat());
        assertEquals(" ", deadline.getStatusIcon());
    }
}
