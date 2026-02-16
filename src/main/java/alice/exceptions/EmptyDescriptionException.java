package alice.exceptions;

public class EmptyDescriptionException extends AliceException {

    public EmptyDescriptionException(String taskType) {
        super("Oops! The description of a " + taskType + " cannot be empty. Please add description to the task!");
    }

}
