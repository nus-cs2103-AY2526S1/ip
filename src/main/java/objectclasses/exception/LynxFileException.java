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
                + "Your tasks may not be saved. Try \"reload\" or \"save\" to reattempt.");
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
                + "Your tasks could not be loaded. Starting with an empty list. Try \"reload\" to reattempt.");
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
                + "Any changes made during this session may not be saved. Try \"save\" to reattempt.");
    }

    /**
     * Returns an exception detailing the number of skipped lines during a failed <code>reload</code> attempt.
     *
     * @param errorCount Number of skipped lines.
     * @return Details wrapped in a <code>LynxFileException</code>
     */
    public static LynxFileException loadError(int errorCount) {
        return new LynxFileException("⚠️ Lynx skipped " + errorCount + " invalid task(s) during loading.");
    }

}
