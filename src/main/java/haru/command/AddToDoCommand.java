package haru.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import haru.exception.HaruException;
import haru.model.ToDo;

/**
 * Command to add a todo task.
 */
public class AddToDoCommand extends AddTaskCommand {

    /**
     * Constructs an AddToDoCommand with required options.
     *
     * @param ctx command context for execution
     */
    public AddToDoCommand(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task name")), ctx);
    }

    /**
     * Executes the command to add a todo task.
     *
     * @throws HaruException if task creation fails
     * @throws IOException   if IO error occurs
     */
    @Override
    public void execute() throws HaruException, IOException {
        String name = super.getRequiredOption("primary");
        add(new ToDo(name));
    }
}
