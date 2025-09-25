package heimerdinger.command;

import java.time.format.DateTimeParseException;

import heimerdinger.HeimerdingerException;
import heimerdinger.Storage;
import heimerdinger.Ui;
import heimerdinger.task.Deadline;
import heimerdinger.task.TaskList;

/**
 * Adds a {@code Deadline} task with a due date/time.
 *
 * <p><b>Usage:</b> {@code deadline <description> /by <yyyy-MM-dd[ HH:mm]>}</p>
 *
 * <p>Examples:</p>
 * <pre>
 * deadline submit lab /by 2025-10-31 18:00
 * deadline file taxes /by 2025-04-15
 * </pre>
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    /**
     * Constructs an {@code AddDeadlineCommand} by parsing the raw argument string.
     *
     * <p>The argument string must contain a description, followed by
     * {@code " /by "} and a date/time in one of the accepted formats
     * (e.g., {@code yyyy-MM-dd} or {@code yyyy-MM-dd HH:mm}).</p>
     *
     * @param args raw argument string that follows the {@code deadline} command word
     *             (for example, {@code "submit lab /by 2025-10-31 18:00"}); must not be {@code null}
     * @throws HeimerdingerException if the description is missing/blank or the {@code /by} segment is absent
     */
    public AddDeadlineCommand(String args) throws HeimerdingerException {
        String[] split = args.split(" /by ", 2);
        if (split[0].isEmpty()) {
            throw new HeimerdingerException("The deadline requires a description!");
        } else if (split.length < 2) {
            throw new HeimerdingerException("The deadline requires a due date!");
        }
        this.description = split[0].trim();
        this.deadline = split[1].trim();
    }

    /**
     * Creates the {@link Deadline} task, appends it to {@code tasks}, informs the user via {@code ui},
     * and persists the updated list via {@code storage}.
     *
     * <p>If the provided date/time cannot be parsed by {@link Deadline}, this method catches the
     * resulting {@link DateTimeParseException} and wraps it into a {@link HeimerdingerException}
     * with a concise tip on valid formats.</p>
     *
     * @param tasks   the live task list to mutate; must not be {@code null}
     * @param ui      the UI sink used to display feedback; must not be {@code null}
     * @param storage the persistence layer used to save changes; must not be {@code null}
     * @throws HeimerdingerException if the date/time format is invalid or if saving to storage fails
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeimerdingerException {
        try {
            Deadline task = new Deadline(description, deadline);
            tasks.add(task);
            ui.showAdded(task);
            storage.save(tasks.getAll());
        } catch (DateTimeParseException e) {
            throw new HeimerdingerException("Would you like some assistance in learning how to write dates? "
                    + "(It's in the 'yyyy-mm-dd'/'yyyy-mm-dd hh:mm' format!)");
        }
    }
}
