package Note.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    @Test
    public void testMarkAndUnmarkTask() {
        Task t = new Todo("Read book");
        assertEquals("[ ][T] Read book", t.toString());

        t.markAsDone();
        assertEquals("[X][T] Read book", t.toString());

        t.markAsNotDone();
        assertEquals("[ ][T] Read book", t.toString());
    }

    @Test
    public void testToStringForAllTasks() {
        Task todo = new Todo("Read book");
        Task deadline = new Deadline("Submit report", "2025-09-05T00:00");
        Task event = new Event("Team meeting", "2025-09-06T14:00", "2025-09-06T16:00");

        assertTrue(todo.toString().contains("[T] Read book"));

        // Only check the important parts, not exact formatting
        String dStr = deadline.toString();
        assertTrue(dStr.contains("[D] Submit report"));
        assertTrue(dStr.contains("2025") || dStr.contains("Sep"));

        String eStr = event.toString();
        assertTrue(eStr.contains("[E] Team meeting"));
        assertTrue(eStr.contains("2025") || eStr.contains("Sep"));
    }

    @Test
    public void testTodoTypeIcon() {
        Task t = new Todo("Read book");
        assertEquals("T", t.getTypeIcon());
    }

    @Test
    public void testDeadlineTypeIconAndBy() {
        Deadline d = new Deadline("Submit report", "2025-09-05T00:00");
        assertEquals("D", d.getTypeIcon());

        String output = d.toString();
        assertTrue(output.contains("Submit report"));
        assertTrue(output.contains("2025") || output.contains("Sep"));
    }

    @Test
    public void testEventTypeIconAndFromTo() {
        Event e = new Event("Team meeting", "2025-09-06T14:00", "2025-09-06T16:00");
        assertEquals("E", e.getTypeIcon());

        String output = e.toString();
        assertTrue(output.contains("Team meeting"));
        assertTrue(output.contains("2025") || output.contains("Sep"));
    }

    @Test
    public void testTaskListOperations() {
        TaskList list = new TaskList();
        list.addTask(new Todo("Task 1"));
        list.addTask(new Deadline("Task 2", "2025-09-07T12:00"));

        assertEquals(2, list.size());
        assertEquals("[ ][T] Task 1", list.getTask(0).toString());
    }
}
