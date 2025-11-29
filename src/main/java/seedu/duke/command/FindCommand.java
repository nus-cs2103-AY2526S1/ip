package seedu.duke.command;

import java.util.List;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Finds tasks whose descriptions contain a given keyword (case-insensitive).
 */
public final class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a {@code FindCommand}.
     *
     * @param keyword Non-empty search keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * Executes the find operation.
     *
     * @param tasks   The task list.
     * @param ui      UI for output.
     * @param storage Persistent storage (unchanged).
     * @throws DukeException If the keyword is empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (keyword.isEmpty()) {
            throw new DukeException("Keyword cannot be empty. Usage: find <keyword>.");
        }

        final List<Task> matches = tasks.findByKeyword(keyword);
        if (matches.isEmpty()) {
            ui.show("No matching tasks found.");
            return;
        }

        final StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            sb.append(i + 1).append(". ").append(matches.get(i)).append('\n');
        }
        ui.show(sb.toString());
    }
}
