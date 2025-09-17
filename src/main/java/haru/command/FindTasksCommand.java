package haru.command;

import java.util.HashMap;
import java.util.Map;

import haru.exception.HaruException;

/**
 * Command to find tasks matching a string.
 */
public class FindTasksCommand extends Command {

    /**
     * Constructs a FindTasksCommand with required options.
     *
     * @param ctx command context for execution
     */
    public FindTasksCommand(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "search string")), ctx);
    }

    /**
     * Executes the command to list matching tasks.
     *
     * @throws HaruException if search input is missing
     */
    @Override
    public void execute() throws HaruException {
        String str = super.getRequiredOption("primary");
        this.getUi().showHaruMessage("Okay~! Here are all the tasks that match:");
        this.getUi().showHaruMessage(this.getTaskList().find(str).toString());
    }
}
