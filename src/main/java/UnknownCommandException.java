public class UnknownCommandException extends SamException {
    public UnknownCommandException() {
        super("🤔 Your words drift like clouds I cannot grasp. Perhaps try: list, todo, deadline, event, mark, unmark, delete, find, priority, or bye?");
    }
}
