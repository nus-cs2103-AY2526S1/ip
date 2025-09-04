public class EmptyDescriptionException extends SamException {
    public EmptyDescriptionException(final String cmd) {
        super("OOPS!!! The description of a " + cmd + " cannot be empty.");
    }
}