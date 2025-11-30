package briar.command;

import briar.exception.BriarException;
import briar.task.Task;
import briar.task.TaskList;
import briar.ui.Storage;
import briar.ui.Ui;

/**
 * Represents a command that adds a Task into a TaskList.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates a command to add the stored task to a task list.
     * @param taskToAdd Task to be added to the task list.
     */
    public AddCommand(Task taskToAdd) {
        super();
        assert taskToAdd != null : "taskToAdd should not be null";
        task = taskToAdd;
    }

    /**
     * Executes the command to add the stored task to the task list.
     * @param tasks Task list of the chatbot.
     * @param ui Ui used by the chatbot to display.
     * @param storage Storage used to save and load the task list.
     * @return Response from Briar after completing the command
     * @throws BriarException If writing to file fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        tasks.add(task);
        String response = ui.showAddedTask(task) + System.lineSeparator() + ui.showTaskNumber(tasks);
        storage.write(tasks.taskToTextString());
        return response;
    }

    /**
     * Returns a String containing the format of the command and what it does.
     */
    public static String getCommandInformation() {
        return "todo <description> | Add a todo task to the list"
                + System.lineSeparator()
                + "deadline <description> /by <YYYY-MM-DD> | Add a deadline task to the list"
                + System.lineSeparator()
                + "event <description> /from <time> /to <time> | Add a event task to the list";
    }

    /**
     * Returns the string representation of the command.
     */
    @Override
    public String toString() {
        return "Add: " + task;
    }
}
