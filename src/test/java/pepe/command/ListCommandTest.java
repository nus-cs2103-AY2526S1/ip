package pepe.command;

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

class ListCommandTest {
    private static final String TEST_FILE_PATH = "data/test.txt";
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        // Reset test file
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
    void execute_listWithTasks_returnsAllTasks() throws PepeExceptions {
        taskList.addTask(new ToDos("Task A"));
        taskList.addTask(new ToDos("Task B"));

        ListCommand command = new ListCommand();
        command.execute(taskList, ui, storage);

        String response = command.getString();
        assertTrue(response.contains("1. [T][ ] Task A"), "Expected Task A in list");
        assertTrue(response.contains("2. [T][ ] Task B"), "Expected Task B in list");
    }

    @Test
    void execute_listEmpty_returnsEmptyMessage() throws PepeExceptions {
        ListCommand command = new ListCommand();
        command.execute(taskList, ui, storage);

        String response = command.getString();
        assertTrue(response.contains("WOW! Either you're really on task...or..."),
                "Expected empty list message");
    }
}
