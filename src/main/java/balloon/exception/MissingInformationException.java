package balloon.exception;

import balloon.command.Command.CommandType;

/**
 * Represents an exception that is thrown when a command entered by the user
 * does not contain the required information.
 * <p> This exception provides a customized error message depending on the type
 * of command that is missing information.</p>
 */
public class MissingInformationException extends BalloonException {
    private String requirement;
    private String commandName;

    /**
     * Creates a {@code MissingInformationException} with an error message specific
     * to the given {@code commandType}
     *
     * @param commandType   the type of command that is missing information
     */
    public MissingInformationException(CommandType commandType) {
        switch(commandType) {
        case TODO:
            commandName = "<todo>";
            requirement = "have a non-empty description";
            break;
        case DEADLINE:
            commandName = "<deadline>";
            requirement = "have non-empty description and deadline";
            break;
        case EVENT:
            commandName = "<event>";
            requirement = "have non-empty description, from and to";
            break;
        case MARK:
            commandName = "<mark>";
            requirement = "be followed by an integer representing a task";
            break;
        case UNMARK:
            commandName = "<unmark>";
            requirement = "be followed by an integer representing a task";
            break;
        case DELETE:
            commandName = "<delete>";
            requirement = "be followed by an integer representing a task";
            break;
        case FIND:
            commandName = "<find>";
            requirement = "be followed by a String keyword";
            break;
        default:
            // do nothing
        }
    }

    @Override
    public String toString() {
        return String.format("The %s command must %s", commandName, requirement);
    }
}
