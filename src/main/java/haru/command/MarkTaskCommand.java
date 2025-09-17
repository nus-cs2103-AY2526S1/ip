package haru.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import haru.exception.HaruException;
import haru.model.Task;
import haru.model.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkTaskCommand extends Command {

    /**
     * Constructs a MarkTaskCommand with required options.
     *
     * @param ctx command context for execution
     */
    public MarkTaskCommand(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task number")), ctx);
    }

    /**
     * Executes the command to mark a task as done.
     *
     * @throws HaruException if task update fails
     * @throws IOException   if IO error occurs
     */
    @Override
    public void execute() throws HaruException, IOException {
        TaskList taskList = this.getTaskList();
        int id = taskList.parseTaskId(this.getRequiredOption("primary"));
        Task task = taskList.mark(id);
        this.getUi().showHaruMessage("Okay~! I will mark this task as done:");
        this.getUi().showHaruMessage(task.toString());
    }
}
