package scribbles.exception;

/**
 * Provides exception when receiving an unknown command
 */
public class UnknownCommandException extends ScribblesException {
    public UnknownCommandException(String cmd) {
        super("[!] I do not know what you mean by '%s' @.@".formatted(cmd));
    }
}
