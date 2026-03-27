package jaiden.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void getStatusIconTest() {
        assertEquals(" ", new Task("test").getStatusIcon());
        assertEquals("X", new Task("test", true).getStatusIcon());
    }

    @Test
    public void markAsDoneTest() {
        Task test = new Task("test");
        test.markAsDone();
        assertEquals("X", test.getStatusIcon());
    }

    @Test
    public void markAsNotDoneTest() {
        Task test = new Task("test");
        test.markAsNotDone();
        assertEquals(" ", test.getStatusIcon());
    }

    @Test
    public void hasTextTest() {
        Task test = new Task("test");
        assertTrue(test.hasText("test"));
        assertFalse(test.hasText("test1"));
    }

    @Test
    public void saveTest() {
        assertEquals("0 | test", new Task("test").save());
        assertEquals("1 | test", new Task("test", true).save());
    }

    @Test
    public void toStringTest() {
        assertEquals("[ ] test", new Task("test").toString());
        assertEquals("[X] test", new Task("test", true).toString());
    }

    @Test
    public void equalsTest() {
        assertEquals(new Task("test"), new Task("test"));
        assertEquals(new Task("test", true), new Task("test", true));
        assertNotEquals(new Task("test1"), new Task("test2"));
        assertNotEquals(new Task("test", true), new Task("test", false));
    }
}
