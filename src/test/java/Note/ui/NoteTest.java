package Note.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    @Test
    public void testMarkAndUnmarkTask() {
        Task t = new Todo("Read book");
        assertEquals("[T][ ] Read book", t.toString());

        t.markAsDone();
        assertEquals("[T][X] Read book", t.toString());

        t.markAsNotDone();
        assertEquals("[T][ ] Read book", t.toString());
    }

    @Test
    public void testToStringForAllTasks() {
        Task todo = new Todo("Read book");
        Task deadline = new Deadline("Submit report", "5/9/2025 0000");
        Task event = new Event("Team meeting", "6/9/2025 1400", "6/9/2025 1600");

        assertEquals("[T][ ] Read book", todo.toString());
        assertEquals("[D][ ] Submit report (by: Sep 5 2025, 12:00 am)", deadline.toString());
        assertEquals("[E][ ] Team meeting (from: Sep 6 2025, 2:00 pm to: Sep 6 2025, 4:00 pm)", event.toString());
    }


    @Test
    public void testTodoTypeIcon() {
        Task t = new Todo("Read book");
        assertEquals("T", t.getTypeIcon());
    }

    @Test
    public void testDeadlineTypeIconAndBy() {
        Deadline d = new Deadline("Submit report", "5/9/2025 0000");
        assertEquals("D", d.getTypeIcon());

        String output = d.toString();
        assertTrue(output.contains("Submit report"));
        assertTrue(output.contains("2025") || output.contains("Sep"));
    }

    @Test
    public void testEventTypeIconAndFromTo() {
        Event e = new Event("Team meeting", "6/9/2025 1400", "6/9/2025 1600");
        assertEquals("E", e.getTypeIcon());

        String output = e.toString();
        assertTrue(output.contains("Team meeting"));
        assertTrue(output.contains("2025") || output.contains("Sep"));
    }

    @Test
    public void testTaskListOperations() {
        TaskList list = new TaskList();
        list.addTask(new Todo("Task 1"));
        list.addTask(new Deadline("Task 2", "7/9/2025 1200"));

        assertEquals(2, list.size());
        assertEquals("[T][ ] Task 1", list.getTask(0).toString());
    }
}
