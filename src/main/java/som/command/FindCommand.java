package som.command;

import som.SomException;
import som.TaskList;
import som.Ui;

/**
 * Represents a command to find tasks of a specified keyword.
 * <p>When executed, this command searches the task list case-insensitively
 * and displays matching tasks.</p>
 *
 * @author Darien Tan
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the given date.
     *
     * @param keyword the keyword to search for; must not be null.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws SomException {
        return ui.showFindResults(tasks.findTasks(keyword), keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
