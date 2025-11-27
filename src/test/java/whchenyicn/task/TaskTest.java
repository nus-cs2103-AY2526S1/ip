package whchenyicn.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    void constructorTest() {
        Task t = new Task("read book");
        assertEquals("? | 0 | read book", t.toSave());
        assertEquals("[ ] read book", t.toString());
    }

    @Test
    void markDoneTest() {
        Task t = new Task("do homework");
        t.markDone();
        assertEquals("? | 1 | do homework", t.toSave());
        assertEquals("[X] do homework", t.toString());
    }

    @Test
    void unmarkTest() {
        Task t = new Task("buy milk");
        t.markDone();
        t.unmark();
        assertEquals("? | 0 | buy milk", t.toSave());
        assertEquals("[ ] buy milk", t.toString());
    }

    @Test
    void MarkUnmarkTest() {
        Task t = new Task("exercise");
        assertEquals(" ", t.getStatus());

        t.markDone();
        assertEquals("X", t.getStatus());

        t.unmark();
        assertEquals(" ", t.getStatus());

        t.markDone();
        assertEquals("X", t.getStatus());
    }

    @Test
    void emptyDescTest() {
        Task t = new Task("");
        assertEquals("? | 0 | ", t.toSave());
        assertEquals("[ ] ", t.toString());
    }

}
