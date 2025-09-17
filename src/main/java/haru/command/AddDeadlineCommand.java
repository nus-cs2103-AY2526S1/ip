package haru.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import haru.exception.HaruException;
import haru.model.Deadline;
import haru.model.TaskTime;

/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends AddTaskCommand {

    /**
     * Constructs an AddDeadlineCommand with required options.
     *
     * @param ctx command context for execution
     */
    public AddDeadlineCommand(CommandContext ctx) {
        super(new HashMap<>(Map.of(
                "primary", "task name",
                "by", "deadline"
        )), ctx);
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @throws HaruException if task creation fails
     * @throws IOException   if IO error occurs
     */
    @Override
    public void execute() throws HaruException, IOException {
        String name = super.getRequiredOption("primary");
        TaskTime by = super.getRequiredTime("by");
        add(new Deadline(name, by));
    }
}
