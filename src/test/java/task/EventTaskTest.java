package task;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
public class EventTaskTest {

    @Test
    void constructor_defaultIsNotDone() {
        EventTask task = new EventTask(
                "NUS Hackathon",
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 2)
        );

        assertFalse(task.isDone, "Task should start undone by default");
        assertEquals("E | 0 | NUS Hackathon | 2025-01-01 | 2025-01-02", task.toStorage());
        assertEquals("[E][] NUS Hackathon (from: Jan 1 2025 to: Jan 2 2025)", task.toString());
    }

    @Test
    void constructor_respectsIsDoneFlag() {
        EventTask task = new EventTask(
                "Career Fair",
                true,
                LocalDate.of(2025, 3, 15),
                LocalDate.of(2025, 3, 16)
        );

        assertTrue(task.isDone, "Task should start as done when flag is true");
        assertEquals("E | 1 | Career Fair | 2025-03-15 | 2025-03-16", task.toStorage());
        assertEquals("[E][X] Career Fair (from: Mar 15 2025 to: Mar 16 2025)", task.toString());
    }

    @Test
    void markDone_changesStateAndToString() {
        EventTask task = new EventTask(
                "Midterms",
                LocalDate.of(2025, 10, 10),
                LocalDate.of(2025, 10, 12)
        );

        assertFalse(task.isDone);

        assertTrue(task.markDone(), "Should return true when marking not-yet-done task");
        assertTrue(task.isDone);
        assertEquals("[E][X] Midterms (from: Oct 10 2025 to: Oct 12 2025)", task.toString());

        // Mark again should return false and keep state
        assertFalse(task.markDone(), "Should return false when already done");
        assertTrue(task.isDone);
    }

    @Test
    void markUndone_changesStateAndToString() {
        EventTask task = new EventTask(
                "Project Sprint",
                true,
                LocalDate.of(2025, 7, 20),
                LocalDate.of(2025, 7, 21)
        );

        assertTrue(task.isDone);

        assertTrue(task.markUndone(), "Should return true when undoing a done task");
        assertFalse(task.isDone);
        assertEquals("[E][] Project Sprint (from: Jul 20 2025 to: Jul 21 2025)", task.toString());

        // Unmark again should return false and keep state
        assertFalse(task.markUndone(), "Should return false when already undone");
        assertFalse(task.isDone);
    }

    @Test
    void storageAndString_formatsConsistently() {
        LocalDate from = LocalDate.of(2025, 11, 5);
        LocalDate to   = LocalDate.of(2025, 11, 6);

        EventTask undone = new EventTask("Open House", from, to);
        EventTask done   = new EventTask("Open House", true, from, to);

        assertEquals("E | 0 | Open House | 2025-11-05 | 2025-11-06", undone.toStorage());
        assertEquals("[E][] Open House (from: Nov 5 2025 to: Nov 6 2025)", undone.toString());

        assertEquals("E | 1 | Open House | 2025-11-05 | 2025-11-06", done.toStorage());
        assertEquals("[E][X] Open House (from: Nov 5 2025 to: Nov 6 2025)", done.toString());
    }

    @Test
    void getDate_returnsFromDate() {
        LocalDate fromDate = LocalDate.of(2025, 11, 5);
        LocalDate toDate = LocalDate.of(2025, 11, 10);
        EventTask task = new EventTask("test event", fromDate, toDate);
        assertEquals(fromDate, task.getDate(), "getDate should return the from date");
    }

    @Test
    void getDate_preservesDateAfterStateChange() {
        LocalDate fromDate = LocalDate.of(2025, 11, 5);
        LocalDate toDate = LocalDate.of(2025, 11, 10);
        EventTask task = new EventTask("test event", fromDate, toDate);

        task.markDone();
        assertEquals(fromDate, task.getDate(), "From date should remain unchanged after marking done");

        task.markUndone();
        assertEquals(fromDate, task.getDate(), "From date should remain unchanged after marking undone");
    }
}
