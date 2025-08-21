/**
 * Skeletal Version of GoksChat
 */

import java.util.Scanner;

public class GoksChat {
    public static void printWelcomeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    What's up, what's up! I'm GoksChat");
        System.out.println("    How can I assist you today?");
        System.out.println("    ____________________________________________________________");
    }

    public static void printGoodbyeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Good Day! Hope to see you again");
        System.out.println("    ____________________________________________________________");
    }

    public static void printUserInput(String userInput) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + userInput);
        System.out.println("    ____________________________________________________________");
    }

    public static String getUserInput() {
        // Create scanner to read input
        Scanner scanner = new Scanner(System.in);

        // Get user input
        String userInput = scanner.nextLine();

        // Return user input
        return userInput;
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        // Get user input
        String userInput = getUserInput();

        // Print according to what the user input is
        while (!userInput.equals("bye")) {
            printUserInput(userInput);

            // Get user input again
            userInput = getUserInput();
        }

        printGoodbyeMessage();
    }
}
