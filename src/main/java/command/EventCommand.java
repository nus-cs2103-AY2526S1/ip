package command;

import exception.BaymaxException;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import task.TaskType;
import ui.Ui;

/**
 * Represents a command that adds an {@link Event} to the task list.
 */
public class EventCommand extends Command {
    private final String desc;
    private final String from;
    private final String to;

    /**
     * Creates a new {@code EventCommand}.
     *
     * @param desc The description of the event task.
     * @param from The start date/time of the event.
     * @param to   The end date/time of the event.
     */

    public EventCommand(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command by creating an {@code Event} task,
     * adding it to the task list, and saving the updated list to storage.
     *
     * @param tasks   The task list to which the event will be added.
     * @param ui      The UI component used to display messages.
     * @param storage The storage component used to save tasks.
     * @return A message confirming the task has been added, or an error message if creation fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Event t = new Event(desc, TaskType.EVENT, from, to);
            tasks.add(t);
            storage.save(tasks.getAll());
            return ui.showTaskAdded(t, tasks.size());
        } catch (BaymaxException e) {
            return ui.showError(e.getMessage());
        }
    }
}
