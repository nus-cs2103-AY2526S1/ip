package talkgpt.command;

import talkgpt.storage.Storage;
import talkgpt.task.Task;
import talkgpt.tasklist.TaskList;
import talkgpt.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list in the TalkGPT application.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand to mark a task at the specified index.
     *
     * @param index Index of the task in the list to be marked (1-based, will be converted to 0-based).
     */
    public MarkCommand(String index) {
        this.index = Integer.parseInt(index) - 1;
    }

    /**
     * Executes the mark task function for the TaskList, UI, and Storage.
     *
     * @param list TaskList for the task to be marked.
     * @param ui UI for the mark task print statement.
     * @param storage Storage for the marked task to be updated.
     * @return The confirmation message after marking the task.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String oldTask = list.get(index).serialize();
        Task newTask = list.markTask(index);
        storage.update(oldTask, newTask);

        return ui.markTask(newTask, list.size());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MarkCommand)) {
            return false;
        }

        MarkCommand o = (MarkCommand) other;
        return this.index == o.index;
    }
}
