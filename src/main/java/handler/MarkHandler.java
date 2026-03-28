package handler;

import hachi.CommandCode;
import hachi.TaskList;
import hachi.Ui;

/**
 * Handles the {@link CommandCode#MARK} command.
 *
 * <p>This handler expects user input of the form:
 * <pre>
 *     mark &lt;taskNumber&gt;
 * </pre>
 * Example:
 * <pre>
 *     mark 2
 * </pre>
 *
 * <p>If the specified task number is valid, the corresponding task is marked
 * as completed. Otherwise, a failure message is returned.</p>
 */
public class MarkHandler implements Manager {
    private final String input;
    private final Ui ui;

    /**
     * Constructs a {@code MarkHandler}.
     *
     * @param input the raw user input string
     * @param ui    the UI utility for success/failure messages
     */
    public MarkHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    /**
     * Executes the mark command by marking the specified task as completed.
     *
     * @param tasks the task list to operate on
     * @return a success message if the task is marked successfully,
     *         or a failure message if the input is invalid or out of range
     */
    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ");

        // guard: no task number provided
        if (parts.length <= 1) {
            return Ui.failure(CommandCode.MARK);
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return Ui.failure(CommandCode.MARK); // invalid number format
        }

        // guard: invalid task index
        if (taskNumber < 1 || taskNumber > tasks.getTasks().size()) {
            return Ui.failure(CommandCode.MISSING);
        }

        // success path
        tasks.getTasks().get(taskNumber - 1).mark();
        return ui.success(CommandCode.MARK);
    }


}

