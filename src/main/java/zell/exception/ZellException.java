package zell.exception;

public class ZellException extends Exception{
    public ZellException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "An error occurred: " + getMessage();
    }
}
