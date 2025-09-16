package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.task.Event;
import tinkerton.storage.Save;

/**
 * Represents a command to add an Event task.
 */
public class EventCommand extends Command {
    private static final String DATE_TIME_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

    /**
     * Constructs an EventCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public EventCommand(String fullCommand) {
        super(fullCommand);
    }

    public String parseEventName(String fullCommand) {
        return fullCommand.substring(6, fullCommand.indexOf("/from")).trim();
    }

    public String parseEventStart(String fullCommand) {
        return fullCommand.substring(fullCommand.indexOf("/from") + 5, fullCommand.indexOf("/to"))
                .trim();
    }

    public String parseEventEnd(String fullCommand) {
        return fullCommand.substring(fullCommand.indexOf("/to") + 3).trim();
    }

    /**
     * Executes the Event command, adding a new Event task to the list.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface handler.
     * @param save The save handler for persisting tasks.
     * @throws TinkertonException If the command format is invalid.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        String fullCommand = super.getFull();
        String eventName = parseEventName(fullCommand);
        String eventStart = parseEventStart(fullCommand);
        String eventEnd = parseEventEnd(fullCommand);

        if (!fullCommand.contains("/from") || !fullCommand.contains("/to")) {
            throw new TinkertonException("Your event has no start and end...");
        }

        if (eventStart.isEmpty() || eventEnd.isEmpty()) {
            throw new TinkertonException("You seem to be missing some information...");
        }

        if (!eventStart.matches(DATE_TIME_REGEX)) {
            throw new TinkertonException(
                    "The format of your event start time should be yyyy-MM-dd HHmm!");
        }

        if (!eventEnd.matches(DATE_TIME_REGEX)) {
            throw new TinkertonException(
                    "The format of your event end time should be yyyy-MM-dd HHmm!");
        }
        
        if (tasks.containsTaskName(eventName)) {
            throw new TinkertonException("This task already exists in your list.");
        }

        int prevSize = tasks.size();
        tasks.add(new Event(eventName, false, eventStart, eventEnd));
        assert tasks.size() == prevSize + 1 : "TaskList size should increase after adding an event";

        save.save(tasks);

        return "Got it, I've added this task:\n" + tasks.get(tasks.size() - 1).toString()
                + "<SPLIT>Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false, as adding an Event does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
