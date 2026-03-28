package clippy.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Test class for Command and its subclasses.
 * Created with the aid of Copilot, mainly to generate boilerplate test methods through autocompletion.
 * The tests ensure that each command executes without throwing exceptions
 */
public class CommandTest {
    private static final String filePath = "test_tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    @BeforeEach
    void setUp() {
        storage = new Storage(filePath); // temporary file for testing
        tasks = new TaskList();
        ui = new Ui();
    }

    @AfterAll
    static void tearDown() {
        // Clean up test storage file if necessary
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void addDeadlineCommandExecutesWithoutError() {
        Command addDeadlineCommand = new AddDeadlineCommand("submit report /by 2024-12-01");
        assertDoesNotThrow(() -> addDeadlineCommand.execute(tasks, ui, storage));
        assertEquals(1, tasks.size());
        assertEquals("[D] [ ] submit report (by: Dec 1 2024)", tasks.get(0).toString());
    }

    @Test
    void addToDoCommandExecutesWithoutError() {
        Command addToDoCommand = new AddTodoCommand("read book");
        assertDoesNotThrow(() -> addToDoCommand.execute(tasks, ui, storage));
        assertEquals(1, tasks.size());
        assertEquals("[T] [ ] read book", tasks.get(0).toString());
    }

    @Test
    void addEventCommandExecutesWithoutError() {
        Command addEventCommand = new AddEventCommand("project /from 2024-11-15 /to 2024-11-16");
        assertDoesNotThrow(() -> addEventCommand.execute(tasks, ui, storage));
        assertEquals(1, tasks.size());
        assertEquals("[E] [ ] project (from: Nov 15 2024 to: Nov 16 2024)", tasks.get(0).toString());
    }

    @Test
    void deleteCommandExecutesWithoutError() {
        tasks.add(new Task("read book"));
        Command deleteCommand = new DeleteCommand(1);
        assertDoesNotThrow(() -> deleteCommand.execute(tasks, ui, storage));
        assertEquals(0, tasks.size());
    }

    @Test
    void listCommandExecutesWithoutError() {
        tasks.add(new Task("read book"));
        Command listCommand = new ListCommand();
        assertDoesNotThrow(() -> listCommand.execute(tasks, ui, storage));
    }

    @Test
    void markCommandExecutesWithoutError() {
        tasks.add(new Task("read book"));
        Command markCommand = new MarkCommand(1);
        assertDoesNotThrow(() -> markCommand.execute(tasks, ui, storage));
        assertTrue(tasks.get(0).isCompleted());
    }

    @Test
    void unmarkCommandExecutesWithoutError() {
        Task task = new Task("read book");
        task.markAsCompleted();
        tasks.add(task);
        Command unmarkCommand = new UnmarkCommand(1);
        assertDoesNotThrow(() -> unmarkCommand.execute(tasks, ui, storage));
        assertFalse(tasks.get(0).isCompleted());
    }

    @Test
    void helpCommandExecutesWithoutError() {
        Command helpCommand = new HelpCommand();
        assertDoesNotThrow(() -> helpCommand.execute(tasks, ui, storage));
    }

    @Test
    void findCommandExecutesWithoutError() {
        tasks.add(new Task("read book"));
        tasks.add(new Task("write code"));
        Command findCommand = new FindCommand("read");
        assertDoesNotThrow(() -> findCommand.execute(tasks, ui, storage));
    }

    @Test
    void isExitReturnsCorrectValue() {
        Command addDeadlineCommand = new AddDeadlineCommand("submit report /by 2024-12-01");
        Command addToDoCommand = new AddTodoCommand("read book");
        Command addEventCommand = new AddEventCommand("team meeting /at 2024-11-15");
        Command deleteCommand = new DeleteCommand(1);
        Command listCommand = new ListCommand();
        Command markCommand = new MarkCommand(1);
        Command unmarkCommand = new UnmarkCommand(1);
        Command helpCommand = new HelpCommand();
        Command findCommand = new FindCommand("read");

        assertFalse(addDeadlineCommand.isExit());
        assertFalse(addToDoCommand.isExit());
        assertFalse(addEventCommand.isExit());
        assertFalse(deleteCommand.isExit());
        assertFalse(listCommand.isExit());
        assertFalse(markCommand.isExit());
        assertFalse(unmarkCommand.isExit());
        assertFalse(helpCommand.isExit());
        assertFalse(findCommand.isExit());
    }
}
