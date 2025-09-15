package bobby.ui;

import java.util.Scanner;

import bobby.exception.BobbyException;
import bobby.parser.Parser;

/**
 * Ui class that interacts with the user by sending messages
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * takes a string and converts it into indented and separated sout
     *
     * @param message to be converted
     */
    public static void showMessage(String message) {
        System.out.println("    ________________________________________________________________");
        String[] lines = message.split("\\R");
        for (String line : lines) {
            System.out.println("    " + line);
        }
        System.out.println("    ________________________________________________________________");
    }

    /**
     * @return welcome String
     */
    public static String outputWelcome() {
        return "Hello I'm Bobby!\nWhat can I do for you?";
    }

    /**
     * @return goodbye String
     */
    public static String outputGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * prints the welcome message
     */
    public static void showWelcome() {
        showMessage(outputWelcome());
    }

    /**
     * prints the exit message
     */
    public static void showGoodbye() {
        showMessage(outputGoodbye());
    }

    public void run(Parser parser) {
        String input;
        boolean isBye = false;

        showWelcome();
        while (!isBye) {
            try {
                input = scanner.nextLine();
                isBye = parser.checkBye(input);
                Ui.showMessage(parser.processCommand(input));
            } catch (BobbyException e) {
                this.showMessage(e.getMessage());
            }
        }
    }
}
