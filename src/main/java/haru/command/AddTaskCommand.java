package haru.command;

import java.io.IOException;
import java.util.HashMap;

import haru.model.Task;

/**
 * Base command for adding tasks.
 */
public abstract class AddTaskCommand extends Command {

    /**
     * Constructs an AddTaskCommand with option aliases.
     *
     * @param aliases map of option aliases
     * @param ctx     command context for execution
     */
    public AddTaskCommand(HashMap<String, String> aliases, CommandContext ctx) {
        super(aliases, ctx);
    }

    /**
     * Adds a task to the task list and shows confirmation.
     *
     * @param task the task to add
     * @throws IOException if Ui output fails
     */
    public void add(Task task) throws IOException {
        this.getUi().showHaruMessage("Okay~! I will add this task to your list:");
        this.getUi().showHaruMessage(task.toString());
        this.getTaskList().add(task);
    }
}
