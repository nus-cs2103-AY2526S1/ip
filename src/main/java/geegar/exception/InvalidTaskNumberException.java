package geegar.exception;

/**
 * Thrown to indicate the task number shown does not exist
 */
public class InvalidTaskNumberException extends GeegarException {

    /**
     * Takes in the task Number and throws an error message to indicate task number doesnt exist
     * @param taskNumber
     */
    public InvalidTaskNumberException(String taskNumber) {

        super("Task Number " + taskNumber + " does not exist!");
    }
}