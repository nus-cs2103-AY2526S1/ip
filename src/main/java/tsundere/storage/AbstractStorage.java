package tsundere.storage;

import java.io.IOException;

import tsundere.TsundereException;
import tsundere.task.TaskList;

/**
 * An abstract class the handles the storage of task lists.
 */
public abstract class AbstractStorage {
    public abstract void storeAll(TaskList tasks) throws TsundereException;
    public abstract TaskList retrieveAll() throws TsundereException;
    public abstract void clear();
}
