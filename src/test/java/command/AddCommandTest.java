package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import misc.TaskBotException;
import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;





public class AddCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        tasks = new TaskList();
        ui = new Ui();
        Path tempFile = Files.createTempFile("taskbot", ".txt");
        storage = new Storage(tempFile.toString());
    }

    @Test
    public void execute_addTodo_success() throws TaskBotException {
        Todo todo = new Todo("read book");
        AddCommand cmd = new AddCommand(todo);

        cmd.execute(tasks, ui, storage);

        assertEquals(1, tasks.size(), "Task list should have 1 task after add");
        assertEquals("[T][ ] read book", tasks.getTasks().get(0).toString());
    }

    @Test
    public void execute_storageThrowsException_taskBotExceptionThrown() {
        Storage badStorage = new Storage("/invalid-path/doesnotexist.txt");
        Todo todo = new Todo("read book");
        AddCommand cmd = new AddCommand(todo);

        assertThrows(TaskBotException.class, () -> {
            cmd.execute(tasks, ui, badStorage);
        });
    }
}
