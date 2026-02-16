package taro.command;

import java.time.LocalDate;
import java.time.LocalTime;

import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.corecomp.TaskList;
import taro.task.Event;
import taro.task.Task;
import taro.ui.Ui;

/**
 * Represents the {@code event} command which adds an {@link Event} task
 * to the current task list.
 * Example:
 *     event project meeting /from 2025-09-05 14:00 /to 16:00
 * creates an event task scheduled on 5 Sep 2025 from 2 PM to 4 PM.
 */
public class EventCommand implements Command {
    /** The raw user input string containing the event command. */
    private final String input;

    /**
     * Constructs an {@code EventCommand} with the specified input string.
     * @param input the raw user input that starts with {@code event}
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the {@code event} command.
     * Parses the description, date, start time, and end time from the input
     * string, creates an {@link Event} task, adds it to the {@link TaskList},
     * saves the updated list to {@link Storage}, and displays a confirmation
     * message through the {@link Ui}.
     *
     * @param tasks   the current list of tasks
     * @param ui      the user interface used to display messages
     * @param storage the storage handler used to save the updated task list
     * @return {@code false} as the program should not terminate after execution
     * @throws TaroException if the command format is invalid or missing the
     *                       {@code /from} or {@code /to} keywords
     * @throws java.time.format.DateTimeParseException if the date or time
     *                       values cannot be parsed into valid {@link LocalDate} or {@link LocalTime}
     * @throws IndexOutOfBoundsException if the input string does not contain
     *                       the expected number of fields after splitting
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        assert tasks != null : "TaskList must not be null when executing";
        assert ui != null : "UI must not be null when executing";
        assert storage != null : "Storage must not be null when executing";

        String body = input.substring(5).trim();
        int fromPos = body.indexOf("/from");
        int toPos = body.indexOf("/to");
        if (fromPos == -1 || toPos == -1) {
            throw new TaroException("Opps!: Event must have /from and /to.");
        }
        String desc = body.substring(0, fromPos).trim();
        String fromRaw = body.substring(fromPos + 5, toPos).trim();
        String toRaw = body.substring(toPos + 3).trim();

        String[] dateAndTime = fromRaw.split(" ", 2);
        LocalDate date = LocalDate.parse(dateAndTime[0]);
        LocalTime start = LocalTime.parse(dateAndTime[1]);
        LocalTime end = LocalTime.parse(toRaw);

        Task t = new Event(desc, date, start, end, false);
        tasks.add(t);
        storage.save(tasks);

        ui.showLine();
        ui.show(" Got it. I've added this task:");
        ui.show("   " + t);
        ui.show(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        return false;
    }
}
