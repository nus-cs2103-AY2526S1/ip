package Coffee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides helper operations used by commands to add tasks, persist them, and acknowledge actions to the user.
 * Each utility method encapsulates constructing a task, adding it to the {@link TaskList}, saving via {@link Storage},
 * and emitting user-facing confirmations through {@link Ui}.
 */
public class CommandUtil {

    /**
     * Adds a {@link ToDo} task, saves all tasks to storage, and acknowledges the action to the user.
     *
     * @param description description of the to-do task.
     * @param tasks       task list to mutate.
     * @param ui          UI used to display confirmation messages.
     * @param storage     storage used to persist the updated task list.
     * @throws IllegalArgumentException if the description is blank.
     */
    public static void addSaveAndAck(String description, TaskList tasks, Ui ui, Storage storage) {
        ToDo t = new ToDo(description);
        tasks.addTask(t);
        storage.save(tasks.view());
        ui.showMessage("Got it. I've added this task:\n" + t);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds a {@link Deadline} task, saves all tasks to storage, and acknowledges the action to the user.
     *
     * @param description description of the deadline task.
     * @param by          deadline in {@code yyyy-MM-dd HHmm} format.
     * @param tasks       task list to mutate.
     * @param ui          UI used to display confirmation messages.
     * @param storage     storage used to persist the updated task list.
     * @throws IllegalArgumentException if the description or deadline is invalid as defined by {@link Deadline}.
     */
    public static void addDeadlineSaveAndAck(String description, String by, TaskList tasks, Ui ui, Storage storage) {
        Deadline d = new Deadline(description, by);
        tasks.addTask(d);
        storage.save(tasks.view());
        ui.showMessage("Got it. I've added this task:\n" + d);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds an {@link Event} task after validating that it does not overlap existing events, then saves
     * all tasks to storage and acknowledges the action to the user.
     * Overlap detection uses the rule: intervals {@code [startA, endA)} and {@code [startB, endB)} overlap iff
     * {@code startA < endB} and {@code endA > startB}.
     *
     * @param description description of the event.
     * @param from        start datetime in {@code yyyy-MM-dd HHmm} format.
     * @param to          end datetime in {@code yyyy-MM-dd HHmm} format.
     * @param tasks       task list to validate against and mutate.
     * @param ui          UI used to display error/confirmation messages.
     * @param storage     storage used to persist the updated task list.
     * @throws java.time.format.DateTimeParseException if {@code from} or {@code to} is not in the expected format.
     * @throws IllegalArgumentException                if the event construction fails due to invalid arguments.
     */
    public static void addEventSaveAndAck(String description, String from, String to, TaskList tasks,
                                          Ui ui, Storage storage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime newFrom = LocalDateTime.parse(from, formatter);
        LocalDateTime newTo = LocalDateTime.parse(to, formatter);

        for (Task t : tasks.view()) {
            if (t instanceof Event) {
                Event e = (Event) t;
                // Check for overlap: (startA < endB) && (endA > startB)
                if (newFrom.isBefore(e.to) && newTo.isAfter(e.from)) {
                    ui.showMessage("Error: This event overlaps with an existing event.");
                    return;
                }
            }
        }

        Event e = new Event(description, from, to);
        tasks.addTask(e);
        storage.save(tasks.view());
        ui.showMessage("Got it. I've added this task:\n" + e);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }
}
