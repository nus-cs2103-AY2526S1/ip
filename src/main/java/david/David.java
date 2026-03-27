package david;

import david.command.CommandHistory;
import david.storage.Storage;
import david.task.TaskList;

/**
 * Entrypoint class for David chatbot.
 */
public class David {
    private Storage storage;
    private TaskList tasks;
    private CommandHistory history;

    /**
     * Initialises the David chatbot.
     *
     * @param filePath File path of storage file/
     */
    public David(String filePath) {
        assert filePath != null : "filePath should never be null";
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
        history = new CommandHistory();
    }

    public Storage getStorage() {
        return this.storage;
    }

    public TaskList getTaskList() {
        return this.tasks;
    }

    public CommandHistory getHistory() {
        return this.history;
    }
}
