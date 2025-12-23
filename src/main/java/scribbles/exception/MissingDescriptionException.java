package scribbles.exception;

/**
 * Provides exception when receiving missing description
 */
public class MissingDescriptionException extends ScribblesException {
    public MissingDescriptionException() {
        super("[!] Your task is missing a description! D:");
    }
}
