package tony.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import tony.exceptions.TonyException;
import tony.parsers.DateTimeManager;
import tony.storage.Storage;
import tony.tasks.Deadline;
import tony.tasks.Event;
import tony.tasks.Task;
import tony.tasks.TaskList;
import tony.ui.UI;

/**
 * Represents a command to show list of tasks from the task list
 * that lie on a specified date by the user.
 * The user specifies the tasks to show using the format <code>dd-MM-yyyy</code>.
 */
public class ShowTasksOnDateCommand extends Command {
    private final LocalDateTime targetDate;

    /**
     * Constructs a new {@link ShowTasksOnDateCommand} by parsing the input date.
     *
     * @param targetDate The raw input string of the date to find events that lie in it.
     * @throws TonyException If the input date cannot be parsed into a valid date-time format.
     */
    public ShowTasksOnDateCommand(String targetDate) throws TonyException {
        try {
            this.targetDate = DateTimeManager.parse(targetDate);
        } catch (DateTimeParseException e) {
            throw new TonyException("Let me spell it out for you: dd-MM-yyyy (e.g. 12-09-2025).");
        }
    }

    /**
     * Executes the {@link ShowTasksOnDateCommand}.
     * Goes through the {@link TaskList} to find tasks that lie on the date specified by the user.
     * Displays tasks that lie on the date specified by the user through the {@link UI}.
     *
     * @param tasks The {@link TaskList} from which the task will be marked.
     * @param ui The {@link UI} instance for displaying feedback to the user.
     * @param storage The {@link Storage} instance for saving tasks to file.
     * @return The tasks found as a {@link String}.
     * @throws TonyException If the input date cannot be parsed into a valid date-time format.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws TonyException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "UI cannot be null";
        ArrayList<Task> currTasks = new ArrayList<>();
        boolean isFound = false;
        for (Task task : tasks.getList()) {
            if (task instanceof Deadline) {
                LocalDateTime dt = ((Deadline) task).getDeadline();
                if (dt.equals(targetDate)) {
                    isFound = true;
                    currTasks.add(task);
                }
            } else if (task instanceof Event) {
                LocalDateTime from = ((Event) task).getFrom();
                LocalDateTime to = ((Event) task).getTo();
                if (!from.isAfter(targetDate) && !to.isBefore(targetDate)) {
                    isFound = true;
                    currTasks.add(task);
                }
            }
        }
        return ui.showTasksOnDate(currTasks, isFound);
    }
}
