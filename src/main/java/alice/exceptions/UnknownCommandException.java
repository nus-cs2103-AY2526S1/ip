package alice.exceptions;

public class UnknownCommandException extends AliceException {

    public UnknownCommandException() {
        super("Sorry, I don't know what that means. Please enter a task: Todo, Deadline, Event, and add a description");
    }

}
