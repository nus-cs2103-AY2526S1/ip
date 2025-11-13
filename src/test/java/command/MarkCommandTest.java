package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


public class MarkCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tasks = new TaskList();
        ui = new Ui();

        tempFile = Files.createTempFile("tasks", ".txt");
        storage = new Storage(tempFile.toString());

        tasks.addTask(new Todo("read book"));
    }

    @Test
    public void execute_markTask_taskIsMarked() throws TaskBotException {
        MarkCommand cmd = new MarkCommand(0, true);
        cmd.execute(tasks, ui, storage);

        assertEquals("X", tasks.getTask(0).getStatusIcon(),
                "Task should be marked as done");
    }

    @Test
    public void execute_unmarkTask_taskIsUnmarked() throws TaskBotException {
        tasks.getTask(0).mark();

        MarkCommand cmd = new MarkCommand(0, false);
        cmd.execute(tasks, ui, storage);

        assertEquals(" ", tasks.getTask(0).getStatusIcon(),
                "Task should be unmarked (not done)");
    }
}
