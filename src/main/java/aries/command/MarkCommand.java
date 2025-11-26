package aries.command;

import aries.AriesException;
import aries.task.Task;
import aries.task.TaskList;
import aries.ui.Ui;
import aries.util.IndexHandling;

/**
 * Marks a task as done.
 */
public class MarkCommand implements Command {
    private String index;

    /**
     * Constructor for MarkCommand.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(String index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui) throws AriesException {
        int zeroBasedIndex = IndexHandling.getValidIndex(this.index, tasks.size());
        Task taskToMark = tasks.get(zeroBasedIndex);
        taskToMark.markAsDone();
        return new CommandResult(ui.showMarkedStatus(taskToMark, true), true, false);
    }
}
