package pepe.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.ToDos;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

class FindCommandTest {
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
    void execute_findMatchingTasks_returnsCorrectTasks() throws PepeExceptions {
        // Arrange
        taskList.addTask(new ToDos("Read Book"));
        taskList.addTask(new ToDos("Write Code"));
        taskList.addTask(new ToDos("Read Notes"));

        FindCommand command = new FindCommand("Read");

        // Act
        command.execute(taskList, ui, storage);

        // Assert
        String response = command.getString();
        assertTrue(response.contains("1. [T][ ] Read Book"),
                "Expected 'Read Book' to be in the results");
        assertTrue(response.contains("2. [T][ ] Read Notes"),
                "Expected 'Read Notes' to be in the results");
        assertFalse(response.contains("Write Code"),
                "Did not expect 'Write Code' in the results");
    }

    @Test
    void execute_findNoMatches_returnsEmptyMessage() throws PepeExceptions {
        // Arrange
        taskList.addTask(new ToDos("Clean Room"));
        taskList.addTask(new ToDos("Buy Milk"));

        FindCommand command = new FindCommand("Homework");

        // Act
        command.execute(taskList, ui, storage);

        // Assert
        String response = command.getString();
        assertTrue(response.contains("There are NO tasks that match your search!"),
                "Expected no matches message");
    }
}

