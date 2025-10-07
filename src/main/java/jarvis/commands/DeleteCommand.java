package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;
import jarvis.ui.DarrenAssistantException;
import jarvis.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 * A delete command removes the task at the specified index from the {@link TaskList}.
 */
public class DeleteCommand extends Command {
    private final int idx;
    private String message; // what Duke will show in the GUI

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DarrenAssistantException, IOException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new DarrenAssistantException("No such task: " + (idx + 1));
        }

        Task t = tasks.remove(idx);

        // Build confirmation message using Ui (instance method that RETURNS a String)
        // e.g., in Ui: public String formatDeleted(Task t, int total) { ... }
        message = ui.formatDeleted(t, tasks.size());

        // Save updated list
        storage.save(tasks.asList());
    }

    @Override
    public String getString() {
        return message != null ? message : "Deleted task " + (idx + 1);
    }
}
