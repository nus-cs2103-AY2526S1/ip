package joobot.main;
/**
 * Represents an exception that can happen in JooBot application.
 * Provides different types of errors with user-friendly messages.
 */
public class JooException extends Exception {

    /**
     * Different categories of possible errors that can occur in JooBot.
     */
    public enum ErrorType {
        EMPTY_INPUT,
        EMPTY_LIST,
        NO_INDEX,
        OUT_OF_INDEX,
        MISSING_DESC,
        MISSING_BY_DATE,
        MISSING_FROM_TO,
        DEFAULT
    }

    private final ErrorType type;

    /**
     * Constructs a new {@code JooException} with the specified error type.
     *
     * @param type the type of error that occurred
     */
    public JooException(ErrorType type) {
        super(getErrorMessage(type));
        this.type = type;
    }

    /**
     * Returns the type of error for this exception.
     *
     * @return the {@link ErrorType} associated with this exception
     */
    public ErrorType getType() {
        return type;
    }

    /**
     * Maps each {@link ErrorType} to a corresponding human-readable error message.
     *
     * @param type the error type
     * @return the error message
     */
    private static String getErrorMessage(ErrorType type) {
        return switch (type) {
        case EMPTY_INPUT ->
            """
                Error! Empty input is not allowed.
                Type one of these as inputs:
                 - todo
                 - deadline
                 - event
                 - list
                 - find
                 - delete
                 - sort
                 - mark
                 - unmark
                 - bye""";

        case EMPTY_LIST ->
            """
                You have an empty to do list!""";

        case NO_INDEX, OUT_OF_INDEX ->
            """
                Please enter an index that is of valid range.""";

        case MISSING_DESC ->
            """
                Please enter a description of the task.""";

        case MISSING_BY_DATE ->
            """
                The deadline must have a /by <text> OR /by <D/M/YYYY HHMM>.""";

        case MISSING_FROM_TO -> """
                The deadline must have a /from <text> OR /from <D/M/YYYY HHMM> and"""
                + """
                /to <text> OR /to <D/M/YYYY HHMM>.""";

        case DEFAULT ->
            """
                Please enter a valid command.
                Type one of these as inputs:
                 - todo
                 - deadline
                 - event
                 - list
                 - find
                 - delete
                 - sort
                 - mark
                 - unmark
                 - bye""";

        default -> "This is a different error."; // should not reach this stage
        };
    }
}
