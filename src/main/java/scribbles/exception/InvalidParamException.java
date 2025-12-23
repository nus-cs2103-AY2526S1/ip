package scribbles.exception;

/**
 * Provides exception when receiving missing or invalid parameters
 */
public class InvalidParamException extends ScribblesException {

    /**
     * Formulates the error message based on the required parameters
     *
     * @param params The parameters required for the specific command
     */
    public InvalidParamException(String... params) {
        super("[!] You need to include the following parameter(s) for this command: %s (._.)"
                .formatted(String.join(", ", params)));
    }
}
