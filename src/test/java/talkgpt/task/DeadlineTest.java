package talkgpt.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static talkgpt.task.Deadline.deserialize;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testDeserialize() {
        String task = "D|false|return book|2025-12-03T18:00|reading";
        Deadline expected = new Deadline("return book", "2025-12-03T18:00", false, "reading");
        assertEquals(expected, deserialize(task.split("\\s*\\|\\s*")));
    }

    @Test
    public void testSerialize() {
        Deadline task = new Deadline("return book", "2025-12-03T18:00", false, "reading");
        String expected = "D|false|return book|2025-12-03T18:00|reading";
        assertEquals(expected, task.serialize());
    }

    @Test
    public void testgetTag() {
        Deadline task = new Deadline("return book", "2025-12-03T18:00", false, "reading");
        String expected = "reading";
        assertEquals(expected, task.getTag());
    }

    @Test
    public void testgetDescription() {
        Deadline task = new Deadline("return book", "2025-12-03T18:00", false, "reading");
        String expected = "return book";
        assertEquals(expected, task.getDescription());
    }

    @Test
    public void testgetStatus() {
        Deadline task = new Deadline("return book", "2025-12-03T18:00", false, "reading");
        boolean expected = false;
        assertEquals(expected, task.getStatus());
    }

    @Test
    public void testMark() {
        Deadline task = new Deadline("return book", "2025-12-03T18:00", false, "reading");
        task.mark();
        boolean expected = true;
        assertEquals(expected, task.getStatus());
    }

    @Test
    public void testUnmark() {
        Deadline task = new Deadline("return book", "2025-12-03T18:00", true, "reading");
        task.unmark();
        boolean expected = false;
        assertEquals(expected, task.getStatus());
    }
}
