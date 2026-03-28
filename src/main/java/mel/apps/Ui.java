package mel.apps;

import java.util.Scanner;

import mel.exceptions.MelException;

/**
 * The UI class represents the interface from which the user interacts with and deals
 * with displaying the feedback to the users.
 */

public class Ui {

    private Scanner sc;
    private String nextMessage;

    /**
     * Constructor for Ui
     *
     * @param sc
     */
    public Ui(Scanner sc) {
        this.sc = sc;

    }

    /**
     * Returns the next input from the user
     *
     *
     * @return String
     * @throws MelException
     */
    public String readCommand() throws MelException {
        System.out.println("Next command:");
        if (sc.hasNext()) {
            return sc.nextLine();

        } else {
            throw new MelException("No more input");

        }

    }

    /**
     * Prints out the output with line spacing
     *
     * @param input
     *
     */
    public void printOut(String input) {
        String line = "_______________________________________________________\n";
        System.out.println(line + " " + input + "\n" + line);
        this.nextMessage = input;

    }


    public String giveNextMessage() {
        return this.nextMessage;

    }

    /**
     * Prints out greeting message
     */
    public void showGreeting() {
        String greeting = "Hello! I'm Mel\n "
                + "What can I do for you?";

        printOut(greeting);

    }

    /**
     * Prints out the exit message
     */
    public void showExit() {
        String exitMessage = " Bye! Hope to see you again soon!";
        printOut(exitMessage);
    }
}
