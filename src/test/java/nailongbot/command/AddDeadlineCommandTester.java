package nailongbot.command;

import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;
import nailongbot.utils.Storage;
import nailongbot.exception.JkBotException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// Tester for AddDeadlineCommand Class
class AddDeadlineCommandTest {

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
    void executeAddsDeadlineAndReturnsMessage() throws JkBotException {
        String deadlineArgs = "Submit report /by 28/8/2025 2359";
        AddDeadlineCommand command = new AddDeadlineCommand(deadlineArgs);
        String result = command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertTrue(taskList.getTask(0).toString().contains("Submit report"));
        assertTrue(taskList.getTask(0).toString().toLowerCase().contains("aug 28 2025 11:59 pm"));

        assertTrue(result.contains("Got it. I've added this deadline"));
        assertTrue(result.contains("Submit report"));
        assertTrue(result.contains("Now you have 1 tasks in the list"));
    }

    @Test
    void isExitReturnsFalse() {
        AddDeadlineCommand command = new AddDeadlineCommand("Submit report /by 28/8/2025 2359");
        assertFalse(command.isExit());
    }
}
