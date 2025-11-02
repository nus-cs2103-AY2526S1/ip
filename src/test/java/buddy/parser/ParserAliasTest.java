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
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/*
  Re-use notice:
  As part of AI assisted increment, ChatGPT suggested a unit test for friendlier syntax
  No code was used; only the idea.
 */

public class ParserAliasTest {

    @TempDir
    Path tempDir;

    @Test void todoAliasWorks() throws BuddyException {
        Ui ui = new Ui();
        Storage storage = new Storage(tempDir.resolve("tasks.txt").toString());
        TaskList tasks = new TaskList(storage.load());

        String cmd = "t buy apples";
        boolean shouldExit = Parser.handle(cmd, tasks, ui, storage);

        assertFalse(shouldExit, "Adding a todo should not exit the app");
        assertEquals(1, tasks.getSize(), "Exactly one task should be added");

        Task t = tasks.toList().get(0);
        assertInstanceOf(Todo.class, t, "Task should be a Todo");
        assertEquals("buy apples", t.getDescription());
        assertFalse(t.isDone(), "New todo should be not done by default");

    }
}
