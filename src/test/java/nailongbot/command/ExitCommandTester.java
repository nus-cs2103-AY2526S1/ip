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

// Tester for ExitCommand Class
class ExitCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        taskList.addTask(new Todo("Test task"));
        ui = new Ui();
        storage = new Storage();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void executeSavesTasksAndReturnsGoodbye() throws JkBotException {
        ExitCommand command = new ExitCommand();
        String result = command.execute(taskList, ui, storage);
        assertTrue(result.contains("Bye") || result.contains("Hope to see you again"));
    }

    @Test
    void isExitReturnsTrue() {
        ExitCommand command = new ExitCommand();
        assertTrue(command.isExit());
    }
}
