package com.oadultradeepfield.smartotter.command;

import java.util.List;

import com.oadultradeepfield.smartotter.task.Task;

/**
 * A command that lists all current tasks with their status.
 */
public class ListCommand extends AbstractListCommand {
    /**
     * Creates a {@code ListCommand} from user input. This command ignores any provided input.
     *
     * @param input unused input string
     * @return a new {@code ListCommand} instance
     */
    @SuppressWarnings("unused")
    public static Executable fromInput(String input) {
        return new ListCommand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Task> getTasks(CommandContext context) {
        return context.tasks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String emptyMessage() {
        return "Congratulations! You don't have any tasks at the moment.";
    }
}
