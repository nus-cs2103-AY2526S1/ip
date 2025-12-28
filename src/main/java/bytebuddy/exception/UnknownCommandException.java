package bytebuddy.exception;

public class UnknownCommandException extends ByteBuddyException {
    public UnknownCommandException() {
        super("Please enter 'help' to see the list of available commands.");
    }
}
