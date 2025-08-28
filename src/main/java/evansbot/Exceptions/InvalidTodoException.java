package evansbot.Exceptions;

public class InvalidTodoException extends EvansBotException {
    public InvalidTodoException() {
        super("A todo needs a description. Please add the description after typing todo. for example; todo borrow a book");
    }
}
