package chash.ui;

/** Handles user input/output for CHASH. */
public abstract class ChashUi {
    //Input

    //For the CLI to read user input
    public abstract String readLine();

    //Output

    public abstract void printMsg(String txt);

    //For the GUI to show user output
    public abstract void printUserInput(String txt);

    //Error

    public abstract void printErr(String txt);

    //CHASH default messages

    /** Prints the CHASH welcome message. */
    public void printWelcome() {
        printMsg("Hello! I'm Crysis Heir Activity Sentre Hepdesk (CHASH)."
                + "\n" + "What can I do for you?");
    }
}
