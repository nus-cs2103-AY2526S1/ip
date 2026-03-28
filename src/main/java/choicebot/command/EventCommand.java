package choicebot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.Event;
import choicebot.task.Task;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Represents a command that creates and adds an Event task to tasklist.
 * An Event follows the format: event {description} /from {time} /to {time}
 */
public class EventCommand extends Command {
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected String description;

    /**
     * Constructs an EventCommand with the given description.
     *
     * @param description Contains name of Event, and start and end date/time.
     */
    public EventCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the Event command by creating new Event instance.
     * The Event instance is added to given task list and saved to storage.
     * Displays a confirmation message through given UI.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @param storage Storage used in current instance.
     * @throws ChoiceBotException If command does not have /from, /to, or if name, start, or end date/time missing.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws ChoiceBotException {
        handleDescription();

        String[] descriptionTimeSplit = description.split("/from", 2);
        String eventName = descriptionTimeSplit[0].trim();
        String[] timeSplit = descriptionTimeSplit[1].split("/to", 2);
        String startString = timeSplit[0].trim();
        String endString = timeSplit[1].trim();

        handleEventParameters(eventName, startString, endString);

        try {
            LocalDateTime startDate = LocalDateTime.parse(startString, DATE_FORMAT);
            LocalDateTime endDate = LocalDateTime.parse(endString, DATE_FORMAT);
            Task eventTask = new Event(eventName, false, startDate, endDate);
            tasks.addTask(eventTask);
            assert tasks.getTaskList().contains(eventTask) : "Event task was not added to task list";
            storage.saveFile(tasks);
            return ui.addTaskMessage(eventTask, tasks);
        } catch (DateTimeParseException e) {
            throw new ChoiceBotException("Please use format \"yyyy-MM-dd HH:mm\" for start and end dates.");
        }
    }

    /**
     * Throws a ChoiceBotException if no /from or /to flags.
     */
    public void handleDescription() throws ChoiceBotException {
        if (!description.contains("/from ") || !description.contains("/to")) {
            throw new ChoiceBotException(
                    "Please follow format: "
                            + "event {description} /from {yyyy-MM-dd HH:mm} /to {yyyy-MM-dd HH:mm}.");
        }
    }

    /**
     * Throws a ChoiceBotException if event parameters are blank.
     */
    public void handleEventParameters(String eventName, String start, String end) throws ChoiceBotException {
        if (eventName.isBlank() || start.isBlank() || end.isBlank()) {
            throw new ChoiceBotException(
                    "Please follow format: "
                            + "event {description} /from {yyyy-MM-dd HH:mm} /to {yyyy-MM-dd HH:mm}.");
        }
    }
}
