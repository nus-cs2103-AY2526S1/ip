package nailongbot.command;

import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;
import nailongbot.utils.Storage;
import nailongbot.exception.JkBotException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// Tester for AddToDoCommand Class
class AddTodoCommandTest {

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
    void executeAddsTodoAndReturnsMessage() throws JkBotException {
        AddTodoCommand command = new AddTodoCommand("Read book");
        String result = command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertEquals("Read book", taskList.getTask(0).getDesc());

        assertTrue(result.contains("Got it. I've added this task"));
        assertTrue(result.contains("Read book"));
        assertTrue(result.contains("Now you have 1 tasks in the list"));
    }

    @Test
    void isExitReturnsFalse() {
        AddTodoCommand command = new AddTodoCommand("Read book");
        assertFalse(command.isExit());
    }
}
