package chatbot.command;

import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;

/**
 * Represents a command to mark or unmark a task as done in the task list.
 * <p>
 * This command updates the status of a task (completed or not completed)
 * and provides feedback to the user via the UI.
 */
public class MarkCommand extends Command {
    private int index;
    private boolean isMark;

    /**
     * Constructs a MarkCommand.
     *
     * @param index the 0-based index of the task to mark or unmark
     * @param mark  true to mark the task as done, false to unmark it
     */
    public MarkCommand(int index, boolean mark) {
        this.index = index;
        this.isMark = mark;
    }

    /**
     * Executes the command by marking or unmarking the specified task.
     * Updates the user interface with the result.
     *
     * @param tasks   the TaskList containing the tasks
     * @param ui      the Ui to show messages to the user
     * @param storage the Storage to save the tasks (not used here)
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        try {
            Task task = tasks.getTask(index);
            assert task != null : "Task cannot be null";
            if (isMark) {
                task.markDone();
            } else {
                task.unmarkDone();
            }
            String status = isMark ? "completed" : "not completed";
            return "Okay, I've marked this task as " + status + ":" + "\n" + task.toString()
                    + "\nBEEP B00P";
        } catch (IndexOutOfBoundsException e) {
            return "Index out of bounds BEEP B00P";
        }
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false because MarkCommand does not terminate the chatbot
     */
    @Override
    public boolean isExit() { return false; }
}
