/**
 * Skeletal Version of GoksChat
 */

import java.util.Scanner;

public class GoksChat {
    private static InputProcessor inputProcessor = new InputProcessor();
    private static Ui ui = new Ui();

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

    public static String getUserInput() {
        // Create scanner to read input
        Scanner scanner = new Scanner(System.in);

        // Get user input
        String userInput = scanner.nextLine();

        // Return user input
        return userInput;
    }

    public static void main(String[] args) throws InvalidPromptException, TodoException {
        ui.printWelcomeMessage();

        // Get user input
        String userInput = ui.readUserInput();

        // Print according to what the user input is
        while (!userInput.equals("bye")) {
            inputProcessor.processInput(userInput);

            // Get user input again
            userInput = ui.readUserInput();
        }

        ui.printGoodbyeMessage();
    }
}
