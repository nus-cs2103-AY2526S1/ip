package uy;

/**
 * Exception thrown when an input command is not recognized.
 */
public class UnrecognizedCommandError extends Exception{
    public UnrecognizedCommandError(String message){
        super(message);
    }
}
