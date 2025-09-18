package cheryl.command;

import cheryl.task.Task;
import cheryl.task.Event;
import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add an Event task.
 */
public class AddEventCommand implements Command {
    private String title;
    private LocalDate from;
    private LocalDate to;

    /**
     * Creates a new AddEventCommand with the given arguments.
     *
     * @param arguments The event arguments in format "title /from yyyy-MM-dd /to yyyy-MM-dd"
     * @throws DukeException If the input format is invalid
     */
    public AddEventCommand(String arguments) throws DukeException {
        try {
            String[] parts = arguments.split("/from", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            }
            title = parts[0].trim();

            String[] times = parts[1].split("/to", 2);
            if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                throw new DukeException("OOPS!!! Start and end time of an event cannot be empty.");
            }

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            from = LocalDate.parse(times[0].trim(), fmt);
            to = LocalDate.parse(times[1].trim(), fmt);

        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Please use yyyy-MM-dd format for events.");
        } catch (Exception e) {
            throw new DukeException("Invalid event format! Use: event <title> /from <start> /to <end>");
        }
    }

    /**
     * Executes the command: adds a new Event to the TaskList.
     *
     * @param tasks   The TaskList to add the Event to
     * @param ui      The Ui to display messages
     * @param storage The Storage to save the updated list
     * @throws DukeException If an error occurs during saving
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Event(title, from, to);
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(task.toString());
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
        try {
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            throw new DukeException("Error saving tasks: " + e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
