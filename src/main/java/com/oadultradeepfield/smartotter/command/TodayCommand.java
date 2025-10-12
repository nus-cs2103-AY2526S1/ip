package com.oadultradeepfield.smartotter.command;

import java.util.List;

import com.oadultradeepfield.smartotter.task.Task;

/**
 * A command that lists all tasks scheduled for today.
 */
public class TodayCommand extends AbstractListCommand {
    /**
     * Creates a {@code TodayCommand} from user input. This command ignores any provided input.
     *
     * @param input unused input string
     * @return a new {@code TodayCommand} instance
     */
    @SuppressWarnings("unused")
    public static Executable fromInput(String input) {
        return new TodayCommand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Task> getTasks(CommandContext context) {
        return context.listToday();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String emptyMessage() {
        return "Congratulations! You don't have any tasks scheduled for today.";
    }
}
