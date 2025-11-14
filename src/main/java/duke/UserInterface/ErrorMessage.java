package duke.userinterface;

/**
 * Utility class for printing error messages in a formatted style.
 */
public class ErrorMessage {

    /**
     * Prints an error message in a formatted box.
     *
     * @param msg the error message to display
     */
    public static String printError(String msg) {
        return "    ____________________________________\n"
             + "     " + msg + "\n"
             + "    ____________________________________";
    }
}
