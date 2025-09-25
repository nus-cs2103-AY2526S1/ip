package heimerdinger.command;

import heimerdinger.HeimerdingerException;
import heimerdinger.Storage;
import heimerdinger.Ui;
import heimerdinger.parser.DateTimeParser;
import heimerdinger.task.Deadline;
import heimerdinger.task.Event;
import heimerdinger.task.Task;
import heimerdinger.task.TaskList;
import heimerdinger.task.ToDo;

/**
 * Shows an agenda-style view of upcoming deadlines and events on or after
 * a given date.
 *
 * <p><b>Usage:</b> {@code schedule &lt;yyyy-MM-dd&gt;}</p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * schedule 2025-10-01
 * }</pre>
 *
 * <p>This command ignores {@link ToDo} tasks, and only considers:</p>
 * <ul>
 *   <li>{@link Deadline} tasks — included if their deadline is after the given date.</li>
 *   <li>{@link Event} tasks — included if the given date lies between their
 *       {@code fromDate} and {@code toDate} (exclusive of boundaries).</li>
 * </ul>
 *
 * <p>All matching tasks are displayed via {@link Ui} in an agenda-like list.</p>
 *
 * @see Deadline
 * @see Event
 * @see DateTimeParser
 */
public class ScheduleCommand extends Command {
    private final String dateTime;

    /**
     * Constructs a {@code ScheduleCommand} with the given date string.
     *
     * @param dateTime a string representing the reference date in {@code yyyy-MM-dd}
     *                 format; must not be {@code null}
     */
    public ScheduleCommand(String dateTime) {
        this.dateTime = dateTime.trim();
    }

    /**
     * Parses the given date, filters deadlines and events relative to it,
     * and displays the matching tasks via {@link Ui}.
     *
     * <p>If the date string cannot be parsed, a {@link HeimerdingerException}
     * is thrown by the {@link DateTimeParser} utility.</p>
     *
     * @param tasks   the live task list to search; must not be {@code null}
     * @param ui      the UI sink used to display scheduled tasks; must not be {@code null}
     * @param storage the persistence layer (unused in this command)
     * @throws HeimerdingerException if the date cannot be parsed
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeimerdingerException {
        assert this.dateTime != null : "Date cannot be empty!";
        DateTimeParser dateTime = DateTimeParser.parse(this.dateTime);

        TaskList displayList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getStatusIcon().equals("X")) {
                continue;
            }

            if (task instanceof ToDo) {
                displayList.add(task);
            }

            if (task instanceof Deadline) {
                DateTimeParser deadline = ((Deadline) task).getDeadline();
                if (dateTime.isBefore(deadline)) {
                    displayList.add(task);
                }
            }

            if (task instanceof Event) {
                DateTimeParser fromDate = ((Event) task).getFromDate();
                DateTimeParser toDate = ((Event) task).getToDate();
                boolean timeInRange =
                        fromDate.isBefore(dateTime)
                                && dateTime.isBefore(toDate);
                boolean dateInRange = (
                        fromDate.toLocalDate().isBefore(dateTime.toLocalDate())
                            || fromDate.toLocalDate().isEqual(dateTime.toLocalDate()))
                                && (toDate.toLocalDate().isAfter(dateTime.toLocalDate())
                                    || toDate.toLocalDate().isEqual((dateTime.toLocalDate())));
                if (dateInRange || timeInRange) {
                    displayList.add(task);
                }
            }
        }
        ui.showScheduledTasks(displayList, dateTime.toString());
    }
}
