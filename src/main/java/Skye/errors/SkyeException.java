package Skye.errors;

public class SkyeException extends Exception {
    public SkyeException(String message) {
        super("Skye has encountered an error! " + message); // Differentiate Skye's internal exceptions
    }
}
