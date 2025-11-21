package david.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toStringTest() throws Exception {
        Deadline test1 = new Deadline("test1", "2025-08-25");
        Deadline test2 = new Deadline("test2", "2025-08-25 1800");
        Deadline test3 = new Deadline("test3", "2025-8-25");
        assertEquals("[D][ ] test1 (by: Aug 25 2025)", test1.toString());
        assertEquals("[D][ ] test2 (by: Aug 25 2025, 6:00PM)", test2.toString());
        assertEquals("[D][ ] test3 (by: 2025-8-25)", test3.toString());
    }

    @Test
    public void markTest() throws Exception {
        Deadline test2 = new Deadline("test2", "2025-08-25 1800");
        test2.markAsDone();
        assertEquals("[D][X] test2 (by: Aug 25 2025, 6:00PM)", test2.toString());

        test2.markAsUndone();
        assertEquals("[D][ ] test2 (by: Aug 25 2025, 6:00PM)", test2.toString());
    }
}
