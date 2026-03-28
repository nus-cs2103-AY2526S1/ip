package choicebot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Unit tests for the {@link EventCommand} class.
 */
public class EventCommandTest {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
     * Tests that a valid event input adds an Event task correctly to the TaskList.
     */
    @Test
    public void execute_validEvent_addsTask() throws ChoiceBotException {
        String input = "Meeting /from 2025-09-19 10:00 /to 2025-09-19 11:00";
        EventCommand event = new EventCommand(input);
        event.execute(tasks, ui, storage);

        assertEquals(1, tasks.size(), "TaskList should contain one task after adding a valid event");

        LocalDateTime start = LocalDateTime.parse("2025-09-19 10:00", DATE_FORMAT);
        LocalDateTime end = LocalDateTime.parse("2025-09-19 11:00", DATE_FORMAT);

        String expected = String.format("[E][ ] Meeting (from: %s to: %s)",
                start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        assertEquals(expected, tasks.getTask(0).toString(), "Event task string should match expected format");
    }



    /**
     * Tests that an event with missing /from keyword throws ChoiceBotException.
     */
    @Test
    public void execute_missingFrom_throwsException() {
        String input = "Event Meeting /to 2025-09-19T11:00";
        EventCommand event = new EventCommand(input);

        assertThrows(ChoiceBotException.class,
                () -> event.execute(tasks, ui, storage),
                "Missing /from keyword should throw ChoiceBotException");
    }

    /**
     * Tests that an event with missing /to keyword throws ChoiceBotException.
     */
    @Test
    public void execute_missingTo_throwsException() {
        String input = "Event Meeting /from 2025-09-19T10:00";
        EventCommand event = new EventCommand(input);

        assertThrows(ChoiceBotException.class, () -> event.execute(tasks, ui, storage),
                "Missing /to keyword should throw ChoiceBotException");
    }

    /**
     * Tests that an event with invalid date format throws ChoiceBotException.
     */
    @Test
    public void execute_invalidDateFormat_throwsException() {
        String input = "Event Meeting /from 19-09-2025T10:00 /to 19-09-2025T11:00";
        EventCommand event = new EventCommand(input);

        assertThrows(ChoiceBotException.class, () -> event.execute(tasks, ui, storage),
                "Invalid date format should throw ChoiceBotException");
    }
}
