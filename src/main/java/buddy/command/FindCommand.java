package buddy.command;

import buddy.exception.BuddyException;
import buddy.model.TaskList;
import buddy.storage.Storage;
import buddy.ui.Ui;

public final class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String keyword) throws BuddyException {
        if (keyword == null || keyword.isBlank()) {
            throw new BuddyException("Usage: find <keyword>");
        }
        this.keyword = keyword.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(tasks.findAsString(keyword));
    }
}
