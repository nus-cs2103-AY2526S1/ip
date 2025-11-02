package buddy.exception;

/** Thrown when a command word is not recognized. */

public class UnknownCommandException extends BuddyException {
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}