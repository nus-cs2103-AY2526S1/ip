public class EmptyDescriptionException extends SamException {
    public EmptyDescriptionException(String cmd) {
        super("OOPS!!! The description of a " + cmd + " cannot be empty.");
    }
}