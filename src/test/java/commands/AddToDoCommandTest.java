package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import tasks.ToDos;
import ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class AddToDoCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() throws Exception {
        taskList = new TaskList();
        ui = new Ui();
        Path tempFile = Files.createTempFile("testTasks", ".txt");
        tempFile.toFile().deleteOnExit();
        storage = new Storage(tempFile.toString());
        // ensure starting empty
        storage.saveTasks(taskList);
    }

    @Test
    void execute_withValidDescription_addsTaskAndPersists() throws Exception {
        String description = "Test Task 1";
        AddTodoCommand cmd = new AddTodoCommand(description);

        cmd.execute(taskList, ui, storage);

        // TaskList updated: check by type and description
        assertTrue(taskList.getTasks().stream().anyMatch(t -> t instanceof ToDos && t.getDescription().equals(description)),
                "TaskList should contain the new todo");

        // Persisted to storage: load into a fresh TaskList and verify
        TaskList loaded = new TaskList();
        storage.loadTasks(loaded);
        assertTrue(loaded.getTasks().stream().anyMatch(t -> t instanceof ToDos && t.getDescription().equals(description)),
                "Loaded tasks should contain the new todo");
    }

    @Test
    void execute_withEmptyDescription_throwsJackException() {
        AddTodoCommand cmd = new AddTodoCommand("");
        assertThrows(JackException.class, () -> cmd.execute(taskList, ui, storage));
    }

    @Test
    void execute_withNullDescription_throwsJackException() {
        AddTodoCommand cmd = new AddTodoCommand(null);
        assertThrows(JackException.class, () -> cmd.execute(taskList, ui, storage));
    }
}
