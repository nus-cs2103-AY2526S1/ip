package chash.ui;

/** Handles user input/output for CHASH. */
public abstract class ChashUi {
    //Input

    /**
     * Reads a single line of user input.
     *
     * @return Input line
     */
    public abstract String readLine();

    //Output

    /**
     * Prints a newline separated message to STDOUT with auto indent and wrapped by separators.
     *
     * @param txt Message text
     */
    public abstract void printMsg(String txt);

    //E.g. for the GUI to show user output
    public abstract void printUserInput(String txt);

    //Error

    /**
     * Prints an error message to standard error, wrapped with separators.
     *
     * @param txt Error text
     */
    public abstract void printErr(String txt);

    //CHASH default messages

    /** Prints the CHASH welcome message. */
    public void printWelcome() {
        printMsg("Hello! I'm Crysis Heir Activity Sentre Hepdesk (CHASH)." +
                "\n" + "What can I do for you?");
    }
}
