package com.oadultradeepfield.smartotter.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.util.CustomIO;

/**
 * Base class for commands that output a list of tasks.
 */
public abstract class AbstractListCommand implements Executable {
    /**
     * Gets the tasks relevant for this command.
     *
     * @param context the command context
     * @return the list of tasks to display
     */
    protected abstract List<Task> getTasks(CommandContext context);

    /**
     * Returns the message shown when there are no tasks to display.
     *
     * @return the empty message
     */
    protected abstract String emptyMessage();

    /**
     * Returns the optional prefix shown before listing tasks (e.g., "Here are the matching tasks I found:")
     *
     * @return the prefix string (can be empty)
     */
    protected String prefixMessage() {
        return "";
    }

    @Override
    public String execute(CommandContext context) {
        assert context != null : "Context cannot be null";

        List<Task> tasks = getTasks(context);

        String result =
            IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                .collect(Collectors.joining("\n"));

        if (tasks.isEmpty()) {
            CustomIO.printPretty(emptyMessage());
            return emptyMessage();
        } else {
            String message = prefixMessage() + (prefixMessage().isEmpty() ? "" : "\n") + result;
            CustomIO.printPretty(message);
            return message;
        }
    }
}
