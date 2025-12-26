package gene.exceptions;

public class TaskOutOfRangeException extends CreateTaskException {
    public TaskOutOfRangeException() {
        super("Task number is out of range. Please enter a valid task number.");
    }
}
