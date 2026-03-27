package pepe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.Events;
import pepe.task.Task;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

class EventCommandTest {
    private static final String TEST_FILE_PATH = "data/test.txt";
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        // Ensure test file exists and is cleared
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            assertTrue(file.delete(), "Failed to delete old test file");
        }
        file.getParentFile().mkdirs();
        assertTrue(file.createNewFile(), "Failed to create test file");

        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage(TEST_FILE_PATH);
    }

    @Test
    void execute_addEventTask_success() throws PepeExceptions, IOException {
        Task event = new Events("Party", "2025-09-20", "2025-10-10");
        EventCommand command = new EventCommand(event);

        command.execute(taskList, ui, storage);

        // Check response message
        String response = command.getString();
        assertTrue(response.contains("Sure let's add this task that you'll definitely do:"));
        assertTrue(response.contains("Party"));

        // Check that task list has the event
        assertEquals(1, taskList.size());
        assertEquals(event.toString(), taskList.get(0).toString());

        // Check storage file is not empty (task persisted)
        String fileContent = java.nio.file.Files.readString(new File(TEST_FILE_PATH).toPath());
        assertTrue(fileContent.contains("Party"));
    }

    @Test
    void constructor_invalidDateFormat_throwsPepeExceptions() {
        PepeExceptions ex = assertThrows(PepeExceptions.class, () -> {
            new Events("Meeting", "2025-02-30", "2025-03-01"); // Invalid start date
        });
        assertTrue(ex.getMessage().contains("Invalid Input: Please check the format"));
    }

    @Test
    void constructor_endDateBeforeToday_throwsPepeExceptions() {
        String pastDate = LocalDate.now().minusDays(1).toString();
        PepeExceptions ex = assertThrows(PepeExceptions.class, () -> {
            new Events("Old Event", LocalDate.now().toString(), pastDate);
        });
        assertTrue(ex.getMessage().contains("End Date cannot be before today"));
    }

    @Test
    void constructor_startAfterEnd_throwsPepeExceptions() {
        String start = LocalDate.now().plusDays(10).toString();
        String end = LocalDate.now().plusDays(5).toString();
        PepeExceptions ex = assertThrows(PepeExceptions.class, () -> {
            new Events("Weird Event", start, end);
        });
        assertTrue(ex.getMessage().contains("Start Date cannot be after End Date"));
    }
}
