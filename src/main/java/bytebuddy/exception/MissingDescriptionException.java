package bytebuddy.exception;

public class MissingDescriptionException extends ByteBuddyException {
    public MissingDescriptionException(String taskType) {
        super("The description of a " + taskType +  " cannot be empty.");
    }
}
