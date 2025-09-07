package objectclasses.exception;

/**
 * Represents errors detected by the program during file operations.
 */
public class LynxFileException extends LynxException {

    private LynxFileException(String message) {
        super(message);
    }

    /**
     * Returns an exception containing details of a failed <code>createFile</code> attempt.
     *
     * @param details <code>IOException</code> containing the details of the failure.
     * @return Details wrapped in a <code>LynxFileException</code>
     */
    public static LynxFileException createError(String details) {
        return new LynxFileException("⚠️ Warning: Lynx couldn't set up your data file!\n"
                + "Details: " + details + "\n"
                + "Your tasks may not be saved. Please check your file permissions or disk space.");
    }

    /**
     * Returns an exception containing details of a failed <code>readFromFile</code> attempt.
     *
     * @param details <code>IOException</code> containing the details of the failure.
     * @return Details wrapped in a <code>LynxFileException</code>
     */
    public static LynxFileException readError(String details) {
        return new LynxFileException("⚠️ Oops! Lynx couldn't read your data file.\n"
                + "Details: " + details + "\n"
                + "Your tasks could not be loaded. Starting with an empty list.");
    }

    /**
     * Returns an exception containing details of a failed <code>writeToFile</code> attempt.
     *
     * @param details <code>IOException</code> containing the details of the failure.
     * @return Details wrapped in a <code>LynxFileException</code>
     */
    public static LynxFileException writeError(String details) {
        return new LynxFileException("⚠️ Oops! Lynx couldn't save your tasks.\n"
                + "Details: " + details + "\n"
                + "Any changes made during this session may not be saved.");
    }

}
