package tsunderechan.command;

import java.util.List;

import tsunderechan.storage.Storage;
import tsunderechan.task.Task;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a command that finds tasks containing the keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Instantiates a FindCommand object.
     *
     * @param keyword Keyword to match with description of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.find(keyword);
        if (matches.isEmpty()) {
            return ui.showNoMatchFound();
        }
        return ui.showFindResults(matches);
    }
}
