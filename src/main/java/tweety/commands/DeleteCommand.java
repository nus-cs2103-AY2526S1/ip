package tweety.commands;

import tweety.exceptions.TweetyException;

import tweety.storage.Storage;

import tweety.tasks.Task;
import tweety.tasks.TaskList;

import tweety.ui.Ui;

/**
 * Command for deleting tasks in the Tweety application.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a new DeleteCommand with the specified task number.
     *
     * @param taskNumber The 1-based index of the task to delete.
     */
    public DeleteCommand(int taskNumber) {
        super(CommandType.DELETE);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        Task deletedTask = tasks.deleteTask(taskNumber);

        storage.saveTasks(tasks);

        ui.printDeletedTask(deletedTask, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
