package evansbot.Exceptions;

public class InvalidCommandException extends EvansBotException {
    public InvalidCommandException() {
        super("""
                Sorry! I don't know what this comment is supposed to be...\s
                Available commands: todo (description) , event (description) (from) (to), deadline (description) (by)
                Type 'bye' to cancel the chat!""");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
