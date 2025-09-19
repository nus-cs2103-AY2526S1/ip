package jone;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void markAsDone_marksTaskCorrectly() {
        Task t = new Todo("read book");
        t.mark();
        assertTrue(t.isDone(), "Task should be marked as done");
    }

    @Test
    public void toString_formatCorrect() {
        Task t = new Todo("read book");
        assertEquals("[T][ ] read book", t.toString());
        t.mark();
        assertEquals("[T][X] read book", t.toString());
    }

    @Test
    public void unmark_revertsTaskToNotDone() {
        Task t = new Todo("read book");
        t.mark();
        assertTrue(t.isDone(), "Task should be marked done before unmarking");
        t.unmark();
        assertFalse(t.isDone(), "Task should be unmarked and not done");
    }

    @Test
    public void todo_storesDescriptionCorrectly() {
        Task t = new Todo("buy milk");
        assertEquals("[T][ ] buy milk", t.toString());
    }

    @Test
    public void deadline_toStringFormatCorrect() throws JoneException {
        Task d = new Deadline("submit homework", "2025-09-20");
        assertEquals("[D][ ] submit homework (by: Sep 20 2025)", d.toString());
    }

    @Test
    public void event_toStringFormatCorrect() throws JoneException {
        Task e = new Event("project meeting", "2025-09-21 1400", "2025-09-21 1600");
        assertEquals("[E][ ] project meeting (from: Sep 21 2025, 2:00 pm to: Sep 21 2025, 4:00 pm)", e.toString());
    }

    @Test
    public void mark_deadlineMarksCorrectly() throws JoneException {
        Task d = new Deadline("submit homework", "2025-09-20");
        d.mark();
        assertTrue(d.isDone(), "Deadline should be marked as done");
        assertEquals("[D][X] submit homework (by: Sep 20 2025)", d.toString());
    }

    // ========================
    // Extra Todo Tests
    // ========================
    @Test
    public void todo_isNotDoneByDefault() {
        Task t = new Todo("exercise");
        assertFalse(t.isDone(), "New Todo should not be marked as done by default");
    }

    // ========================
    // Extra Deadline Tests
    // ========================
    @Test
    public void deadline_isNotDoneByDefault() throws JoneException {
        Task d = new Deadline("pay bills", "2025-10-01");
        assertFalse(d.isDone(), "New Deadline should not be done by default");
    }

    @Test
    public void deadline_markUnmark_worksCorrectly() throws JoneException {
        Task d = new Deadline("renew license", "2025-11-15");
        d.mark();
        assertTrue(d.isDone(), "Deadline should be marked done");
        d.unmark();
        assertFalse(d.isDone(), "Deadline should be unmarked");
    }

    // ========================
    // Extra Event Tests
    // ========================
    @Test
    public void event_isNotDoneByDefault() throws JoneException {
        Task e = new Event("concert", "2025-12-01 1900", "2025-12-01 2200");
        assertFalse(e.isDone(), "New Event should not be done by default");
    }

    @Test
    public void event_markUnmark_worksCorrectly() throws JoneException {
        Task e = new Event("orientation", "2026-01-10 0900", "2026-01-10 1700");
        e.mark();
        assertTrue(e.isDone(), "Event should be marked done");
        e.unmark();
        assertFalse(e.isDone(), "Event should be unmarked");
    }

    // ========================
    // Exception Tests
    // ========================
    @Test
    public void deadline_invalidDate_throwsException() {
        assertThrows(JoneException.class, () -> {
            new Deadline("assignment", "15-11-2025"); // wrong format
        });
    }

    @Test
    public void event_invalidDateTime_throwsException() {
        assertThrows(JoneException.class, () -> {
            new Event("meeting", "2025/11/15 0900", "2025/11/15 1100"); // wrong format
        });
    }
}
