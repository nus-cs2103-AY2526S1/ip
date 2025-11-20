package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import tasks.Events;
import tasks.Task;
import ui.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The AddEventCommand class represents a command to add a new event task to the task list.
 * It accepts a raw argument string (description + /from + <yyyy-mm-dd> + /to + <yyyy-mm-dd>) and performs parsing/validation in execute().
 */
public class AddEventCommand extends Command {

    private final String rawArg;

    /**
     * Constructs an AddEventCommand with the raw argument as provided by the parser.
     * Expected format: <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>
     *
     * @param rawArg raw argument string (may be null)
     */
    public AddEventCommand(String rawArg) {
        this.rawArg = rawArg;
    }

    /**
     * Executes the AddEventCommand. This method parses the raw argument string to extract the
     * description, start time, and end time of the event. It then creates a new event task and
     * adds it to the task list, saving the updated task list to storage.
     *
     * Input dates must be in ISO format: yyyy-MM-dd. Output formatting is handled by the Events task.
     *
     * @param taskList The task list where the task will be added.
     * @param ui The UI object to interact with the user (though not used in this method).
     * @param storage The storage object to save the updated task list.
     * @throws JackException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        if (rawArg == null || rawArg.trim().isEmpty()) {
            throw new JackException("The description and time frame of an event cannot be empty. Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        }
        // Expect format: <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>
        String[] parts = rawArg.split("/from", 2);
        if (parts.length < 2) {
            throw new JackException("Missing time frame. Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        }
        String description = parts[0].trim();
        String timesPart = parts[1].trim();
        String[] timeParts = timesPart.split("/to", 2);
        if (timeParts.length < 2) {
            throw new JackException("Missing '/to'. Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        if (description.isEmpty()) {
            throw new JackException("Description cannot be empty. Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        }
        if (from.isEmpty()) {
            throw new JackException("Missing start time after '/from'. Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        }
        if (to.isEmpty()) {
            throw new JackException("Missing end time after '/to'. Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        }

        // Parse start and end as ISO dates (yyyy-MM-dd) and validate ordering
        LocalDate fromDate;
        LocalDate toDate;
        try {
            fromDate = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            throw new JackException("Invalid start date format. Expected yyyy-MM-dd. Received: '" + from + "'. Use: event <description> /from yyyy-MM-dd /to yyyy-MM-dd");
        }
        try {
            toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new JackException("Invalid end date format. Expected yyyy-MM-dd. Received: '" + to + "'. Use: event <description> /from yyyy-MM-dd /to yyyy-MM-dd");
        }

        if (fromDate.isAfter(toDate)) {
            throw new JackException("Event start date is after end date. Ensure start is on or before end. Received: from='" + from + "', to='" + to + "'.");
        }

        // All checks passed; create event (Events will parse the ISO strings as LocalDate internally)
        Task task = new Events(description, from, to);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        return ui.showAdd(task, taskList.getTaskCount());
    }

    /**
     * Returns false as this command does not cause the program to exit.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false; // This command doesn't cause the program to exit
    }
}
