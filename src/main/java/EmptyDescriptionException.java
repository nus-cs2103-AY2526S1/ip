public class EmptyDescriptionException extends SamException {
    public EmptyDescriptionException(final String cmd) {
        super("🌫️ Like morning mist, your " + cmd + " lacks substance. Please provide a description to give it form.");
    }
}
