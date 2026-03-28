package clare.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import clare.exception.StringConvertExceptions;

public class TaskTest {
    @Test
    public void testConvert_todo() throws StringConvertExceptions {
        String data = "T|0|read book";
        Task task = Task.convert(data);
        assertInstanceOf(Todo.class, task);
        assertEquals("[ ]", task.getIsDoneStatus());
        assertEquals("read book", task.getTitle());
    }

    @Test
    public void testConvert_deadline() throws StringConvertExceptions {
        String data = "D|1|return book|2023-10-26";
        Task task = Task.convert(data);
        assertInstanceOf(Deadline.class, task);
        assertEquals("[X]", task.getIsDoneStatus());
        assertEquals("return book", task.getTitle());
    }

    @Test
    public void testConvert_event() throws StringConvertExceptions {
        String data = "E|0|project meeting|2023-11-01|2023-11-02";
        Task task = Task.convert(data);
        assertInstanceOf(Event.class, task);
        assertEquals("[ ]", task.getIsDoneStatus());
        assertEquals("project meeting", task.getTitle());
    }

    @Test
    public void testMarkDone() throws StringConvertExceptions {
        String data = "T|0|read book";
        Task task = Task.convert(data);
        task.markDone();
        assertEquals("[X]", task.getIsDoneStatus());
        assertEquals("1", task.getIsDoneInt());
    }

    @Test
    public void testMarkUndone() throws StringConvertExceptions {
        String data = "T|1|read book";
        Task task = Task.convert(data);
        task.markUndone();
        assertEquals("[ ]", task.getIsDoneStatus());
        assertEquals("0", task.getIsDoneInt());
    }
}
