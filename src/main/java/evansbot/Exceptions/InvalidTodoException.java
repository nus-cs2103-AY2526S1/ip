package evansbot.Exceptions;

/**
 * Represents an Invalid ToDo exception that occurs when an invalid Event is given.
 */
public class InvalidTodoException extends EvansBotException {
    /**
     * Constructs an Invalid ToDo Exception with the specified error message.
     */
    public InvalidTodoException() {
        super("A todo needs a description. Please add the description after typing todo. "
                + "for example; todo borrow a book");
    }
}
