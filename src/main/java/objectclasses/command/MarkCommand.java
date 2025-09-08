package objectclasses.command;

import objectclasses.exception.LynxException;

/**
 * Represents a string of search modifiers for a "mark" command and stores its search results.
 * Does not handle the execution of commands.
 */
public class MarkCommand extends LynxCommand {

    /**
     * Takes in a string containing search modifiers and parses it into separate components.
     *
     * @param input String of search modifiers.
     * @throws LynxException If input is of invalid format and cannot be parsed.
     */
    public MarkCommand(String input) throws LynxException {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String emptyDialogue() {
        return "     (No tasks found or marked)";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String actionDialogue() {
        return String.format("Marked %s", super.getSearchString());
    }

}
