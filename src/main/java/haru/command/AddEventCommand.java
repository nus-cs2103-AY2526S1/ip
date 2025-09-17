package haru.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import haru.exception.HaruException;
import haru.model.Event;
import haru.model.TaskTime;

/**
 * Command to add an event task.
 */
public class AddEventCommand extends AddTaskCommand {

    /**
     * Constructs an AddEventCommand with required options.
     *
     * @param ctx command context for execution
     */
    public AddEventCommand(CommandContext ctx) {
        super(new HashMap<>(Map.of(
                "primary", "event name",
                "from", "start time",
                "to", "end time"
        )), ctx);
    }

    /**
     * Executes the command to add an event task.
     *
     * @throws HaruException if task creation fails
     * @throws IOException   if IO error occurs
     */
    @Override
    public void execute() throws HaruException, IOException {
        String name = super.getRequiredOption("primary");
        TaskTime from = super.getRequiredTime("from");
        TaskTime to = super.getRequiredTime("to");
        add(new Event(name, from, to));
    }
}
