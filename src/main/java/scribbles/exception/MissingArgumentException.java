package scribbles.exception;

/**
 * Provides exception when receiving missing arguments
 */
public class MissingArgumentException extends ScribblesException {
    public MissingArgumentException() {
        super("[!] Your command is missing certain arguments...");
    }
}
