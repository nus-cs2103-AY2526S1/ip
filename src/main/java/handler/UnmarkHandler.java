package handler;

import hachi.CommandCode;
import hachi.TaskList;
import hachi.Ui;

/**
 * Handles the {@link CommandCode#UNMARK} command.
 *
 * <p>This handler expects user input of the form:
 * <pre>
 *     unmark &lt;taskNumber&gt;
 * </pre>
 * Example:
 * <pre>
 *     unmark 3
 * </pre>
 *
 * <p>If the specified task number is valid, the corresponding task is
 * unmarked (set to incomplete). Otherwise, a failure message is returned.</p>
 */
public class UnmarkHandler implements Manager {
    private final String input;
    private final Ui ui;

    /**
     * Constructs an {@code UnmarkHandler}.
     *
     * @param input the raw user input string
     * @param ui    the UI utility for success/failure messages
     */
    public UnmarkHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    /**
     * Executes the unmark command by unmarking the specified task.
     *
     * @param tasks the task list to operate on
     * @return a success message if the task is unmarked successfully,
     *         or a failure message if the input is invalid or out of range
     */
    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ");

        // guard: no task number provided
        if (parts.length <= 1) {
            return Ui.failure(CommandCode.UNMARK);
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return Ui.failure(CommandCode.UNMARK); // invalid number format
        }

        // guard: invalid task index
        if (taskNumber < 1 || taskNumber > tasks.getTasks().size()) {
            return Ui.failure(CommandCode.MISSING);
        }

        // success path
        tasks.getTasks().get(taskNumber - 1).unmark();
        return ui.success(CommandCode.UNMARK);
    }


}

