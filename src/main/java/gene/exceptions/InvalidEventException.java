package gene.exceptions;

public class InvalidEventException extends CreateTaskException {
    public InvalidEventException() {
        super("Invalid event format. Use: event <description> /from <start> /to <end>");
    }
}
