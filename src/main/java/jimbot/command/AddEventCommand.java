package jimbot.command;

import java.time.LocalDateTime;

import jimbot.exception.InvalidDateTimeException;
import jimbot.exception.InvalidEventException;
import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.Event;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;
import jimbot.util.Parser;

/**
 * Represents a command that adds an event task to the task list.
 * An event task has a description, a start date/time, and an end date/time,
 * specified with the {@code /from} and {@code /to} keywords in the user input.
 *
 * Example input:
 * {@code event project meeting /from 2025-09-11 14:00 /to 2025-09-11 16:00}
 *
 * @author limjimin-nus
 */
public class AddEventCommand implements Command {
    private final String userInput;

    /**
     * Constructs an AddEventCommand with the specified user input.
     *
     * @param input The raw user string input.
     */
    public AddEventCommand(String input) {
        this.userInput = input;
    }

    /**
     * Executes the command by creating and adding a new event task.
     * The task is stored in the task list and persisted to storage.
     *
     * @param userList Task list to which the new event is added.
     * @param userStorage Storage manager to update with the new task.
     * @param user UI manager used to generate the response message.
     * @return Response message confirming the addition of the event task.
     * @throws InvalidEventException If the input is missing {@code /from}
     *                               or {@code /to}, or has empty fields.
     * @throws InvalidDateTimeException If the {@code /to} time is before
     *                                  the {@code /from} time.
     * @throws JimbotException If other task-related errors occur.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            throw new InvalidEventException();
        }

        String[] event = userInput.substring(6)
                .trim()
                .split("/from", 2);
        String description = event[0].trim();
        String[] timings = event[1]
                .trim()
                .split("/to", 2);
        String from = timings[0].trim();
        String to = timings[1].trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new InvalidEventException();
        }

        LocalDateTime dateTime1 = Parser.parseDateTime(from);
        LocalDateTime dateTime2 = Parser.parseDateTime(to);
        if (dateTime2.isBefore(dateTime1)) {
            throw new InvalidDateTimeException();
        }
        Event userEvent = new Event(description, dateTime1, dateTime2);
        userList.addToList(userEvent);
        userStorage.update(userList);

        return user.addTask(userEvent, userList.getTaskCount());
    }
}
