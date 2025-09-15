package chirp.exceptions;

/**
 * Represents exception for invalid attribute type
 */
public class InvalidAttributeException extends ChirpException {
    /**
     * Creates InvalidAttributeException
     *
     * @param data      Attribute value in input
     * @param attribute Attribute name
     * @param reason    Reason for invalid attribute
     */
    public InvalidAttributeException(String data, String attribute, String reason) {
        super(String.format("Attribute %s \"%s\" is invalid: %s", attribute, data, reason));
    }
}
