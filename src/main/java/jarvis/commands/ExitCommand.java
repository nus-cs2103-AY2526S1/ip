package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.tasks.TaskList;
import jarvis.ui.Ui;

/**
 * Represents a command to exit the assistant program.
 * When executed, this command displays a goodbye message and signals the program to terminate.
 */
public class ExitCommand extends Command {
    private String message;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Build goodbye message (Ui should return a string, not print)
        // e.g., in Ui: public String formatGoodbye() { return "Bye. Hope to see you again soon!"; }
        message = ui.formatGoodbye();
        isExit = true;
    }

    @Override
    public String getString() {
        return message != null ? message : "Goodbye!";
    }
}
