package pepe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.Task;
import pepe.task.ToDos;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

class DeleteCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private File testFile;

    @BeforeEach
    void setUp() throws IOException {
        // Ensure data directory exists
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        // Point storage to test.txt inside data directory
        testFile = new File(dataDir, "test.txt");

        // Clear file before each test
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
    void execute_validDelete_removesTaskAndSaves() throws Exception {
        // Add tasks
        Task todo1 = new ToDos("Task A");
        Task todo2 = new ToDos("Task B");
        tasks.addTask(todo1);
        tasks.addTask(todo2);

        storage.save(tasks); // save initial state

        // Delete first task
        int[] indices = {0};
        DeleteCommand command = new DeleteCommand(indices);
        command.execute(tasks, ui, storage);

        // Only one task should remain
        assertEquals(1, tasks.size());
        assertEquals(todo2.toString(), tasks.get(0).toString());

        // Response should match UI output
        String expectedResponse = ui.showUiDelete(tasks, todo1);
        assertEquals(expectedResponse, command.getString());

        // File should reflect updated task list
        String fileContent = Files.readString(testFile.toPath());
        assertEquals(true, fileContent.contains("Task B"));
        assertEquals(false, fileContent.contains("Task A"));
    }

    @Test
    void execute_invalidIndex_throwsPepeExceptions() {
        tasks.addTask(new ToDos("Task X"));
        int[] indices = {5}; // out of range

        DeleteCommand command = new DeleteCommand(indices);

        PepeExceptions exception = assertThrows(
                PepeExceptions.class, () -> command.execute(tasks, ui, storage)
        );

        assertEquals("There is no task at index: 6!\nAborting all Operations...", exception.getMessage());
    }

    @Test
    void execute_storageError_throwsPepeExceptions() {
        tasks.addTask(new ToDos("Task Y"));
        // Use directory instead of file to simulate save error
        Storage badStorage = new Storage("data");

        DeleteCommand command = new DeleteCommand(new int[]{0});

        PepeExceptions exception = assertThrows(
                PepeExceptions.class, () -> command.execute(tasks, ui, badStorage)
        );

        assertEquals(true, exception.getMessage().startsWith("Error saving file:"));
    }
}

