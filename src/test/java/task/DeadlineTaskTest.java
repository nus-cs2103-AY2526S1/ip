package task;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


public class DeadlineTaskTest {

    @Test
    void constructor_defaultIsNotDone() {
        DeadlineTask task = new DeadlineTask("return book", LocalDate.of(2025, 12, 31));
        assertFalse(task.isDone, "Task should start undone by default");
        assertEquals("D | 0 | return book | 2025-12-31", task.toStorage());
        assertEquals("[D][] return book (by: Dec 31 2025)", task.toString());
    }

    @Test
    void constructor_respectsIsDoneFlag() {
        DeadlineTask task = new DeadlineTask("submit report", true, LocalDate.of(2025, 1, 1));
        assertTrue(task.isDone, "Task should start as done when flag is true");
        assertEquals("D | 1 | submit report | 2025-01-01", task.toStorage());
        assertEquals("[D][X] submit report (by: Jan 1 2025)", task.toString());
    }

    @Test
    void markDone_changesStateAndToString() {
        DeadlineTask task = new DeadlineTask("read", LocalDate.of(2025, 6, 15));
        assertFalse(task.isDone);

        assertTrue(task.markDone(), "Should return true when marking not-yet-done task");
        assertTrue(task.isDone);
        assertEquals("[D][X] read (by: Jun 15 2025)", task.toString());

        // marking again should not change state
        assertFalse(task.markDone(), "Should return false when already done");
    }

    @Test
    void markUndone_changesStateAndToString() {
        DeadlineTask task = new DeadlineTask("review", true, LocalDate.of(2025, 6, 15));
        assertTrue(task.isDone);

        assertTrue(task.markUndone(), "Should return true when undoing a done task");
        assertFalse(task.isDone);
        assertEquals("[D][] review (by: Jun 15 2025)", task.toString());

        // unmarking again should not change state
        assertFalse(task.markUndone(), "Should return false when already undone");
    }

    @Test
    void storageAndString_formatsConsistently() {
        LocalDate date = LocalDate.of(2025, 11, 5);
        DeadlineTask undone = new DeadlineTask("abc", date);
        DeadlineTask done = new DeadlineTask("xyz", true, date);

        assertEquals("D | 0 | abc | 2025-11-05", undone.toStorage());
        assertEquals("[D][] abc (by: Nov 5 2025)", undone.toString());

        assertEquals("D | 1 | xyz | 2025-11-05", done.toStorage());
        assertEquals("[D][X] xyz (by: Nov 5 2025)", done.toString());
    }

    @Test
    void getDate_returnsDeadlineDate() {
        LocalDate date = LocalDate.of(2025, 11, 5);
        DeadlineTask task = new DeadlineTask("test task", date);
        assertEquals(date, task.getDate(), "getDate should return the deadline date");
    }

    @Test
    void getDate_preservesDateAfterStateChange() {
        LocalDate date = LocalDate.of(2025, 11, 5);
        DeadlineTask task = new DeadlineTask("test task", date);
        task.markDone();
        assertEquals(date, task.getDate(), "Date should remain unchanged after marking done");
        task.markUndone();
        assertEquals(date, task.getDate(), "Date should remain unchanged after marking undone");
    }
}
