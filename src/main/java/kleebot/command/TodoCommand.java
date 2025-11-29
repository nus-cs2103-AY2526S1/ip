package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.task.ToDo;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Ui;

/**
 * A command to create a to-do task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a new {@code TodoCommand}.
     *
     * @param description The description of the to-do task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Adds a to-do task to the task list.
     *
     * @param tasks   The task list to add the to-do to.
     * @param ui      The UI to display messages.
     * @param storage The storage used for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo todo = new ToDo(description);
        tasks.addToList(todo);
        ui.updateList(todo, tasks.getSize());
    }

    @Override
    public String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        ToDo todo = new ToDo(description);
        tasks.addToList(todo);
        storage.saveTasksToLocal(tasks.getTasks());
        return ui.formatAddTask(todo, tasks.getSize());
    }
}
