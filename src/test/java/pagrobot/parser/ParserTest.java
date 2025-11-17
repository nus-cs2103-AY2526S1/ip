package pagrobot.parser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pagrobot.PagroBot;
import pagrobot.errors.InvalidTaskException;
import pagrobot.tasklist.TaskList;
import pagrobot.tasks.Deadline;
import pagrobot.tasks.Event;
import pagrobot.tasks.Task;
import pagrobot.tasks.ToDo;

class ParserTest {

    private TaskList taskList;
    private Parser parser;

    @BeforeEach
    void setup() {
        taskList = new TaskList();
        parser = new Parser(taskList, PagroBot.getUi());
    }

    @Test
    void testCreateToDo() {
        Task task = parser.createTask("todo Buy milk", new String[] { "todo", "Buy", "milk" });
        assertTrue(task instanceof ToDo);
        assertEquals("Buy milk", ((ToDo) task).getName());
    }

    @Test
    void testCreateDeadline() {
        Task task = parser.createTask("deadline Submit report /by 01/09/2025 1800",
                new String[] { "deadline", "Submit", "report" });
        assertTrue(task instanceof Deadline);
        assertEquals("Submit report ", ((Deadline) task).getName());
        assertEquals(LocalDateTime.of(2025, 9, 1, 18, 0), ((Deadline) task).getDeadline());
    }

    @Test
    void testCreateEvent() {
        Task task = parser.createTask("event Party /from 01/09/2025 1800 /to 02/09/2025 1800",
                new String[] { "event", "Party" });
        assertTrue(task instanceof Event);
        assertEquals("Party ", ((Event) task).getName());
        assertEquals(LocalDateTime.of(2025, 9, 1, 18, 0), ((Event) task).getFrom());
        assertEquals(LocalDateTime.of(2025, 9, 2, 18, 0), ((Event) task).getTo());
    }

    @Test
    void testCreateInvalidTask() {
        InvalidTaskException ex = assertThrows(
            InvalidTaskException.class, () -> parser.createTask("random text", new String[] {"random", "text"})
        );
        assertTrue(ex.getMessage().contains("Unknown task type"));
    }
}
