package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;
import jarvis.ui.DarrenAssistantException;
import jarvis.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to unmark a task in the task list as not done.
 */
public class UnmarkCommand extends Command {
    private final int idx;
    private String message; // what the GUI will show

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DarrenAssistantException, IOException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new DarrenAssistantException("No such task: " + (idx + 1));
        }

        Task t = tasks.get(idx);
        t.markAsNotDone();

        // Build confirmation message (Ui should return a string)
        // e.g., in Ui: public String formatUnmarked(Task t) { ... }
        message = ui.formatUnmarked(t);

        // Save updated tasks
        storage.save(tasks.asList());
    }

    @Override
    public String getString() {
        return message != null ? message : "Unmarked task " + (idx + 1) + " as not done.";
    }
}
