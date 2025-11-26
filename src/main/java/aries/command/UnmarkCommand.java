package aries.command;

import aries.AriesException;
import aries.task.Task;
import aries.task.TaskList;
import aries.ui.Ui;
import aries.util.IndexHandling;

/**
 * Unmarks a task as done.
 */
public class UnmarkCommand implements Command {
    private String index;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(String index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui) throws AriesException {
        int zeroBasedIndex = IndexHandling.getValidIndex(this.index, tasks.size());
        Task taskToUnmark = tasks.get(zeroBasedIndex);
        taskToUnmark.unmark();
        return new CommandResult(ui.showMarkedStatus(taskToUnmark, false), true, false);
    }
}
