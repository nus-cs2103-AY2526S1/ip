package nailongbot.command;

import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;
import nailongbot.utils.Storage;
import nailongbot.task.Deadline;
import nailongbot.task.Event;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// Tester for ShowCommand Class
class ShowCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() throws Exception {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
        System.setOut(new PrintStream(outContent));

        // Add some tasks
        taskList.addTask(new Deadline("Submit report", "28/8/2025 2359"));
        taskList.addTask(new Event("Conference", "27/8/2025 1000", "29/8/2025 1800"));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void executeReturnsTasksOnGivenDate() throws Exception {
        ShowCommand command = new ShowCommand("28/8/2025");
        String result = command.execute(taskList, ui, storage);
        assertTrue(result.contains("Submit report"));
        assertTrue(result.contains("Conference"));
    }

    @Test
    void executeReturnsNoTasksMessageIfNone() throws Exception {
        ShowCommand command = new ShowCommand("1/1/2050");
        String result = command.execute(taskList, ui, storage);
        assertTrue(result.contains("No tasks/events found on 1/1/2050"));
    }

    @Test
    void isExitReturnsFalse() {
        ShowCommand command = new ShowCommand("28/8/2025");
        assertFalse(command.isExit());
    }
}
