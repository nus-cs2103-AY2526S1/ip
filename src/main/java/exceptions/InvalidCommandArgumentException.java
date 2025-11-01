package exceptions;

public class InvalidCommandArgumentException extends UserInputException {
    private String command;
    private String details = "";
    
    /**
     * Thrown when there are missing arguments to the command
     * @param command The String version of command in question
     */
    public InvalidCommandArgumentException(String command) {
        this.command = command;
    }
    
    public InvalidCommandArgumentException(String command, String details) {
        this.command = command;
        this.details = details;
    }
    
    @Override
    public String toString() {
        return String.format("WHAT DO YOU WANNA DO WITH THIS COMMAND: '%s'!! %s GRRAAAHH", this.command, this.details);
    }
}
