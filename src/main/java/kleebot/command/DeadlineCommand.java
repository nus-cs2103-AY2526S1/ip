package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.Deadline;
import kleebot.task.TaskList;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Ui;

/**
 * A command to create a deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs a new {@code DeadlineCommand}.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date/time.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param tasks   The task list to add the deadline to.
     * @param ui      The UI to display messages.
     * @param storage The storage used for saving tasks.
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        tasks.addToList(deadline);
        ui.updateList(deadline, tasks.getSize());
    }

    @Override
    public String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        Deadline deadline = new Deadline(description, by);
        tasks.addToList(deadline);
        storage.saveTasksToLocal(tasks.getTasks());
        return ui.formatAddTask(deadline, tasks.getSize());
    }


    public String getDescription() {
        return description;
    }

    public String getBy() {
        return by;
    }
}
