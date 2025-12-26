package gene.exceptions;

public class EmptyTodoException extends CreateTaskException {
    public EmptyTodoException() {
        super("The description of a todo cannot be empty.");
    }
}
