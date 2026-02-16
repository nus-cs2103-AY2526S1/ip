package command;

import ui.Lmbd;

/**
 * An abstract base class for all commands in the Lmbd application. It defines
 * the common structure and behavior for commands, including their command word,
 * required arguments, and help text. Concrete command classes must implement
 * the {@code run} method to define their specific execution logic.
 */
public abstract class Command {
    private String cmd;
    private String helpText;
    private int numRequiredArgs;

    /**
     * Command constructor
     *
     * @param cmd
     *            The word to call this command
     *
     * @param numRequiredArgs
     *            The minimum number of required arguments
     *
     * @param helpText
     *            A short help text to be displayed with the help command
     */
    public Command(String cmd, int numRequiredArgs, String helpText) {
        this.cmd = cmd;
        this.numRequiredArgs = numRequiredArgs;
        this.helpText = helpText;
    }

    /**
     * Runs this command using the arguments
     *
     * @param lmbd
     *            The Lmbd object that received this command
     * @param args
     *            An array of arguments following the command, separated by spaces
     */
    public String call(Lmbd lmbd, String[] args) {
        if (args.length < numRequiredArgs) {

            return String.format("%s command requires at least %d args", cmd, numRequiredArgs);
        } else {
            return run(lmbd, args);
        }
    }

    abstract String run(Lmbd lmbd, String[] args);

    /** Returns the help text for this command */
    public String getHelpText() {
        return helpText;
    }

    /** Returns the string that calls this command */
    public String getCmd() {
        return cmd;
    }
}
