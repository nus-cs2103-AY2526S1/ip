package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import task.ToDos;

/**
 * Test class for the {@link Ui} class.
 * Uses JUnit 5 to verify correctness of output and input handling.
 * Tests are performed by capturing console output and simulating user input.
 */
class UiTest {

    /** Captures printed output to System.out for verification. */
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /** Stores the original System.out to restore after tests. */
    private final PrintStream originalOut = System.out;

    /** Instance of Ui used in tests. */
    private Ui ui;

    /**
     * Sets up test environment before each test:
     * - Redirects System.out to capture output
     * - Sets a dummy input stream for reading commands
     * - Initializes the Ui instance
     */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        ByteArrayInputStream input = new ByteArrayInputStream("dummy command\n".getBytes());
        System.setIn(input);
        ui = new Ui();
    }

    /**
     * Restores original System.out and System.in after each test.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    /**
     * Tests that {@link Ui#showWelcome()} returns and prints the correct welcome message.
     */
    @Test
    void testShowWelcome() {
        String expected = "Hello! I'm Ineffa\nWhat can I do for you? Enter \"help\" if needed";
        String actual = Ui.showWelcome();
        assertEquals(expected, actual);
        assertTrue(outputStream.toString().contains(expected));
    }

    /**
     * Tests that {@link Ui#displayTasks(ArrayList)} correctly handles an empty task list.
     */
    @Test
    void testDisplayTasks_emptyList() {
        ArrayList<task.Task> tasks = new ArrayList<>();
        String result = ui.displayTasks(tasks);
        assertEquals("No tasks found", result);
        assertTrue(outputStream.toString().contains("No tasks found"));
    }

    /**
     * Tests that {@link Ui#displayTasks(ArrayList)} correctly formats and prints a list of tasks.
     */
    @Test
    void testDisplayTasks_withTasks() {
        ArrayList<task.Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("Read book"));
        tasks.add(new ToDos("Write code"));

        String result = ui.displayTasks(tasks);

        assertTrue(result.contains("Here are the tasks in your list:"));
        assertTrue(result.contains("1: [T][ ] Read book"));
        assertTrue(result.contains("2: [T][ ] Write code"));
        assertTrue(outputStream.toString().contains("1: [T][ ] Read book"));
        assertTrue(outputStream.toString().contains("2: [T][ ] Write code"));
    }

    /**
     * Tests that {@link Ui#exit()} prints the correct exit message.
     */
    @Test
    void testExitMessage() {
        ui.exit();
        assertTrue(outputStream.toString().contains("Bye. Hope to see you again soon!"));
    }

    /**
     * Tests that {@link Ui#getExitMessage()} returns the expected string.
     */
    @Test
    void testGetExitMessage() {
        assertEquals("Bye. Hope to see you again soon!", Ui.getExitMessage());
    }

    /**
     * Tests that {@link Ui#showError(String)} correctly prints the given error message.
     */
    @Test
    void testShowError() {
        ui.showError("This is an error");
        assertTrue(outputStream.toString().contains("This is an error"));
    }

    /**
     * Tests that {@link Ui#readCommand()} reads a command from input.
     * Input is simulated using a dummy string.
     */
    @Test
    void testReadCommand() {
        String command = ui.readCommand();
        assertEquals("dummy command", command);
    }
}
