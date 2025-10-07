package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;
import jarvis.ui.DarrenAssistantException;
import jarvis.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task in the task list as done.
 */
public class MarkCommand extends Command {
    private final int idx;
    private String message; // what the GUI will show

    /**
     * @param idx Index of the task to mark as done (0-based).
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DarrenAssistantException, IOException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new DarrenAssistantException("No such task: " + (idx + 1));
        }

        Task t = tasks.get(idx);
        t.markAsDone();

        // Build GUI message (Ui returns a String)
        // e.g., in Ui: public String formatMarked(Task t) { ... }
        message = ui.formatMarked(t);

        // Persist
        storage.save(tasks.asList());
    }

    @Override
    public String getString() {
        return message != null ? message : "Marked task " + (idx + 1) + " as done.";
    }
}
