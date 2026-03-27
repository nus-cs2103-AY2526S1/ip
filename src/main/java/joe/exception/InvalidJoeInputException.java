package joe.exception;

public class InvalidJoeInputException extends RuntimeException {
    public InvalidJoeInputException() {
        super("I don't recognise that command!");
    }

    public InvalidJoeInputException(String command) {
        super("You need to provide me a description when using the " + command + " command!");
    }

    public InvalidJoeInputException(String command, String reason) {
        super(reason + " when using the " + command + " command!");
    }
}