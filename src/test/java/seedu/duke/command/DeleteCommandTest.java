package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;
import seedu.duke.ui.FakeUi;

/**
 * Tests for {@link DeleteCommand}.
 */
public class DeleteCommandTest {

    /** Simple spy to verify that {@code save()} is invoked. */
    static class SpyStorage extends Storage {
        private boolean saved;

        SpyStorage() {
            super("ignore.txt");
        }
        @Override public void save(TaskList tasks) {
            saved = true;
        }
    }

    @Test
    public void execute_validIndex_deletesTaskAndSaves() throws Exception {
        final TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"), new ToDo("write code"));
        final FakeUi ui = new FakeUi();
        final SpyStorage storage = new SpyStorage();

        final Command cmd = new DeleteCommand(1); // delete first task (1-based)
        cmd.execute(tasks, ui, storage);

        assertEquals(1, tasks.size(), "One task should remain.");
        assertEquals("write code", tasks.get(0).getName(), "Remaining task should be 'write code'.");
        assertTrue(storage.saved, "Delete should persist via storage.save().");

        final String shown = ui.getLastShown();
        assertNotNull(shown, "UI should show a message.");
        final String low = shown.toLowerCase();
        assertTrue(low.contains("removed") || low.contains("delete"),
                "UI output should communicate deletion.");
    }

    @Test
    public void execute_indexZero_throwsAndDoesNotSave() {
        final TaskList tasks = new TaskList();
        tasks.add(new ToDo("one"));
        final FakeUi ui = new FakeUi();
        final SpyStorage storage = new SpyStorage();

        final Command cmd = new DeleteCommand(0);
        assertThrows(DukeException.class, () -> cmd.execute(tasks, ui, storage));
        assertFalse(storage.saved, "On failure, storage.save() must not be called.");
    }

    @Test
    public void execute_indexOutOfRange_throwsAndDoesNotSave() {
        final TaskList tasks = new TaskList();
        tasks.add(new ToDo("only"));
        final FakeUi ui = new FakeUi();
        final SpyStorage storage = new SpyStorage();

        final Command cmd = new DeleteCommand(2);
        assertThrows(DukeException.class, () -> cmd.execute(tasks, ui, storage));
        assertFalse(storage.saved, "On failure, storage.save() must not be called.");
    }

    @Test
    public void execute_deleteLastItem_listShrinksAndSaves() throws Exception {
        final TaskList tasks = new TaskList();
        tasks.add(new ToDo("a"), new ToDo("b"), new ToDo("c"));
        final FakeUi ui = new FakeUi();
        final SpyStorage storage = new SpyStorage();

        final Command cmd = new DeleteCommand(3);
        cmd.execute(tasks, ui, storage);

        assertEquals(2, tasks.size(), "Size should decrease by one.");
        assertEquals("b", tasks.get(1).getName(), "Last remaining item should be 'b'.");
        assertTrue(storage.saved, "Delete should persist via storage.save().");
    }
}
