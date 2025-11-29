package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;
import seedu.duke.ui.FakeUi;

/**
 * Tests for {@link MarkCommand}.
 */
public class MarkCommandTest {

    /** Spy that records if {@code save()} is called (it SHOULD be on success). */
    static class SpyStorage extends Storage {
        private boolean saved;

        SpyStorage() {
            super("ignore.txt");
        }

        @Override
        public void save(TaskList tasks) {
            saved = true;
        }
    }

    @Test
    public void execute_validIndex_marksDoneAndSaves() throws Exception {
        final TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"));

        final FakeUi ui = new FakeUi();
        final SpyStorage storage = new SpyStorage();

        final Command cmd = new MarkCommand(1);
        cmd.execute(tasks, ui, storage);

        assertTrue(tasks.get(0).isDone(), "Task should be marked done.");
        assertTrue(ui.getLastShown().toLowerCase().contains("marked as done"),
                "UI should mention that the task was marked.");
        assertTrue(storage.saved, "Mark should persist via storage.save().");
    }

    @Test
    public void execute_indexZero_throwsAndDoesNotSave() {
        final TaskList tasks = new TaskList();
        tasks.add(new ToDo("one"));

        final FakeUi ui = new FakeUi();
        final SpyStorage storage = new SpyStorage();

        final Command cmd = new MarkCommand(0);
        assertThrows(DukeException.class, () -> cmd.execute(tasks, ui, storage));
        assertFalse(storage.saved, "On failure, storage.save() must not be called.");
    }

    @Test
    public void execute_indexTooLarge_throwsAndDoesNotSave() {
        final TaskList tasks = new TaskList();
        tasks.add(new ToDo("one"));

        final FakeUi ui = new FakeUi();
        final SpyStorage storage = new SpyStorage();

        final Command cmd = new MarkCommand(2);
        assertThrows(DukeException.class, () -> cmd.execute(tasks, ui, storage));
        assertFalse(storage.saved, "On failure, storage.save() must not be called.");
    }

    @Test
    public void execute_markTwice_idempotentAndSavesBothTimes() throws Exception {
        final TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"));

        final FakeUi ui = new FakeUi();
        final SpyStorage storage = new SpyStorage();

        new MarkCommand(1).execute(tasks, ui, storage);
        final boolean firstSaved = storage.saved;

        // Reset spy flag and mark again.
        storage.saved = false;
        new MarkCommand(1).execute(tasks, ui, storage);

        assertTrue(tasks.get(0).isDone(), "Task should remain done.");
        assertTrue(firstSaved && storage.saved, "Both executions should call save().");
    }
}
