package alice.exceptions;

/**
 * Thrown when a date/time string does not match the expected format.
 */
public class InvalidDateFormatException extends AliceException {
  public InvalidDateFormatException(String message) {
    super(message);
  }
}
