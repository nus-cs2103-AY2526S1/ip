package pepe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.Deadlines;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

class DeadlineCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private File testFile;

    @BeforeEach
    void setUp() throws IOException {
        // Ensure the "data" directory exists
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        // Use test.txt inside data directory
        testFile = new File(dataDir, "test.txt");

        // Clear or create the file fresh before each test
        if (testFile.exists()) {
            Files.writeString(testFile.toPath(), "");
        } else {
            testFile.createNewFile();
        }

        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage(testFile.getPath());
    }

    @Test
    void execute_validDeadlineTask_addsTaskAndSaves() throws Exception {
        Deadlines deadline = new Deadlines("Submit assignment", "2025-09-20");
        DeadlineCommand command = new DeadlineCommand(deadline);

        command.execute(tasks, ui, storage);

        // Task added
        assertEquals(1, tasks.size());
        assertEquals(deadline.toString(), tasks.get(0).toString());

        // UI response matches
        String expectedResponse = ui.showUiAddDeadline(tasks, deadline);
        assertEquals(expectedResponse, command.getString());

        // File should not be empty
        String fileContent = Files.readString(testFile.toPath());
        assertEquals(false, fileContent.isBlank());
    }

    @Test
    void execute_invalidDateFormat_throwsPepeExceptions() {
        PepeExceptions exception = assertThrows(PepeExceptions.class, () -> {
            Deadlines invalidDeadline = new Deadlines("Task with bad format", "2025-02-31"); // invalid day
            DeadlineCommand command = new DeadlineCommand(invalidDeadline);
            command.execute(tasks, ui, storage);
        });

        assertTrue(exception.getMessage().contains("Invalid Input: Please check the format"));
    }

    @Test
    void execute_pastDate_throwsPepeExceptions() {
        PepeExceptions exception = assertThrows(PepeExceptions.class, () -> {
            Deadlines pastDeadline = new Deadlines("Task in the past", "2000-01-01"); // past date
            DeadlineCommand command = new DeadlineCommand(pastDeadline);
            command.execute(tasks, ui, storage);
        });

        assertTrue(exception.getMessage().contains("Dateline cannot be before today"));
    }

    @Test
    void execute_withInvalidStoragePath_throwsPepeExceptions() throws PepeExceptions {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        // Invalid path (points to a directory instead of file)
        Storage badStorage = new Storage("data");

        Deadlines deadline = new Deadlines("Submit assignment", "2025-09-20");
        DeadlineCommand command = new DeadlineCommand(deadline);

        PepeExceptions exception = assertThrows(
                PepeExceptions.class, () -> command.execute(tasks, ui, badStorage)
        );

        assertEquals(
                "Add a Deadline Task: deadline <task-name> /by <deadline> (In the format: yyyy-mm-dd)",
                exception.getMessage()
        );
    }
}
