package handler;

import hachi.CommandCode;
import hachi.TaskList;
import hachi.Ui;

/**
 * Handles the {@link CommandCode#DELETE} command.
 *
 * <p>This handler expects input of the form:
 * <pre>
 *     delete &lt;taskNumber&gt;
 * </pre>
 * Example:
 * <pre>
 *     delete 2
 * </pre>
 *
 * <p>If the provided task number is valid, the task is removed from the
 * {@link TaskList} and a success message is returned. Otherwise, a
 * failure message is returned.</p>
 */
public class DeleteHandler implements Manager {

    private final String input;
    private final Ui ui;

    /**
     * Constructs a {@code DeleteHandler}.
     *
     * @param input the raw user input string
     * @param ui    the UI utility for success/failure messages
     */
    public DeleteHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    /**
     * Attempts to delete a task from the given {@link TaskList}.
     *
     * @param tasks the task list from which to delete
     * @return a success message if the deletion succeeds,
     *         or a failure message otherwise
     */
    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.trim().split("\\s+");

        // guard: no task number provided
        if (parts.length <= 1) {
            return Ui.failure(CommandCode.DELETE);
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return Ui.failure(CommandCode.DELETE); // invalid number format
        }

        // guard: invalid task index
        if (taskNumber < 1 || taskNumber > tasks.getTasks().size()) {
            return Ui.failure(CommandCode.MISSING);
        }

        // success path
        tasks.getTasks().remove(taskNumber - 1);
        return ui.success(CommandCode.DELETE);
    }

}
