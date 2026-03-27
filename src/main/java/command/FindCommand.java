package command;

import java.util.List;

import sunday.Storage;
import sunday.TaskList;
import sunday.Ui;
import task.Task;

/**
 * Finds tasks whose description contains a given keyword (case-insensitive).
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword == null ? "" : keyword.trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        assert ui != null : "UI cannot be null";
        assert taskList != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        assert keyword != null : "Input cannot be null";

        if (keyword.isBlank()) {
            ui.showError("Please provide a keyword, e.g., find book");
            return;
        }

        List<Task> result = taskList.findByKeyword(this.keyword);
        ui.showFindResult(result);
    }
}
