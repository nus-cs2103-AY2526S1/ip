package Skye.errors;

public class UnknownCommandException extends SkyeException{
    public UnknownCommandException() {
        super("This Command is not known!");
    }
}
