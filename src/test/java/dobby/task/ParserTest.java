package dobby;

import dobby.exceptions.DobbyException;
import dobby.exceptions.InvalidCommandException;
import dobby.exceptions.InvalidTaskException;
import dobby.task.Deadline;
import dobby.task.Event;
import dobby.task.ToDo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

class ParserTest {

    @TempDir
    Path tempDir;

    private Parser setupParser(TaskList tasks) {
        Path testFile = tempDir.resolve("test.txt");
        Storage storage = new Storage(testFile);
        return new Parser(tasks, storage);
    }

    /** Tests todo command with valid and empty description */
    @Test
    void handleTodoCommand_validAndEmptyDescription() throws DobbyException {
        TaskList tasks = new TaskList();
        Parser parser = setupParser(tasks);

        // Valid todo
        parser.handleCommand("todo 5Buy milk");
        assertEquals(1, tasks.size());
        assertEquals("Buy milk", tasks.getTasks().get(0).getDescription());

        // Empty description
        Exception exception = assertThrows(InvalidTaskException.class, () ->
                parser.handleCommand("todo   ")
        );
        assertEquals("Task description cannot be empty!", exception.getMessage());
    }

    /** Tests list command with empty and non-empty list */
    @Test
    void handleListCommand_emptyAndNonEmpty() throws DobbyException {
        TaskList tasks = new TaskList();
        Parser parser = setupParser(tasks);

        // Empty list
        assertEquals("You have no tasks in your list.", parser.handleCommand("list"));

        // Add a task
        parser.handleCommand("todo Read book");

        // Non-empty list
        String output = parser.handleCommand("list");
        assertTrue(output.contains("1. [T][ ] Read book"));
    }

    /** Tests bye command */
    @Test
    void handleByeCommand() throws DobbyException {
        Parser parser = setupParser(new TaskList());
        assertEquals("Bye! Hope to see you again soon!", parser.handleCommand("bye"));
    }

    /** Tests help command */
    @Test
    void handleHelpCommand() throws DobbyException {
        Parser parser = setupParser(new TaskList());
        String output = parser.handleCommand("help");
        assertTrue(output.contains("todo <description> - Add a ToDo task"));
    }

    /** Tests mark/unmark commands */
    @Test
    void handleMarkAndUnmark_validAndInvalid() throws DobbyException {
        TaskList tasks = new TaskList();
        Parser parser = setupParser(tasks);

        parser.handleCommand("todo Test task");

        // Mark valid
        String markResult = parser.handleCommand("mark 1");
        assertTrue(markResult.contains("marked this task as done"));

        // Unmark valid
        String unmarkResult = parser.handleCommand("unmark 1");
        assertTrue(unmarkResult.contains("marked this task as not done"));

        // Invalid mark
        Exception ex1 = assertThrows(InvalidCommandException.class, () ->
                parser.handleCommand("mark 5")
        );
        assertTrue(ex1.getMessage().contains("Invalid task number"));

        // Invalid unmark
        Exception ex2 = assertThrows(InvalidCommandException.class, () ->
                parser.handleCommand("unmark 0")
        );
        assertTrue(ex2.getMessage().contains("Invalid task number"));
    }

    /** Tests delete command */
    @Test
    void handleDelete_validAndInvalid() throws DobbyException {
        TaskList tasks = new TaskList();
        Parser parser = setupParser(tasks);

        parser.handleCommand("todo Temp");

        // Valid delete
        String output = parser.handleCommand("delete 1");
        assertTrue(output.contains("I've removed this task"));
        assertEquals(0, tasks.size());

        // Invalid delete
        Exception ex = assertThrows(InvalidCommandException.class, () ->
                parser.handleCommand("delete 3")
        );
        assertTrue(ex.getMessage().contains("Invalid task number"));
    }

    /** Tests deadline command */
    @Test
    void handleDeadline_validAndInvalid() throws DobbyException {
        TaskList tasks = new TaskList();
        Parser parser = setupParser(tasks);

        // Valid
        parser.handleCommand("deadline Submit report /by 2025-12-01 1800");
        assertEquals(1, tasks.size());
        assertTrue(tasks.getTasks().get(0) instanceof Deadline);

        // Invalid format
        Exception ex1 = assertThrows(InvalidCommandException.class, () ->
                parser.handleCommand("deadline Just words")
        );
        assertTrue(ex1.getMessage().contains("Invalid deadline"));

        // Invalid date
        Exception ex2 = assertThrows(InvalidCommandException.class, () ->
                parser.handleCommand("deadline Something /by not-a-date")
        );
        assertTrue(ex2.getMessage().contains("Invalid deadline"));
    }

    /** Tests event command */
    @Test
    void handleEvent_validAndInvalid() throws DobbyException {
        TaskList tasks = new TaskList();
        Parser parser = setupParser(tasks);

        // Valid
        parser.handleCommand("event Conference /from 2025-05-01 0900 /to 2025-05-01 1700");
        assertEquals(1, tasks.size());
        assertTrue(tasks.getTasks().get(0) instanceof Event);

        // Invalid format
        Exception ex1 = assertThrows(InvalidCommandException.class, () ->
                parser.handleCommand("event Wrong input")
        );
        assertTrue(ex1.getMessage().contains("Invalid event"));

        // Invalid date
        Exception ex2 = assertThrows(InvalidCommandException.class, () ->
                parser.handleCommand("event Bad /from notadate /to again")
        );
        assertTrue(ex2.getMessage().contains("Invalid event"));
    }

    /** Tests unknown command */
    @Test
    void handleUnknownCommand() {
        Parser parser = setupParser(new TaskList());
        Exception ex = assertThrows(InvalidCommandException.class, () ->
                parser.handleCommand("nonsense command")
        );
        assertTrue(ex.getMessage().contains("Unknown command"));
    }
}
