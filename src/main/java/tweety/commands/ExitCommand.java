package tweety.commands;

import tweety.exceptions.TweetyException;

import tweety.storage.Storage;

import tweety.tasks.TaskList;

import tweety.ui.Ui;

/**
 * Command for exiting the Tweety application.
 * Terminates the application and displays a farewell message to the user.
 */
public class ExitCommand extends Command{

    /**
     * Constructs a new ExitCommand.
     * Initialises the command with BYE type to signal application termination.
     */
    public ExitCommand() {
        super(CommandType.BYE);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        storage.saveTasks(tasks);

        ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
