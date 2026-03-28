package exceptions;

public class EmptyDescriptionException extends MarkExceptions {

    public EmptyDescriptionException(String task) {
        super("OOPS!!! The description of a " + task + " cannot be empty.");
    }
}
