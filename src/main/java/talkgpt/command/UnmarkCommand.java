package talkgpt.command;

import talkgpt.storage.Storage;
import talkgpt.task.Task;
import talkgpt.tasklist.TaskList;
import talkgpt.ui.Ui;

/**
 * Represents a command to unmark a task as not done in the task list in the TalkGPT application.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand to unmark a task at the specified index.
     *
     * @param index Index of the task in the list to be unmarked (1-based, will be converted to 0-based).
     */
    public UnmarkCommand(String index) {
        this.index = Integer.parseInt(index) - 1;
    }

    /**
     * Executes the unmark task function for the TaskList, UI, and Storage.
     *
     * @param list TaskList for the task to be unmarked.
     * @param ui UI for displaying the unmark task message.
     * @param storage Storage for updating the persistent task data.
     * @return The confirmation message after unmarking the task.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String oldTask = list.get(index).serialize();
        Task newTask = list.unmarkTask(index);
        storage.update(oldTask, newTask);

        return ui.unmarkTask(newTask, list.size());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UnmarkCommand)) {
            return false;
        }
        UnmarkCommand that = (UnmarkCommand) other;
        return this.index == that.index;
    }
}
