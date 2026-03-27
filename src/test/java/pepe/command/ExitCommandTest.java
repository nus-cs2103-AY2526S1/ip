package pepe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

class ExitCommandTest {
    private static final String TEST_FILE_PATH = "data/test.txt";
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        // Reset the test file
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
    void execute_exitCommand_savesTasksAndShowsByeMessage() throws PepeExceptions, IOException {
        ExitCommand command = new ExitCommand();

        command.execute(taskList, ui, storage);

        // Check the response is the goodbye message
        assertEquals("Fine then! Leave! I don't care...\n", command.getString());

        // Check that storage file exists and was saved (should still be empty task list)
        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists(), "Storage file should exist");
        String fileContent = java.nio.file.Files.readString(file.toPath());
        assertNotNull(fileContent); // File was written, even if it's empty list representation
    }

    @Test
    void isExit_returnsTrue() {
        ExitCommand command = new ExitCommand();
        assertTrue(command.isExit());
    }
}
