package handler;

import hachi.CommandCode;
import hachi.Task;
import hachi.TaskList;
import hachi.Ui;

/**
 * Handles the {@link CommandCode#DISPLAY} command.
 *
 * <p>This handler expects user input of the form:
 * <pre>
 *     display &lt;taskNumber&gt;
 * </pre>
 * Example:
 * <pre>
 *     display 2
 * </pre>
 *
 * <p>If the specified task exists and has an attached note, the note is displayed.
 * Otherwise, an appropriate failure message is returned.</p>
 */
public class DisplayHandler implements Manager {
    private final String input;
    private final Ui ui;

    /**
     * Constructs a {@code DisplayHandler} with the raw user input and a UI instance.
     *
     * @param input the user input string (e.g., {@code "display 2"})
     * @param ui    the UI utility used for success or failure messages
     */
    public DisplayHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    /**
     * Executes the display command by attempting to retrieve and show the note
     * of the specified task from the given {@link TaskList}.
     *
     * @param tasks the task list to operate on
     * @return the task’s note if it exists,
     *         or a failure message if the task number is invalid or no note is attached
     */
    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ");

        // guard: no task number provided
        if (parts.length <= 1) {
            return Ui.failure(CommandCode.DISPLAY);
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return Ui.failure(CommandCode.DISPLAY); // invalid number format
        }

        // guard: invalid task index
        if (taskNumber < 1 || taskNumber > tasks.getTasks().size()) {
            return Ui.failure(CommandCode.MISSING);
        }

        // success path
        Task target = tasks.getTaskAt(taskNumber - 1);
        return target.getNote() != null
                ? target.printNote()
                : Ui.failure(CommandCode.DISPLAY);
    }


}
