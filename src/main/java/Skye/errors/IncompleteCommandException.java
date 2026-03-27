package Skye.errors;

public class IncompleteCommandException extends SkyeException{
    public IncompleteCommandException(String command) {
        super(String.format("The %s command is incomplete!", command));
    }
}
