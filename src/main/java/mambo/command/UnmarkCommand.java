package mambo.command;

import mambo.MamboException;
import mambo.TaskListFileManager;
import mambo.Ui;
import mambo.task.TaskList;

/**
 * Represents a single "unmark" command that has been passed into the chatbot.
 *
 * @author kentalim2
 */
public class UnmarkCommand extends Command {

    public UnmarkCommand(String argument) {
        super(argument);
    }

    /**
     * Executes unmark command by handling the unmarking of tasks on the list by parsing out
     * index of list the user wants to unmark and passing it into unmarkTask().
     * Returns confirmation/failure message sent by chatbot when command is done executing.
     * Throws an exception when a non integer is passed through or the number
     * is out of bounds of the list.
     *
     * @param tasks Task List that is being tracked by chatbot
     * @param file Saved local file containing tasks
     * @return Chatbot message
     * @throws MamboException Throws exception when non integer is passed through or item is out of bounds
     */
    @Override
    public String execute(Ui ui, TaskList tasks, TaskListFileManager file) throws MamboException {
        try {
            int index = Integer.parseInt(this.getArgument());

            // prevent a call to an out-of-bounds index
            if (index < 1 || index > tasks.listSize()) {
                throw new MamboException("your list doesnt have a task at that number dummy!");
            }
            assert(index >= 1 && index <= tasks.listSize());
            return tasks.unmarkTask(index);
        } catch (NumberFormatException e) {
            // throw error when an exception is caught due to the argument not being an integer
            throw new MamboException("hey!! this is not the right way to use unmark. "
                    + "make sure you follow the format \"unmark *integer*\"!");
        }
    }
}
