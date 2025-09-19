package eureka.command;

import eureka.Storage;
import eureka.task.TaskList;
import eureka.ui.Ui;

/**
 * Represents a command that marks a task as completed in the {@link TaskList}.
 * <p>
 * The task is identified by its index. If the index is invalid, an error message
 * is returned instead. When successful, the updated task is shown to the user
 * and persisted to {@link Storage}.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index the zero-based index of the task to mark as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Returns the index of the task to be marked.
     * <p>
     * This method is primarily intended for testing.
     *
     * @return the zero-based index of the task
     */
    public int getIndex() {
        return index;
    }

    /**
     * Executes the mark command by marking the specified task in the {@link TaskList}
     * as completed, updating {@link Storage}, and displaying feedback via {@link Ui}.
     *
     * @param tasks   the task list containing the task to be marked
     * @param ui      the user interface used to show messages
     * @param storage the storage used to persist the updated task list
     * @return a confirmation message showing the marked task,
     *         or an error message if the index is invalid
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (index < 0 || index >= tasks.getCount()) {
            return "⚠️ Oops! Invalid task index.";
        }

        if (index >= 0 && index < tasks.getCount()) {
            tasks.markTask(index);
        }

        storage.updateFile();

        ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        String taskDetails = tasks.getTask(index).toString();
        System.out.println(taskDetails);
        ui.showLine();

        return "Nice! I've marked this task as done:\n"
                + taskDetails;
    }

    /**
     * Indicates whether this command will terminate the program.
     *
     * @return false, since marking a task does not exit the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
