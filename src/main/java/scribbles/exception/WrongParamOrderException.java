package scribbles.exception;

/**
 * Provides exception when the order of parameters is wrong
 */
public class WrongParamOrderException extends ScribblesException {
    public WrongParamOrderException(String... params) {
        super("[!] It should be %s".formatted(String.join(" -> ", params)));
    }
}
