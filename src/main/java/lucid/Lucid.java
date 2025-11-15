package lucid;

import java.util.Scanner;

/**
 * Main class, entry point of the progrma
 */
public class Lucid {

    /**
     * Method that starts the chatbot
     */
    // Adapted from JavaFX tutorial
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            String reply = getResponseForGui(userInput);
            System.out.println(reply);
            if (userInput.equals("bye")) {
                scanner.close();
                return;
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponseForGui(String input) {
        return Parser.parse(input);
    }

    public static void main(String[] args) {
        new Lucid().start();
    }

}
