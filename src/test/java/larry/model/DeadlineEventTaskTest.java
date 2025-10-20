package larry.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineEventTaskTest {

    @Test
    void task_markAndUnmark_flipState() {
        Task t = new Todo("read");
        assertFalse(t.isDone());
        t.markDone();
        assertTrue(t.isDone());
        t.markUndone();
        assertFalse(t.isDone());
    }

    @Test
    void deadline_prettyPrinting_containsDateAndTime() {
        Deadline d = new Deadline("submit", "2025-10-31 23:59");
        String s = d.toString();
        assertTrue(s.contains("Oct") || s.contains("October"));
        assertTrue(s.contains("31"));
        assertTrue(s.contains("2025"));
        assertTrue(s.toLowerCase().contains("11:59") || s.toLowerCase().contains("23:59"));
    }

    @Test
    void event_prettyPrinting_containsFromAndTo() {
        Event e = new Event("party", "2025-11-01 0900", "2025-11-01 1100");
        String s = e.toString();
        assertTrue(s.contains("Nov") || s.contains("November"));
        assertTrue(s.contains("2025"));
        assertTrue(s.contains("9:00") || s.contains("09:00"));
        assertTrue(s.contains("11:00"));
    }
}
