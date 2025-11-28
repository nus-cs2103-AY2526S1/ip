package nailongbot.command;

import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;
import nailongbot.utils.Storage;
import nailongbot.exception.JkBotException;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// Tester for UnknownCommand Class
class UnknownCommandTest {

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
    void executeReturnsUnrecognisedMessage() throws JkBotException {
        UnknownCommand command = new UnknownCommand();
        String result = command.execute(taskList, ui, storage);
        assertTrue(result.contains("Unrecognised command. Try again"));
    }

    @Test
    void isExitReturnsFalse() {
        UnknownCommand command = new UnknownCommand();
        assertFalse(command.isExit());
    }
}
