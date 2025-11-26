package aries.command;

import aries.AriesException;
import aries.task.Events;
import aries.task.Task;
import aries.task.TaskList;
import aries.ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand implements Command {
    private String description;

    /**
     * Constructs an EventCommand with the specified description.
     *
     * @param description The full description of the event task, including /from and /to.
     */
    public EventCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui) throws AriesException {
        if (description == null || description.isEmpty()) {
            throw new AriesException("OOPS!!! The description of an event cannot be empty.");
        }

        int fromIndex = description.indexOf(" /from ");
        int toIndex = description.indexOf(" /to ");
        boolean hasFrom = fromIndex != -1;
        boolean hasTo = toIndex != -1;
        boolean hasValidOrder = hasFrom && hasTo && toIndex > fromIndex;

        if (!hasValidOrder) {
            throw new AriesException("OOPS!!! The event format should be: event <description>"
                    + " /from <start time> /to <end time>");
        }

        String taskDescription = description.substring(0, fromIndex).trim();
        String fromDate = description.substring(fromIndex + " /from ".length(), toIndex).trim();
        String toDate = description.substring(toIndex + " /to ".length()).trim();
        boolean hasDescription = !taskDescription.isEmpty();
        boolean hasDate = !fromDate.isEmpty() && !toDate.isEmpty();

        if (!hasDescription || !hasDate) {
            throw new AriesException("OOPS!!! The event format should be: event <description>"
                    + " /from <start time> /to <end time>");
        }

        Task t = new Events(taskDescription, fromDate, toDate);
        tasks.addTask(t);
        return new CommandResult(ui.showAddedString(tasks), true, false);
    }
}
