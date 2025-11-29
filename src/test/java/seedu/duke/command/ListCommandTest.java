package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;
import seedu.duke.ui.FakeUi;

/**
 * Tests for {@link ListCommand}.
 */
public class ListCommandTest {

    /** Spy that records if {@code save()} is called (it should NOT be). */
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
    public void execute_nonEmptyList_showsTaskListAndDoesNotSave() throws Exception {
        final TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"), new ToDo("write code"));

        final FakeUi ui = new FakeUi();
        final SpyStorage storage = new SpyStorage();

        final Command cmd = new ListCommand();
        cmd.execute(tasks, ui, storage);

        final String shown = ui.getLastShown();
        assertNotNull(shown, "UI should show a list.");
        assertTrue(shown.contains("read book") || shown.contains("write code"),
                "List output should include at least one task.");
        assertFalse(storage.saved, "List should not persist.");
    }

    @Test
    public void execute_emptyList_showsEmptyMessageAndDoesNotSave() throws Exception {
        final TaskList tasks = new TaskList();
        final FakeUi ui = new FakeUi();
        final SpyStorage storage = new SpyStorage();

        final Command cmd = new ListCommand();
        cmd.execute(tasks, ui, storage);

        final String shown = ui.getLastShown();
        assertNotNull(shown, "UI should show an empty-list message.");
        assertTrue(shown.toLowerCase().contains("no items"),
                "Should explicitly show empty list.");
        assertFalse(storage.saved, "List should not persist.");
    }
}
