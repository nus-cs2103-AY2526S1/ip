package haru.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import haru.exception.HaruException;
import haru.model.Task;
import haru.model.TaskList;

/**
 * Command to add a tag to a task.
 */
public class AddTagCommand extends Command {

    /**
     * Constructs an AddTag command with required options.
     *
     * @param ctx command context for execution
     */
    public AddTagCommand(CommandContext ctx) {
        super(new HashMap<>(Map.of(
                "primary", "task number",
                "name", "tag name"
        )), ctx);
    }

    /**
     * Executes the command to add a tag to a task.
     *
     * @throws HaruException if adding the tag fails
     * @throws IOException   if IO error occurs
     */
    @Override
    public void execute() throws HaruException, IOException {
        TaskList taskList = this.getTaskList();
        int id = taskList.parseTaskId(this.getRequiredOption("primary"));
        String tag = this.getRequiredOption("name");
        Task task = taskList.addTag(id, tag);
        this.getUi().showHaruMessage("Okay~! I have updated this task:");
        this.getUi().showHaruMessage(task.toString());
    }
}
