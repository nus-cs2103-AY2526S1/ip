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

public class DeleteCommandTest {
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
        tasks.addTask(new Todo("write report"));
        tasks.addTask(new Todo("do laundry"));
    }

    @Test
    public void execute_deleteFirstTask_taskRemovedAndSizeUpdated() throws TaskBotException {
        DeleteCommand cmd = new DeleteCommand(0);
        String result = cmd.execute(tasks, ui, storage);

        assertEquals(2, tasks.size(), "Task list size should decrease by 1");
        assertEquals("[T][ ] write report", tasks.getTasks().get(0).toString(),
                "First task should now be the original second task");
        // Check the message includes removed task and new size
        // Note: Ui.printDeletedTask concatenates lines without extra spaces/newlines between parts
        // so we check for substrings.
        org.junit.jupiter.api.Assertions.assertTrue(
                result.contains("Noted. I've removed this task: "),
                "Should include removal notice");
        org.junit.jupiter.api.Assertions.assertTrue(
                result.contains("Now you have 2 tasks in the list."),
                "Should include updated task count");
    }

    @Test
    public void execute_deleteMiddleTask_taskRemoved() throws TaskBotException {
        DeleteCommand cmd = new DeleteCommand(1);
        cmd.execute(tasks, ui, storage);

        assertEquals(2, tasks.size(), "Task list size should decrease by 1");
        assertEquals("[T][ ] read book", tasks.getTasks().get(0).toString());
        assertEquals("[T][ ] do laundry", tasks.getTasks().get(1).toString());
    }

    @Test
    public void execute_deleteInvalidIndex_throwsTaskBotException() {
        DeleteCommand cmd = new DeleteCommand(5); // Out of bounds

        assertThrows(TaskBotException.class, () -> {
            cmd.execute(tasks, ui, storage);
        }, "Should throw TaskBotException for invalid index");
    }

    @Test
    public void execute_deleteNegativeIndex_throwsTaskBotException() {
        DeleteCommand cmd = new DeleteCommand(-1); // Negative index

        assertThrows(TaskBotException.class, () -> {
            cmd.execute(tasks, ui, storage);
        }, "Should throw TaskBotException for negative index");
    }
}
