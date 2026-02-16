package talkgpt.command;

import talkgpt.storage.Storage;
import talkgpt.task.Task;
import talkgpt.tasklist.TaskList;
import talkgpt.ui.Ui;

/**
 * Represents a command to add a task to the task list in the TalkGPT application.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand to add the specified task.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add task function for the TaskList, UI, and Storage.
     *
     * @param list TaskList for the task to be added into.
     * @param ui UI for the add task print statement.
     * @param storage Storage for the task to be saved in.
     * @return The confirmation message after adding the task.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        list.addTask(task);
        storage.save(task);

        return ui.addTask(task, list.size());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AddCommand that = (AddCommand) obj;
        return task.equals(that.task);
    }
}
