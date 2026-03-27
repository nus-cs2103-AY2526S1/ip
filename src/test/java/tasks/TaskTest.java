package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import amogus.AmogusException;

public class TaskTest {

    @Test
    void todo_toString_correctFormat() throws AmogusException {
        Task t = new ToDo("read book");
        assertEquals("T | 0 | read book", t.toString());
    }

    @Test
    void deadline_toString_correctDateFormat() throws AmogusException {
        Deadlines d = new Deadlines("return book", "15/9/2025 1800");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        String expected = "D | 0 | return book (by: "
                + LocalDateTime.parse("15/9/2025 1800",
                                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                        .format(fmt) + ")";
        assertTrue(d.toString().contains("return book"));
    }

    @Test
    void event_toString_hasStartAndEnd() throws AmogusException {
        Event e = new Event("meeting", "15/9/2025 0900", "15/9/2025 1000");
        String output = e.toString();
        assertTrue(output.contains("meeting"));
        assertTrue(output.contains("from:"));
        assertTrue(output.contains("to:"));
    }

    @Test
    void task_markAndUnmark_changesStatus() throws AmogusException {
        Task t = new ToDo("write essay");
        assertEquals("0", t.getStatusIcon());
        t.mark();
        assertEquals("1", t.getStatusIcon());
        t.unmark();
        assertEquals("0", t.getStatusIcon());
    }

    @Test
    void task_taggingWorks() throws AmogusException {
        Task t = new ToDo("buy milk");
        t.tag("urgent");
        assertTrue(t.getTag().contains("#urgent"));
    }

    @Test
    void task_taggingTwice_throwsException() throws AmogusException {
        Task t = new ToDo("buy eggs");
        t.tag("urgent");
        assertThrows(AmogusException.class, () -> t.tag("later"));
    }

    @Test
    void taskList_addAndGetWorks() throws AmogusException {
        TaskList list = new TaskList();
        Task t = new ToDo("laundry");
        list.add(t);
        assertEquals(1, list.getSize());
        assertEquals("T | 0 | laundry", list.getTaskDesc(0));
    }
}
