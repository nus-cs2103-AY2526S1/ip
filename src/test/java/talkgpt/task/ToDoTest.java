package talkgpt.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static talkgpt.task.ToDo.deserialize;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testDeserialize() {
        String task = "T|false|read book|reading";
        ToDo expected = new ToDo("read book", false, "reading");
        assertEquals(expected, deserialize(task.split("\\s*\\|\\s*")));
    }

    @Test
    public void testSerialize() {
        ToDo task = new ToDo("read book", false, "reading");
        String expected = "T|false|read book|reading";
        assertEquals(expected, task.serialize());
    }

    @Test
    public void testgetTag() {
        ToDo task = new ToDo("read book", false, "reading");
        String expected = "reading";
        assertEquals(expected, task.getTag());
    }

    @Test
    public void testgetDescription() {
        ToDo task = new ToDo("read book", false, "reading");
        String expected = "read book";
        assertEquals(expected, task.getDescription());
    }

    @Test
    public void testgetStatus() {
        ToDo task = new ToDo("read book", false, "reading");
        boolean expected = false;
        assertEquals(expected, task.getStatus());
    }

    @Test
    public void testMark() {
        ToDo task = new ToDo("read book", false, "reading");
        task.mark();
        boolean expected = true;
        assertEquals(expected, task.getStatus());
    }

    @Test
    public void testUnmark() {
        ToDo task = new ToDo("read book", true, "reading");
        task.unmark();
        boolean expected = false;
        assertEquals(expected, task.getStatus());
    }
}
