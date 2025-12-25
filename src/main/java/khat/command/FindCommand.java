package khat.command;

import khat.exception.KhatException;
import khat.storage.Storage;
import khat.task.TaskList;
import khat.ui.Ui;

/** Represents a command to find tasks with a matching keyword. */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a FindCommand with given keyword.
     *
     * @param keyword Keyword to filter tasks by.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     *
     * Shows tasks in task list with matching keyword.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KhatException {
        TaskList filteredTasks = tasks.getTasksWithKeyword(this.keyword);
        ui.showTasksWithKeyword(filteredTasks, keyword);
    }
}
