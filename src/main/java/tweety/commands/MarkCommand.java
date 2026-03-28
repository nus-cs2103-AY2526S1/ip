package tweety.commands;

import tweety.exceptions.TweetyException;

import tweety.storage.Storage;

import tweety.tasks.Task;
import tweety.tasks.TaskList;

import tweety.ui.Ui;

/**
 * Command for marking tasks as completed in the Tweety application.
 * Marks the specified task as done and updates its status in the task list.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a new MarkCommand with the specified task number.
     *
     * @param taskNumber The 1-based index of the task to mark as completed.
     */
    public MarkCommand(int taskNumber) {
        super(CommandType.MARK);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        Task markedTask = tasks.markTask(taskNumber);

        storage.saveTasks(tasks);

        ui.printMarkedTask(markedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
