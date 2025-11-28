package nailongbot.command;

import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;
import nailongbot.utils.Storage;
import nailongbot.exception.JkBotException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// Tester for AddEventCommand Class
class AddEventCommandTest {

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
    void executeAddsEventAndReturnsMessage() throws JkBotException {
        String eventArgs = "Conference /from 28/8/2025 1000 /to 28/8/2025 1800";
        AddEventCommand command = new AddEventCommand(eventArgs);
        String result = command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertTrue(taskList.getTask(0).toString().contains("Conference"));
        String eventString = taskList.getTask(0).toString().toLowerCase();
        assertTrue(eventString.contains("aug 28 2025 10:00 am"));
        assertTrue(eventString.contains("aug 28 2025 6:00 pm"));

        assertTrue(result.contains("Got it. I've added this event"));
        assertTrue(result.contains("Conference"));
        assertTrue(result.contains("Now you have 1 tasks in the list"));
    }

    @Test
    void isExitReturnsFalse() {
        AddEventCommand command = new AddEventCommand("Conference /from 28/8/2025 1000 /to 28/8/2025 1800");
        assertFalse(command.isExit());
    }
}
