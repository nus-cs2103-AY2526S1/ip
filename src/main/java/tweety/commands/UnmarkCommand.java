package tweety.commands;

import tweety.exceptions.TweetyException;

import tweety.storage.Storage;

import tweety.tasks.Task;
import tweety.tasks.TaskList;

import tweety.ui.Ui;

/**
 * Command for unmarking tasks as not completed in the Tweety application.
 * Marks the specified task as not done and updates its status in the task list.
 */
public class UnmarkCommand extends Command{
    private int taskNumber;

    /**
     * Constructs a new UnmarkCommand with the specified task number.
     *
     * @param taskNumber The 1-based index of the task to mark as not completed.
     */
    public UnmarkCommand(int taskNumber) {
        super(CommandType.UNMARK);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        Task unmarkedTask = tasks.unmarkTask(taskNumber);

        storage.saveTasks(tasks);

        ui.printUnmarkedTask(unmarkedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
