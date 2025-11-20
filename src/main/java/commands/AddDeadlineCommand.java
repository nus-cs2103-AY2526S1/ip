package commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import tasks.Deadline;
import tasks.Task;
import ui.Ui;

/**
 * The AddDeadlineCommand class represents a command to add a new deadline task to the task list.
 * It accepts a raw argument string (description + /by + date) and performs parsing/validation in execute().
 */
public class AddDeadlineCommand extends Command {
    private final String rawArg;

    /**
     * Constructs an AddDeadlineCommand with the raw argument as provided by the parser.
     * The raw argument should contain the description and the deadline separated by "/by".
     *
     * @param rawArg raw argument string (may be null)
     */
    public AddDeadlineCommand(String rawArg) {
        this.rawArg = rawArg;
    }

    /**
     * Executes the AddDeadlineCommand. This method parses and validates the raw argument string,
     * checks for the presence of both description and deadline date, creates a new Deadline task,
     * adds it to the task list, and saves the updated task list to storage.
     *
     * @param taskList The task list where the task will be added.
     * @param ui The UI object to interact with the user (though not used in this method).
     * @param storage The storage object to save the updated task list.
     * @throws JackException If the raw argument is empty, the deadline format is invalid,
     * or the input is incorrect.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        if (rawArg == null || rawArg.trim().isEmpty()) {
            throw new JackException("Description and deadline date missing. Use: deadline <description> /by <yyyy-MM-dd>");
        }
        // Expect format: <description> /by <yyyy-MM-dd>
        String[] parts = rawArg.split("/by", 2);
        if (parts.length < 2) {
            throw new JackException("Deadline date missing. Use: deadline <description> /by <yyyy-MM-dd>");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        if (description.isEmpty()) {
            throw new JackException("Description cannot be empty. Use: deadline <description> /by <yyyy-MM-dd>");
        }
        if (by.isEmpty()) {
            throw new JackException("Missing deadline date after '/by'. Use: deadline <description> /by <yyyy-MM-dd>");
        }

        // Quick check: detect if user provided a time component which is not supported for Deadline
        if (by.matches(".*[:T ].*")) {
            // Contains ':' or 'T' or space which likely indicates a time component
            throw new JackException("Invalid deadline value: deadlines accept date only in yyyy-MM-dd (no time). Received: '" + by + "'.");
        }

        try {
            // Validate date format (yyyy-MM-dd)
            LocalDate parsedDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Task task = new Deadline(description, parsedDate.toString());
            taskList.addTask(task);
            storage.saveTasks(taskList);
            return ui.showAdd(task, taskList.getTaskCount());
        } catch (DateTimeParseException e) {
            throw new JackException("Invalid date format for deadline. Expected yyyy-MM-dd. Received: '" + by + "'.");
        }
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
