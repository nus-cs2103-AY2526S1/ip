package jinbot.command;

import java.time.LocalDate;

import jinbot.task.Deadline;
import jinbot.task.Task;
import jinbot.task.TaskList;
import jinbot.ui.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 * A deadline task has a description and a due date.
 */
public class DeadlineCommand implements Command {
    private final String description;
    private final LocalDate by;

    /**
     * Constructs a DeadlineCommand with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date of the deadline task.
     */
    public DeadlineCommand(String description, LocalDate by) {
        // Arguments must be valid.
        assert description != null : "Description should not be null";
        assert !description.isBlank() : "Description should not be empty";
        assert by != null : "By should not be null";

        this.description = description;
        this.by = by;
    }

    /**
     * Executes the deadline command by creating a new Deadline task,
     * adding it to the task list, and displaying a confirmation message.
     *
     * @param ui       The UI instance that handles user interaction.
     * @param taskList The task list to which the deadline task is added.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task deadline = new Deadline(description, by);
        taskList.addTask(deadline);

        String response = "Got it. I've added this task:\n  " + deadline
            + "\nNow you have " + taskList.getSize()
            + " tasks in the list.";

        ui.printBox(response);
        return response;
    }
}
