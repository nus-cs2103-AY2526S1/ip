package eureka.command;

import eureka.Storage;
import eureka.task.TaskList;
import eureka.ui.Ui;

/**
 * Represents a command that marks a task as not completed in the {@link TaskList}.
 * <p>
 * The task is identified by its index. If the index is invalid, an error message
 * is returned instead. When successful, the updated task is shown to the user
 * and persisted to {@link Storage}.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index the zero-based index of the task to mark as not done
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command by marking the specified task in the {@link TaskList}
     * as not completed, updating {@link Storage}, and displaying feedback via {@link Ui}.
     *
     * @param tasks   the task list containing the task to be unmarked
     * @param ui      the user interface used to show messages
     * @param storage the storage used to persist the updated task list
     * @return a confirmation message showing the unmarked task,
     *         or an error message if the index is invalid
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        if (index < 0 || index >= tasks.getCount()) {
            return "⚠️ Oops! Invalid task index.";
        }

        if (index >= 0 && index < tasks.getCount()) {
            tasks.unmarkTask(index);
        }

        storage.updateFile();

        ui.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.getTask(index).toString());
        ui.showLine();

        return "OK, I've marked this task as not done yet:\n"
                + tasks.getTask(index).toString();
    }

    /**
     * Indicates whether this command will terminate the program.
     *
     * @return false, since unmarking a task does not exit the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
