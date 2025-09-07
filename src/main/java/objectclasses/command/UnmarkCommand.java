package objectclasses.command;

import objectclasses.exception.LynxException;
import objectclasses.task.Task;

import java.util.function.Consumer;

/**
 * Represents a string of search modifiers for an "unmark" command and stores its search results.
 */
public class UnmarkCommand extends LynxCommand {

    /**
     * Takes in a string containing search modifiers and parses it into separate components.
     *
     * @param input String of search modifiers.
     * @throws LynxException If input is of invalid format and cannot be parsed.
     */
    public UnmarkCommand(String input) throws LynxException {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String emptyDialogue() {
        return "     (No tasks found or unmarked)";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String actionDialogue() {
        return String.format("Unmarked %s", super.getSearchString());
    }

}
