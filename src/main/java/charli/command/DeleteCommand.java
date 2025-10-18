package charli.command;

import charli.exception.CharliException;
import charli.task.Task;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

/**
 * Command to delete tasks from the task list.
 * Handles the 'delete' command for task removal.
 */
public class DeleteCommand implements Command {
    private static final String USAGE = "Use 'delete [number]' format!";
    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {

        String[] parts = fullCommand.trim().split("\\s+");
        if (parts.length < 2) {
            throw new CharliException("Please specify a track number. " + USAGE);
        }

        final int oneBased;
        try {
            oneBased = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new CharliException("Track number must be an integer. " + USAGE);
        }

        if (oneBased <= 0) {
            throw new CharliException("Track numbers start at 1. " + USAGE);
        }

        int index = oneBased - 1;
        if (index >= tasks.size()) {
            throw new CharliException("Track #" + oneBased + " doesn't exist. You have "
                    + tasks.size() + " tracks.");
        }

        Task removed = tasks.remove(index);

        return "Got it! I've removed this track:\n"
                + removed
                + "\nNow you have " + tasks.size() + " tracks in your rotation!";
    }

    public boolean isExit() {
        return false;
    }
}

