package buddy.parser;

import buddy.exception.BuddyException;
import buddy.model.Task;
import buddy.model.TaskList;
import buddy.model.Todo;
import buddy.storage.Storage;
import buddy.ui.Ui;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class TodoTest {

        @TempDir
        Path tempDir;

        @Test
        void testAddTodoWithValidDescription() throws BuddyException {

            Ui ui = new Ui();
            Storage storage = new Storage(tempDir.resolve("tasks.txt").toString());
            TaskList tasks = new TaskList(storage.load());

            String cmd = "todo buy apples";
            boolean shouldExit = Parser.handle(cmd, tasks, ui, storage);

            assertFalse(shouldExit, "Adding a todo should not exit the app");
            assertEquals(1, tasks.getSize(), "Exactly one task should be added");

            Task t = tasks.toList().get(0);
            assertTrue(t instanceof Todo, "Task should be a Todo");
            assertEquals("buy apples", t.getDescription());
            assertFalse(t.isDone(), "New todo should be not done by default");

        }
    }

