package ming.command;

import java.util.List;

import ming.model.Task;
import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;
    private final String type;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword
     * @param type
     */
    public FindCommand(String keyword, String type) {
        this.keyword = keyword;
        this.type = type;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> foundTasks;
        if (type.equals("name")) {
            foundTasks = tasks.findByName(keyword);
        } else {
            foundTasks = tasks.findByTag(keyword);
        }

        return ui.showFind(foundTasks);
    }
}
