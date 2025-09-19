package eureka.command;

import eureka.Storage;
import eureka.task.TaskList;
import eureka.ui.Ui;

/**
 * Represents a command that deletes a task from the {@link TaskList}.
 * <p>
 * The task is identified by its index in the list. If the index is valid,
 * the task is removed, the storage is updated, and the user is notified
 * through the {@link Ui}.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index the zero-based index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the given index
     * from the {@link TaskList}, updating {@link Storage}, and showing
     * feedback in the {@link Ui}.
     *
     * @param tasks   the task list from which the task will be deleted
     * @param ui      the user interface used to show messages
     * @param storage the storage used to persist changes after deletion
     * @return a confirmation message showing the deleted task and updated count,
     *         or an error message if the index is invalid
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String deletedInfo = "";

        if (index >= 0 && index < tasks.getCount()) {
            deletedInfo = tasks.getTask(index).toString();
            tasks.deleteTask(index);
        } else {
            return "⚠️ Oops! Invalid task index.";
        }

        storage.updateFile();

        ui.showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedInfo);
        System.out.println("Now you have " + tasks.getCount() + " tasks in the list.");
        ui.showLine();

        String isPlural = tasks.getCount() > 1 ? "s" : "";
        return "Noted. I've removed this task:\n"
                + deletedInfo + "\n"
                + "Now you have " + tasks.getCount() + " task"
                + isPlural + " in the list.";
    }

    /**
     * Indicates whether this command will terminate the program.
     *
     * @return false, since deleting a task does not exit the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
