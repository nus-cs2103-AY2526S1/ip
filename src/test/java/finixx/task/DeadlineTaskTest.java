package finixx.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the DeadlineTask class.
 */
public class DeadlineTaskTest {
    @Test
    public void testToSaveFormatString_doneTask_withoutNotes() {
        DeadlineTask t = new DeadlineTask("submit assignment", true, "", "by 2025-10-30");
        assertEquals("D | 1 | submit assignment | by 2025-10-30 | ", t.toSaveFormatString());
    }

    @Test
    public void testToSaveFormatString_doneTask_withNotes() {
        DeadlineTask t = new DeadlineTask("submit assignment", true, "CS2103T", "by 2025-10-30");
        assertEquals("D | 1 | submit assignment | by 2025-10-30 | CS2103T", t.toSaveFormatString());
    }

    @Test
    public void testToSaveFormatString_undoneTask_withoutNotes() {
        DeadlineTask t = new DeadlineTask("submit assignment", false, "", "by 2025-10-30");
        assertEquals("D | 0 | submit assignment | by 2025-10-30 | ", t.toSaveFormatString());
    }

    @Test
    public void testToSaveFormatString_undoneTask_withNotes() {
        DeadlineTask t = new DeadlineTask("submit assignment", false, "CS2103T", "by 2025-10-30");
        assertEquals("D | 0 | submit assignment | by 2025-10-30 | CS2103T", t.toSaveFormatString());
    }

    @Test
    public void testToString_doneTask_withoutNotes() {
        DeadlineTask t = new DeadlineTask("submit assignment", true, "", "2025-10-30");
        assertEquals("[D][X] submit assignment (by: Oct 30 2025)", t.toString());
    }

    @Test
    public void testToString_doneTask_withNotes() {
        DeadlineTask t = new DeadlineTask("submit assignment", true, "CS2103T", "2025-10-30");
        assertEquals("[D][X] submit assignment (by: Oct 30 2025) <CS2103T>", t.toString());
    }

    @Test
    public void testToString_undoneTask_withoutNotes() {
        DeadlineTask t = new DeadlineTask("submit assignment", false, "", "2025-10-30");
        assertEquals("[D][ ] submit assignment (by: Oct 30 2025)", t.toString());
    }

    @Test
    public void testToString_undoneTask_withNotes() {
        DeadlineTask t = new DeadlineTask("submit assignment", false, "CS2103T", "2025-10-30");
        assertEquals("[D][ ] submit assignment (by: Oct 30 2025) <CS2103T>", t.toString());
    }

    @Test
    public void testSaveAndLoadConsistency() {
        DeadlineTask original = new DeadlineTask("finish report", false, "work", "2025-10-30");
        String saveFormat = original.toSaveFormatString();
        Task loaded = Task.fromSaveFormat(saveFormat);

        assertEquals(original.toSaveFormatString(), loaded.toSaveFormatString());
    }

    @Test
    public void testGetDeadline() {
        DeadlineTask t = new DeadlineTask("submit assignment", false, "CS2103T", "2025-10-30");
        assertEquals("2025-10-30", t.getDeadline());
    }
}
