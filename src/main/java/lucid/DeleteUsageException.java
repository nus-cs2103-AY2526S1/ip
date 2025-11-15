package lucid;

/**
 * Exception resulting from incorrect usage of the delete command
 */
public class DeleteUsageException extends LucidException {
    public DeleteUsageException() {
        super("You need to tell me the number of the task you want me to delete!!");
    }
}
