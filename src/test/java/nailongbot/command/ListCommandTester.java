package nailongbot.command;

import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;
import nailongbot.utils.Storage;
import nailongbot.task.Todo;
import nailongbot.exception.JkBotException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// Tester for ListCommand Class
class ListCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void executeReturnsMessageWhenTaskListEmpty() throws JkBotException {
        ListCommand command = new ListCommand();
        String result = command.execute(taskList, ui, storage);
        assertTrue(result.contains("No tasks in your list yet!"));
    }

    @Test
    void executeReturnsAllTasks() throws JkBotException {
        taskList.addTask(new Todo("Task 1"));
        taskList.addTask(new Todo("Task 2"));

        ListCommand command = new ListCommand();
        String result = command.execute(taskList, ui, storage);
        assertTrue(result.contains("1. [T][ ] Task 1") || result.contains("1. Task 1"));
        assertTrue(result.contains("2. [T][ ] Task 2") || result.contains("2. Task 2"));
    }

    @Test
    void isExitReturnsFalse() {
        ListCommand command = new ListCommand();
        assertFalse(command.isExit());
    }
}
