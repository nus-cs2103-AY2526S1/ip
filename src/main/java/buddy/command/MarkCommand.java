package buddy.command;

import buddy.exception.BuddyException;
import buddy.model.Task;
import buddy.model.TaskList;
import buddy.storage.Storage;
import buddy.ui.Ui;

public final class MarkCommand implements Command {
    private final int index; // 0-based

    public MarkCommand(String token, int size) throws BuddyException {
        this.index = parseIndex(token, size);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        Task t = tasks.get(index);
        t.markAsDone();
        ui.showMessage("Nice! I've marked this task as done:\n  " + t);
    }

    private static int parseIndex(String token, int size) throws BuddyException {
        if (token == null || token.isBlank()) {
            throw new BuddyException("Usage: mark <taskNumber>");
        }
        int idx;
        try {
            idx = Integer.parseInt(token.trim());
        } catch (NumberFormatException e) {
            throw new BuddyException("Please provide a valid positive task number.");
        }
        if (idx < 1 || idx > size) {
            throw new BuddyException("No such task number.");
        }
        return idx;
    }
}
