package bytebuddy.exception;

public class MissingTimeException extends ByteBuddyException {
    public MissingTimeException() {
        super("Please add a time");
    }
}
