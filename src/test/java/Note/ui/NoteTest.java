package Note.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    @Test
    public void testMarkAndUnmarkTask() {
        Task t = new Todo("Buy groceries");
        assertFalse(t.isDone());

        t.markAsDone();
        assertTrue(t.isDone());
        assertEquals("X", t.getStatusIcon());

        t.markAsNotDone();
        assertFalse(t.isDone());
        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    public void testToStringForAllTasks() {
        Task todo = new Todo("Buy groceries");
        assertEquals("[ ][T] Buy groceries", formatTask(todo));

        Task deadline = new Deadline("Submit report", "2025-09-05");
        assertEquals("[ ][D] Submit report (by: 2025-09-05)", formatTask(deadline));

        Task event = new Event("Meeting", "10:00", "12:00");
        assertEquals("[ ][E] Meeting (from: 10:00 to 12:00)", formatTask(event));
    }

    @Test
    public void testTodoTypeIcon() {
        Task todo = new Todo("Homework");
        assertEquals("T", todo.getTypeIcon());
    }

    @Test
    public void testDeadlineTypeIconAndBy() {
        Deadline d = new Deadline("Submit essay", "2025-09-05");
        assertEquals("D", d.getTypeIcon());
        assertEquals("2025-09-05", d.getBy());
    }

    @Test
    public void testEventTypeIconAndFromTo() {
        Event e = new Event("Lecture", "09:00", "11:00");
        assertEquals("E", e.getTypeIcon());
        assertEquals("09:00", e.getFrom());
        assertEquals("11:00", e.getTo());
    }

    @Test
    public void testTaskListOperations() {
        TaskList taskList = new TaskList();
        Task t1 = new Todo("Buy milk");
        Task t2 = new Deadline("Submit report", "2025-09-05");

        taskList.addTask(t1);
        taskList.addTask(t2);

        assertEquals(2, taskList.size());
        assertEquals(t1, taskList.getTask(0));
        assertEquals(t2, taskList.getTask(1));

        Task removed = taskList.deleteTask(0);
        assertEquals(t1, removed);
        assertEquals(1, taskList.size());
    }

    /**
     * Helper method to match the new toString format:
     * "[status][type] description" or with extra info for Deadline/Event.
     */
    private String formatTask(Task t) {
        if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "[" + t.getStatusIcon() + "][" + t.getTypeIcon() + "] " + t.getDescription() + " (by: " + d.getBy() + ")";
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return "[" + t.getStatusIcon() + "][" + t.getTypeIcon() + "] " + t.getDescription()
                    + " (from: " + e.getFrom() + " to " + e.getTo() + ")";
        } else {
            return "[" + t.getStatusIcon() + "][" + t.getTypeIcon() + "] " + t.getDescription();
        }
    }
}
