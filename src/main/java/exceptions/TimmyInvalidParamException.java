package exceptions;

public class TimmyInvalidParamException extends Exception {
    public TimmyInvalidParamException() {}

    public TimmyInvalidParamException(String message) {
        super(message);
    }
}
