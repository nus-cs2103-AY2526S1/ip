package mochi;

/**
 * Extends MochiException to handle ToDo specific exceptions
 */
public class ToDoException extends MochiException {
    public ToDoException() {
        super();
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "\n" + "The description of a todo task cannot be empty.");
    }
}
