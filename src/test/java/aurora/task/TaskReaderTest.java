package aurora.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskReaderTest {
    @Test
    void read_validTodo_returnsTodo() {
        Task task = TaskReader.read("TODO read a book");
        assertInstanceOf(Todo.class, task);
        assertEquals("read a book", task.getDescription());
        assertFalse(task.isDone());
    }

    @Test
    void read_invalidTodo_throwsException() {
        Exception e = assertThrows(InvalidTaskException.class,
                () -> TaskReader.read("TODO"));
        assertTrue(e.getMessage().contains("Invalid todo format"));
    }

    @Test
    void read_validDeadline_returnsDeadline() {
        Task task = TaskReader.read("DEADLINE math quiz /by: 2025-12-12");
        assertInstanceOf(Deadline.class, task);
        assertEquals("math quiz", task.getDescription());
        assertFalse(task.isDone());
    }

    @Test
    void read_invalidDeadline_throwsException() {
        Exception e = assertThrows(InvalidTaskException.class,
                () -> TaskReader.read("DEADLINE project 2025"));
        assertTrue(e.getMessage().contains("Invalid deadline format"));
    }

    @Test
    void read_validEvent_returnsEvent() {
        Task task = TaskReader.read("EVENT midterms /from: 161025 /to: 191025");
        assertInstanceOf(Event.class, task);
        assertEquals("midterms", task.getDescription());
        assertFalse(task.isDone());
    }

    @Test
    void read_invalidEvent_throwsException() {
        Exception e = assertThrows(InvalidTaskException.class,
                () -> TaskReader.read("Event lecture from today to tomorrow"));
        assertTrue(e.getMessage().contains("Invalid event format"));
    }

    @Test
    void read_invalidInput_throwsException() {
        assertThrows(InvalidTaskException.class,
                () -> TaskReader.read("No task."));
    }

    @Test
    void read_invalidDate_throwsException() {
        assertThrows(InvalidTaskException.class,
                () -> TaskReader.read("deadline report /by: invalid-date"));
    }

    @Test
    void fromText_validTodo_returnsTodo() {
        Task task = TaskReader.fromText("T|true|read a book");
        assertInstanceOf(Todo.class, task);
        assertEquals("read a book", task.getDescription());
        assertTrue(task.isDone());
    }

    @Test
    void fromText_validDeadline_returnsDeadline() {
        Task task = TaskReader.fromText("D|false|medical checkup|2025-09-20");
        assertInstanceOf(Deadline.class, task);
        assertEquals("medical checkup", task.getDescription());
        assertFalse(task.isDone());
    }

    @Test
    void fromText_validEvent_returnsEvent() {
        Task task = TaskReader.fromText("E|true|basketball|2025-11-01|2025-11-02");
        assertInstanceOf(Event.class, task);
        assertEquals("basketball", task.getDescription());
        assertTrue(task.isDone());
    }

    @Test
    void fromText_invalidType_throwsException() {
        assertThrows(InvalidTaskException.class,
                () -> TaskReader.fromText("X|false|fake task"));
    }

    @Test
    void fromText_notEnoughFields_throwsException() {
        assertThrows(InvalidTaskException.class,
                () -> TaskReader.fromText("T|true"));
    }
}
