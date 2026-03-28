package choicebot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Unit tests for the {@link DeadlineCommand} class.
 */
public class DeadlineCommandTest {

    private TaskList tasks;
    private UI ui;
    private Storage storage;

    /**
     * Sets up a fresh environment before each test.
     * Uses a temporary test file to avoid overwriting production storage.
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new UI();

        String testFilePath = "data/test_tasks_temp.txt";
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            testFile.delete();
        }
        storage = new Storage(testFilePath);
    }

    /**
     * Tests that a valid deadline input adds a Deadline task correctly to the TaskList.
     */
    @Test
    public void execute_validDeadline_addsTask() throws ChoiceBotException {
        String input = "Deadline /by 2025-08-29";
        DeadlineCommand deadline = new DeadlineCommand(input);
        deadline.execute(tasks, ui, storage);

        assertEquals(1, tasks.size(), "TaskList should contain one task after adding a valid deadline");
        assertEquals("[D][ ] Deadline (by: Aug 29 2025)", tasks.getTask(0).toString(),
                "Task string representation should match the expected format");
    }

    /**
     * Tests that a deadline with an invalid date format throws ChoiceBotException.
     */
    @Test
    public void execute_invalidDeadlineFormat_throwsException() {
        String input = "Submit report /by 29-08-2025"; // Wrong format
        DeadlineCommand deadline = new DeadlineCommand(input);

        assertThrows(ChoiceBotException.class, () -> deadline.execute(tasks, ui, storage),
                "Invalid date format should throw ChoiceBotException");
    }

    /**
     * Tests that a missing /by keyword throws ChoiceBotException.
     */
    @Test
    public void execute_missingBy_throwsException() {
        String input = "Submit report 29-08-2025"; // Missing /by
        DeadlineCommand deadline = new DeadlineCommand(input);

        assertThrows(ChoiceBotException.class, () -> deadline.execute(tasks, ui, storage),
                "Missing /by keyword should throw ChoiceBotException");
    }

    /**
     * Tests that a missing date after /by throws ChoiceBotException.
     */
    @Test
    public void execute_missingDate_throwsException() {
        String input = "Submit report /by"; // Missing date
        DeadlineCommand deadline = new DeadlineCommand(input);

        assertThrows(ChoiceBotException.class, () -> deadline.execute(tasks, ui, storage),
                "Missing date after /by should throw ChoiceBotException");
    }
}
