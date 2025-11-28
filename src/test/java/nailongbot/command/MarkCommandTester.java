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

// Tester for MarkCommand Class
class MarkCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() throws Exception {
        taskList = new TaskList();
        taskList.addTask(new Todo("Test task")); // Initially not done

        ui = new Ui();
        storage = new Storage();

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void executeMarksTaskAndReturnsMessage() throws JkBotException {
        MarkCommand command = new MarkCommand(0);
        String result = command.execute(taskList, ui, storage);

        assertTrue(taskList.getTask(0).isDone());
        assertTrue(result.contains("Good job for completing!!!"));
        assertTrue(result.contains("Test task"));
    }

    @Test
    void isExitReturnsFalse() {
        MarkCommand command = new MarkCommand(0);
        assertFalse(command.isExit());
    }
}
