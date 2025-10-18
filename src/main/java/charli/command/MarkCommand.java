package charli.command;

import charli.exception.CharliException;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

/**
 * Command to mark tasks as played or unplayed.
 * Handles both 'played' and 'unplayed' commands for task status management.
 */
public class MarkCommand implements Command {

    private static final String USAGE_PLAYED   = "Use: played <number> (e.g., played 2)";
    private static final String USAGE_UNPLAYED = "Use: unplayed <number> (e.g., unplayed 2)";

    private String fullCommand;
    private boolean isDone;

    public MarkCommand(String fullCommand, boolean isDone) {
        assert fullCommand != null : "fullCommand cannot be null";
        this.fullCommand = fullCommand;
        this.isDone = isDone;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        assert tasks != null : "tasks must not be null";

        String[] parts = fullCommand.split("\\s+");
        if (parts.length < 2) {
            throw new CharliException("Please specify a track number. " + usage());
        }
        if (tasks.isEmpty()) {
            throw new CharliException("Your rotation is empty. Nothing to mark.");
        }

        int songIndex;
        try {
            songIndex = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new CharliException("Track number must be an integer. " + usage());
        }

        if (songIndex < 0 || songIndex >= tasks.size()) {
            throw new CharliException ("Track number does not exist. You have " + tasks.size() + " tasks.");
        }

        if (isDone) {
            tasks.get(songIndex).markAsDone();
        } else {
            tasks.get(songIndex).markAsNotDone();
        }

        String statusMsg = isDone
                ? "AMAZING! I've marked this bop as played:\n"
                : "AW! I've marked this track as unplayed:\n";

        return statusMsg + tasks.get(songIndex).toString();
    }

    public boolean isExit() {
        return false;
    }

    private String usage() {
        return isDone ? USAGE_PLAYED : USAGE_UNPLAYED;
    }
}
