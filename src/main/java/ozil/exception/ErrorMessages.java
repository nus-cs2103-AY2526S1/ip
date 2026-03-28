package ozil.exception;

/**
 * Static class, containing a lot of the commonly displayed ERROR messages in Ozil chatbot
 */
public class ErrorMessages {
    /**
     * Returns an error message with the error logo.
     * @param errorMessage Message telling the user their mistake.
     * @return
     */
    public static String errorMessage(String errorMessage) {
        return errorMessage;

    }

    /**
     * Error message for when selected task does not exist
     * @return
     */
    public static String wrongMarkNumber() {
        return "You need to select a valid task number bro.\n";
    }

    /**
     * Error message when user does not give any instructions
     */
    public static String nonsenseError() {
        return "What's that? No idea what you mean, cannot help you there mate\n";
    }

    /**
     * Error message when user does not give the description of a task
     * @param tasktype The type of task the user wanted to do
     */
    public static String taskDescriptionError(String tasktype) {
        return "Your description of a " + tasktype + " cannot be blank :(\n";
    }

    /**
     * Error message for when events time format is incorrect.
     * @return Error message
     */
    public static String eventTaskTimeError() {
        return "Your event must have a time format /from <start-time> /to <end-time>\n";
    }

    /**
     * Error message for deadline tasks missing the deadline.
     * @return Error message
     */
    public static String deadlineTaskTimeError() {
        return "Your deadline must have a time format /by <deadline>. \n";
    }
}
