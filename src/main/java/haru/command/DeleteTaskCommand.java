package haru.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import haru.exception.HaruException;
import haru.model.Task;
import haru.model.TaskList;

/**
 * Command to delete a task.
 */
public class DeleteTaskCommand extends Command {

    /**
     * Constructs a DeleteTask command with required options.
     *
     * @param ctx command context for execution
     */
    public DeleteTaskCommand(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task number")), ctx);
    }

    /**
     * Executes the command to delete a task.
     *
     * @throws HaruException if task deletion fails
     * @throws IOException   if IO error occurs
     */
    @Override
    public void execute() throws HaruException, IOException {
        TaskList taskList = this.getTaskList();
        int id = taskList.parseTaskId(this.getRequiredOption("primary"));
        Task task = taskList.remove(id);
        this.getUi().showHaruMessage("Okay~! I will delete this task:");
        this.getUi().showHaruMessage(task.toString());
    }
}
