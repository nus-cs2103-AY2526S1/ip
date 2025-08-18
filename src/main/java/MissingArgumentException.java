public class MissingArgumentException extends LynxException {
    public MissingArgumentException(String command) {
        super("Missing argument for command: " + command + ". Please provide the required details.");
    }
}