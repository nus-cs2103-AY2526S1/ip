package exceptions;

public class NoSpaceAfterCommandException extends UserInputException {
    private String message;

    /**
     * Thrown when there is no space after a command
     * @param command Command in String format
     */
    public NoSpaceAfterCommandException(String command) {
        this.message = String.format("PUT A SPACE AFTER '%s' DANGIT!!!!", command);
    }

    @Override
    public String toString() {
        return this.message;
    }
}
