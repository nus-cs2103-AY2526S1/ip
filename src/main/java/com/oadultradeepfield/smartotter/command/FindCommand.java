package com.oadultradeepfield.smartotter.command;

import java.util.List;

import com.oadultradeepfield.smartotter.task.Task;

/**
 * A command that finds tasks containing a given keyword (case-insensitive).
 */
public class FindCommand extends AbstractListCommand {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Creates a {@code FindCommand} from user input.
     *
     * @param input the search keyword
     * @return a new {@code FindCommand} instance
     */
    public static Executable fromInput(String input) {
        return new FindCommand(input.trim());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Task> getTasks(CommandContext context) {
        return context.findTasks(keyword);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String emptyMessage() {
        return "Hmm... I couldn't find any task containing \"%s\".".formatted(keyword);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String prefixMessage() {
        return "Here are the matching tasks I found:";
    }
}
