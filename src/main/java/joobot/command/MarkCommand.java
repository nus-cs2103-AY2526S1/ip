package joobot.command;
import joobot.main.Storage;
import joobot.task.Task;
import joobot.task.TaskList;

/**
 * Represents a command that marks a task
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            return "Invalid task index.";
        }
        Task task = tasks.getAllTasks().get(index);
        task.markDone();
        storage.save(tasks.getAllTasks());
        return "Nice! I've marked this task as done:\n  " + task;
    }
}
