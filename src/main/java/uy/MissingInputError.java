package uy;

/**
 * Exception thrown when an expected input part is missing (e.g. missing description).
 */
public class MissingInputError extends Exception{
    public MissingInputError(String message){
        super(message);
    }
}
