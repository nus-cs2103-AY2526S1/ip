package briar.command;

import briar.exception.BriarException;
import briar.task.TaskList;
import briar.ui.Storage;
import briar.ui.Ui;

/**
 * Represents a command that marks a Task in a TaskList as done.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Creates a command to marks a Task in a TaskList as done.
     * @param taskNumber Index of the task to be marked as done.
     */
    public MarkCommand(int taskNumber) {
        super();
        assert taskNumber >= 0 : "taskNumber to mark should be >= 0";
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark task with the stored task number as done.
     * @param tasks Task list of the chatbot.
     * @param ui Ui used by the chatbot to display.
     * @param storage Storage used to save and load the task list.
     * @return Response from Briar after completing the command
     * @throws BriarException If stored task number is not in task list or writing to file fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        String taskString = tasks.mark(taskNumber);
        String response = ui.showMarkedTask(taskString);
        storage.write(tasks.taskToTextString());
        return response;
    }

    /**
     * Returns a String containing the format of the command and what it does.
     */
    public static String getCommandInformation() {
        return "mark <index> | Mark the task at the specified index";
    }

    /**
     * Returns the string representation of the command.
     */
    @Override
    public String toString() {
        return "Mark task " + taskNumber;
    }
}
