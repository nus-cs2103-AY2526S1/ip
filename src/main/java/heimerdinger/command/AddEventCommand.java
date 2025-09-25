package heimerdinger.command;

import heimerdinger.HeimerdingerException;
import heimerdinger.Storage;
import heimerdinger.Ui;
import heimerdinger.task.Event;
import heimerdinger.task.TaskList;

/**
 * Adds an {@code Event} task with a start and end date/time.
 *
 * <p><b>Usage:</b> {@code event &lt;description&gt; /from &lt;yyyy-MM-dd[ HH:mm]&gt;
 * /to &lt;yyyy-MM-dd[ HH:mm]&gt;}</p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * event project sync /from 2025-10-03 09:00 /to 2025-10-03 10:00
 * }</pre>
 *
 * <p>This command validates that a description is provided, that a {@code /from} segment
 * exists and is non-empty, and that a {@code /to} segment exists and is non-empty. Date/time
 * parsing and any range validation (e.g., ensuring {@code from <= to}) are delegated to
 * {@link Event}.</p>
 *
 * @see Event
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String fromDate;
    private final String toDate;

    /**
     * Constructs an {@code AddEventCommand} by parsing the raw argument string.
     *
     * <p>The argument string must contain a description followed by
     * {@code " /from "} and a start date/time, then {@code " /to "} and an end date/time,
     * in one of the accepted formats (e.g., {@code yyyy-MM-dd} or {@code yyyy-MM-dd HH:mm}).</p>
     *
     * @param args raw argument string after the {@code event} command word
     *             (for example, {@code "project sync /from 2025-10-03 09:00 /to 2025-10-03 10:00"});
     *             must not be {@code null}
     * @throws HeimerdingerException if the description is missing, {@code /from} or {@code /to}
     *                               is absent, or any required segment is blank
     */
    public AddEventCommand(String args) throws HeimerdingerException {
        String[] split = args.split(" /from ", 2);
        if (split[0].isEmpty() || split.length < 2) {
            throw new HeimerdingerException("The event needs a description before using '/from'!");
        }
        String[] secondSplit = split[1].split(" /to ", 2);
        if (secondSplit[0].isEmpty() || secondSplit.length < 2) {
            throw new HeimerdingerException("The event needs a from date before using '/by'!");
        } else if (secondSplit[1].isEmpty()) {
            throw new HeimerdingerException("The event needs a to date after using '/to'");
        }
        this.description = split[0].trim();
        this.fromDate = secondSplit[0].trim();
        this.toDate = secondSplit[1].trim();
    }

    /**
     * Creates the {@link Event}, appends it to {@code tasks}, informs the user via {@code ui},
     * and persists the updated list via {@code storage}.
     *
     * <p>Any date parsing or logical validation errors thrown by {@link Event} are surfaced
     * as {@link HeimerdingerException} to the caller.</p>
     *
     * @param tasks   the live task list to mutate; must not be {@code null}
     * @param ui      the UI sink used to display feedback; must not be {@code null}
     * @param storage the persistence layer used to save changes; must not be {@code null}
     * @throws HeimerdingerException if event creation fails (e.g., invalid dates) or persistence fails
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeimerdingerException {
        Event event = new Event(description, fromDate, toDate);
        tasks.add(event);
        ui.showAdded(event);
        storage.save(tasks.getAll());
    }
}
