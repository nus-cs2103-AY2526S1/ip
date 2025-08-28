import java.util.Scanner;

/**
 * This class deals with interactions with the user.
 *
 * @author Ravichandran Gokul
 */

public class Ui {
    // Declare fields

    /**
     * Prints welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    What's up, what's up! I'm GoksChat");
        System.out.println("    How can I assist you today?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Good Day! Hope to see you again");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Reads user input.
     *
     * @return The user input as a String.
     */
    public String readUserInput() {
        // Create scanner to read input
        Scanner scanner = new Scanner(System.in);

        /* Return user input */
        return scanner.nextLine();
    }
}
