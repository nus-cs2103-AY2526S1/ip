package joobot.command;
import joobot.main.Storage;
import joobot.task.Task;
import joobot.task.TaskList;

/**
 * Represents a command that deletes a task
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            return "Invalid task index.";
        }
        Task removed = tasks.remove(index);
        storage.save(tasks.getAllTasks());
        return "Noted. I've removed this task:\n  " + removed
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
