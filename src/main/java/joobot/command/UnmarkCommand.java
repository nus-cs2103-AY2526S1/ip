package joobot.command;
import joobot.main.Storage;
import joobot.task.Task;
import joobot.task.TaskList;

/**
 * Represents a command that unmarks a task
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            return "Invalid task index.";
        }
        Task task = tasks.getAllTasks().get(index);
        task.markNotDone();
        storage.save(tasks.getAllTasks());
        return "OK, I've marked this task as not done yet:\n  " + task;
    }
}
