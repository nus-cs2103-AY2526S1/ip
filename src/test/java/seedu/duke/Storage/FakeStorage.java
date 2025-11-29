package seedu.duke.storage;

import seedu.duke.task.TaskList;

/**
 * Test stub of {@link Storage} that avoids touching disk.
 * All saves are no-ops so unit tests can run fast and side-effect free.
 */
public class FakeStorage extends Storage {

    /**
     * Creates a fake storage that points to a throwaway path.
     */
    public FakeStorage() {
        super("ignore.txt");
    }

    /**
     * No-op in tests: do not write anything to disk.
     *
     * @param tasks the current task list (ignored)
     */
    @Override
    public void save(TaskList tasks) {
        // no-op, just to avoid touching disk in tests
    }
}
