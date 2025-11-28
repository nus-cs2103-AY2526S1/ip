package nailongbot.command;

import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;
import nailongbot.utils.Storage;
import nailongbot.task.Todo;
import nailongbot.exception.JkBotException;
import nailongbot.exception.InvalidTaskNumberException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// Tester for UnmarkCommand Class
class UnmarkCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() throws InvalidTaskNumberException {
        taskList = new TaskList();
        taskList.addTask(new Todo("Test task"));

        // Mark the task first to test unmarking
        taskList.markTask(0);

        ui = new Ui();
        System.setOut(new PrintStream(outContent));

        storage = new Storage();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void executeUnmarksTaskAndReturnsMessage() throws JkBotException {
        UnmarkCommand command = new UnmarkCommand(0);
        String result = command.execute(taskList, ui, storage);

        assertFalse(taskList.getTask(0).isDone());
        assertTrue(result.contains("You are undoing this task"));
        assertTrue(result.contains("Test task"));
    }

    @Test
    void isExitReturnsFalse() {
        UnmarkCommand command = new UnmarkCommand(0);
        assertFalse(command.isExit());
    }
}
