package exceptions;

public class JackException extends Exception {

    // Constructor that accepts a custom error message
    public JackException(String message) {
        super(message);  // Pass the message to the parent Exception class
    }
}
