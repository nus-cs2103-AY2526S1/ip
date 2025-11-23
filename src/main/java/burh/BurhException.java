package burh;

/**
 * Represents an exception specific to the Burh application.
 * Extends {@code RuntimeException} so it is unchecked.
 */
public class BurhException extends RuntimeException {
    public enum ErrorType {
        INVALID_COMMAND("Burh, I don't understand that command."),
        MISSING_DESCRIPTION("Burh, you need to provide a description."),
        INVALID_INDEX("Burh, that's not a valid task number."),
        MISSING_INDEX("Burh, you need to specify a task number."),
        FILE_ERROR("Burh, I had trouble accessing the save file."),
        CORRUPTED_DATA("Burh, the save file is corrupted."),
        INVALID_DATE_FORMAT("Burh, use a valid date format (yyyy-MM-dd)."),
        INVALID_EVENT_FORMAT("Burh, use: event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>"),
        INVALID_DEADLINE_FORMAT("Burh, use: deadline <description> /by <yyyy-MM-dd>"),
        INVALID_DATE_RANGE("Burh, the end date must be after the start date."),
        DUPLICATE_TASK("Burh, you already have this task."),
        UNKNOWN_ERROR("Burh, something went wrong.");

        private final String message;

        ErrorType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    private final ErrorType errorType;

    /**
     * Constructs a {@code BurhException} with the specified error type.
     *
     * @param errorType the type of error that occurred
     */
    public BurhException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    /**
     * Constructs a {@code BurhException} with the specified error type and additional details.
     *
     * @param errorType the type of error that occurred
     * @param details additional details about the error
     */
    public BurhException(ErrorType errorType, String details) {
        super(String.format("%s %s", errorType.getMessage(), details));
        this.errorType = errorType;
    }

    /**
     * Returns the error type of this exception.
     *
     * @return the error type
     */
    public ErrorType getErrorType() {
        return errorType;
    }
}
