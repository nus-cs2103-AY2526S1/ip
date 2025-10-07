package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.tasks.TaskList;
import jarvis.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    private String message; // what we’ll return to the GUI

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Use the Ui instance to format the task list into a String
        // e.g., in Ui: public String formatList(TaskList tasks) { ... }
        message = ui.formatList(tasks);
    }

    @Override
    public String getString() {
        return message != null ? message : "No tasks to show.";
    }
}
