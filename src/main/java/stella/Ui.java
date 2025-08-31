package stella;

import java.util.Scanner;

/**
 * Responsible for dealing with the interactions with the user, including
 * greeting, responding to unexpected responses from user and farewell
 */
public class Ui {
    private Parser parser;
    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Greet and interact with user, while catching exceptions
     * when user provide unexpected reply
     */
    public void callInteraction() {
        Scanner scan = new Scanner(System.in);
        System.out.println(" > Hello! I am Stella");
        System.out.println(" > What can I do for you?");
        String userInput = scan.nextLine();

        while (!userInput.equals("bye")) {
            try {
                parser.identifyCommand(userInput);
                userInput = scan.nextLine();
            } catch (UnknownInstructionException e) {
                System.out.println(e.getMessage() + " is invalid. Type new message: ");
                userInput = scan.nextLine();
            } catch (IncompleteInstructionException e) {
                System.out.println(e.getMessage() + " is incomplete. Type new message: ");
                userInput = scan.nextLine();
            } catch (NullPointerException e) {
                System.out.println("Invalid value. Type new message: ");
                userInput = scan.nextLine();
            } catch (Exception e) {
                System.out.println("Did you key in the correct message? Type new message: ");
                userInput = scan.nextLine();
            }
        }
        System.out.println(" > Goodbye!");
    }
}




