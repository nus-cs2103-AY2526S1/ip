package objectclasses.command;

import objectclasses.exception.LynxException;

/**
 * Represents a string of search modifiers for a "delete" command and stores its search results.
 * Does not handle the execution of commands.
 */
public class DeleteCommand extends LynxCommand {

    /**
     * Takes in a string containing search modifiers and parses it into separate components.
     *
     * @param input String of search modifiers.
     * @throws LynxException If input is of invalid format and cannot be parsed.
     */
    public DeleteCommand(String input) throws LynxException {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String emptyDialogue() {
        return "     (No tasks found or deleted)";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String actionDialogue() {
        return String.format("Removed %s", super.getSearchString());
    }

}
