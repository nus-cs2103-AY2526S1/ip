package lux.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import lux.data.AliasList;
import lux.data.DeadlineTask;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Command that adds a deadline task. The arguments must contain a
 * description and a deadline separated by the token " /by ". The deadline
 * should follow the pattern used by {@link lux.ui.Ui#getTimeFormatter()}.
 */
public class DeadlineCommand extends Command {
    private String arguments;

    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Parse the provided arguments, create a {@link lux.data.DeadlineTask},
     * append it to the task list and return the UI confirmation message.
     *
     * @throws LuxException when the arguments are malformed or date parsing
     *                      fails
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) throws LuxException {
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new LuxException("Invalid deadline format. Use: deadline {description} /by {HHmm dd-MM-yyyy}");
        }
        String description = parts[0].trim();

        try {
            LocalDateTime by = LocalDateTime.parse(parts[1].trim(), ui.getTimeFormatter());
            DeadlineTask task = new DeadlineTask(description, by);

            tasks.addTasks(task);
            return ui.addDeadline(task);
        } catch (DateTimeParseException e) {
            throw new LuxException("Invalid date/time format for deadline. Expected: HHmm dd-MM-yyyy");
        }
    }

    public boolean isExit() {
        return false;
    }
}
