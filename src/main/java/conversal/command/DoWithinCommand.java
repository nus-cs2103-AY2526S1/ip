package conversal.command;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.DoWithinPeriodTask;
import conversal.task.Task;
import conversal.task.TaskList;
import conversal.ui.Ui;

/**
 * Represents a command to create and add a DoWithinPeriod task
 * to the task list.
 *
 * The DeadlineCommand is created when the user input starts with "Do-within ".
 *
 * It extracts the description and timeframe from the input,
 * creates a new DoWithinPeriod task,
 * adds it to the TaskList,
 * saves the updated list to Storage,
 * and updates the user through Ui.
 */
public class DoWithinCommand implements Command {
    private String input;

    /**
     * Creates a DoWithinPeriod Command using the user input.
     *
     * @param input the user input containing the description and time frame of the DoWithinPeriod task
     */
    public DoWithinCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to create and add a new DoWithinPeriod task.
     *
     * If the description or period are missing, a ConversalException is thrown.
     * Else, the new task is added to the TaskList,
     * the list is saved via Storage,
     * and a confirmation message is displayed through Ui.
     *
     * @param tasks   the task list to add the new deadline into
     * @param storage the storage of tasks
     * @param ui      the UI to show the confirmation message
     * @throws ConversalException if the deadline description or date is missing or invalid
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 10) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDoWithin());
        }
        if (!input.contains(" /within ")) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDoWithin());
        }

        String[] info = input.substring(10).split(" /within ");
        if (info.length < 2 || info[0].trim().isEmpty() || info[1].trim().isEmpty()) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDoWithin());
        }

        Task t = new DoWithinPeriodTask(info[0], info[1]);
        tasks.addTask(t);
        storage.save(tasks.getList());
        ui.addMessage(t, tasks.size());
    }

    /**
     * Returns whether this command exits the application.
     *
     * @return false because DoWithinPeriod never exits the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
