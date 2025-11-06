package bestbot.command;

import bestbot.exception.BestbotException;
import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.Task;
import bestbot.task.TaskList;
import bestbot.task.Event;

/**
 * Adds a new {@link Event} task to the task list.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Creates an {@code AddEventCommand} with the given description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from        Starting time/date of the event.
     * @param to          Ending time/date of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by creating an {@link Event} task and adding it to the task list.
     * Updates the UI and saves the task list to storage.
     *
     * @param tasks   The task list to add the new event into.
     * @param ui      The UI used to display feedback.
     * @param storage The storage used to save tasks persistently.
     * @throws BestbotException If description, start time, or end time is blank.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        if (description.isBlank() || from.isBlank() || to.isBlank()) {
            throw new BestbotException("Event needs a description, /from <time>, and /to <time>.");
        }

        Task event = new Event(description, from, to);
        tasks.add(event);

        ui.showTaskAdded(event, tasks.size());
        storage.save(tasks.getTasks());
    }

    /**
     * Returns whether this command causes the program to exit.
     *
     * @return Always {@code false}, since adding an event does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
