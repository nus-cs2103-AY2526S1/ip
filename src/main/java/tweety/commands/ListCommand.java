package tweety.commands;

import tweety.exceptions.TweetyException;

import tweety.storage.Storage;

import tweety.tasks.TaskList;

import tweety.ui.Ui;

/**
 * Command for displaying all tasks in the Tweety application.
 * Shows the complete list of tasks with their current status and details.
 */
public class ListCommand extends Command {

    /**
     * Constructs a new ListCommand.
     * Initialises the command with LIST type to display all tasks.
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        if (tasks.getAllTasks().isEmpty()) {
            ui.printNoTaskLeft();
        } else {
            ui.printTaskList(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
