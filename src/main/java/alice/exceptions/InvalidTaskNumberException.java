package alice.exceptions;

public class InvalidTaskNumberException extends AliceException {

    public InvalidTaskNumberException() {
        super("That task number doesn't exist or is invalid.");
    }

}
