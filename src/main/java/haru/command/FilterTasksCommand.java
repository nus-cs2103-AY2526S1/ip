package haru.command;

import java.util.HashMap;
import java.util.Map;

import haru.exception.HaruException;

/**
 * Command to filter tasks by tag.
 */
public class FilterTasksCommand extends Command {

    /**
     * Constructs a FilterTasks command with required options.
     *
     * @param ctx command context for execution
     */
    public FilterTasksCommand(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "search tag")), ctx);
    }

    /**
     * Executes the command to filter tasks by tag.
     *
     * @throws HaruException if filtering fails
     */
    @Override
    public void execute() throws HaruException {
        String tag = super.getRequiredOption("primary");
        this.getUi().showHaruMessage("Okay~! Here are all the tasks that match:");
        this.getUi().showHaruMessage(this.getTaskList().filter(tag).toString());
    }
}
