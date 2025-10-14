package lux.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import lux.data.AliasList;
import lux.data.EventTask;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Command that adds an event task with a start and end time. The
 * arguments must include a description, "/from" time and "/to" time.
 */
public class EventCommand extends Command {
    private String arguments;

    public EventCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Parse arguments, create an {@link lux.data.EventTask}, add it to the
     * task list and return the UI message. Throws {@link LuxException} when
     * required parts are missing or date parsing fails.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) throws LuxException {
        String[] fromSplit = arguments.split(" /from ", 2);
        if (fromSplit.length < 2) {
            throw new LuxException(
                    "Please follow this format: todo {description} /from {HHmm dd-MM-yyyy} /to {HHmm dd-MM-yyyy}");
        }

        String description = fromSplit[0].trim();
        String[] toSplit = fromSplit[1].split(" /to ", 2);
        if (toSplit.length < 2) {
            throw new LuxException(
                    "Please follow this format: todo {description} /from {HHmm dd-MM-yyyy} /to {HHmm dd-MM-yyyy}");
        }

        try {
            LocalDateTime from = LocalDateTime.parse(toSplit[0].trim(), ui.getTimeFormatter());
            LocalDateTime to = LocalDateTime.parse(toSplit[1].trim(), ui.getTimeFormatter());
            EventTask task = new EventTask(description, from, to);

            tasks.addTasks(task);
            return ui.addEvent(task);
        } catch (DateTimeParseException e) {
            throw new LuxException(
                    "Error: Invalid date/time format. Please follow this format: {HHmm dd-MM-yyyy}");
        }

    }

    public boolean isExit() {
        return false;
    }
}
