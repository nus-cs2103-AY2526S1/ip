package chirp.exceptions;

/**
 * Represents exception for missing attribute
 */
public class TaskEmptyAttributeException extends ChirpException {
    /**
     * Creates TaskEmptyAttributeException
     *
     * @param taskType  What type of task requires this attribute
     * @param attribute Attribute tag
     */
    public TaskEmptyAttributeException(String taskType, String attribute) {
        super("The " + attribute + " attribute of a " + taskType + " task cannot be empty!");
    }
}
