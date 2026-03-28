package handler;

import hachi.CommandCode;
import hachi.DateFormatter;
import hachi.TaskList;
import hachi.Ui;

import java.time.LocalDateTime;

/**
 * Handles the {@link CommandCode#NOTE} command.
 *
 * <p>This handler expects user input of the form:
 * <pre>
 *     note &lt;taskNumber&gt; &lt;text&gt;
 * </pre>
 * Example:
 * <pre>
 *     note 2 "Remember to attach PDF"
 * </pre>
 *
 * <p>If the specified task number is valid, a new note with the provided text
 * and the current timestamp is added to that task. Otherwise, a failure
 * message is returned.</p>
 */
public class NoteHandler implements Manager {
    private final String input;
    private final Ui ui;

    /**
     * Constructs a {@code NoteHandler}.
     *
     * @param input the raw user input string
     * @param ui    the UI utility for success/failure messages
     */
    public NoteHandler(String input, Ui ui) {
        this.input = input;
        this.ui = ui;
    }

    /**
     * Executes the note command by adding a note to the specified task.
     *
     * @param tasks the task list to operate on
     * @return a success message if the note was added successfully,
     *         or a failure message if the input is invalid or out of range
     */
    @Override
    public String execute(TaskList tasks) {
        String[] parts = input.split(" ");

        // guard: not enough parts
        if (parts.length <= 2) {
            return Ui.failure(CommandCode.NOTE); // missing parameters
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return Ui.failure(CommandCode.NOTE); // invalid number format
        }

        // guard: invalid task index
        if (taskNumber < 1 || taskNumber > tasks.getTasks().size()) {
            return Ui.failure(CommandCode.MISSING);
        }

        // success path
        String information = parts[2];
        LocalDateTime creationTime = LocalDateTime.now();
        tasks.getTaskAt(taskNumber - 1).addNote(information, creationTime);
        return ui.success(CommandCode.NOTE);
    }


}

