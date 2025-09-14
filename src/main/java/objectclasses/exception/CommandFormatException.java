package objectclasses.exception;

/**
 * Represents invalid command formats inputted by the user.
 */
public class CommandFormatException extends LynxException {

    private CommandFormatException(String message) {
        super(message);
    }

    public static CommandFormatException invalidCommand() {
        return new CommandFormatException("Sorry, I have limited language capabilities. "
                + "Please try again or type \"help\" to access the command guide.");
    }

    public static CommandFormatException emptyCommand() {
        return new CommandFormatException("Command cannot be created from blank string.");
    }

    public static CommandFormatException invalidCommandStructure() {
        return new CommandFormatException("Command must be of appropriate format.");
    }

    public static CommandFormatException multipleDate() {
        return new CommandFormatException("Only one date can be supplied per search.");
    }

    public static CommandFormatException multiplePriority() {
        return new CommandFormatException("Only one priority can be supplied per search.");
    }

    public static CommandFormatException multipleId() {
        return new CommandFormatException("Only one id can be supplied per search.");
    }

    public static CommandFormatException invalidId() {
        return new CommandFormatException("Sorry, that isn't a valid ID.");
    }

    public static CommandFormatException invalidPriority() {
        return new CommandFormatException("Priority must be a non-negative integer.");
    }

}
