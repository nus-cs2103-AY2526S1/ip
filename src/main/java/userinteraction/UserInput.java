package userinteraction;

import java.util.Scanner;

import parse.Parser;
import storetasks.TaskList;

/**
 * This class takes user input and
 * manages the interactions with the user. This is mainly
 * for interaction for before the GUI.
 */

public class UserInput {

    /**
     * This method maintains the main function and the starting
     * phrases from the chatbot.
     */
    public static void starting() {
        UserInput uI = new UserInput();
        Scanner object = new Scanner(System.in);
        System.out.println(uI.greet());
        String userWords = object.nextLine();
        TaskList text = new TaskList();
        Parser i = new Parser();
        while (!uI.exitingTheLoop(userWords)) {
            try {
                System.out.println(i.validityOfWords(userWords, text));
            } catch (Exception e) {
                System.out.println(e);
            }
            userWords = object.nextLine();
        }
        System.out.println(uI.ending());
    }

    /**
     * greets the user
     * @return string greeting the user
     */
    public String greet() {
        return "Hello! I'm chatbot.Jocelyn. What can I do for you?";
    }

    /**
     * ending message if user says "bye"
     * @return goodbye message
     */
    public String ending() {
        return "Bye, I hope to see you soon!";
    }

    /**
     * checker to see when the input loop stops
     * @param input input from user
     * @return whether it is time to stop the loop
     */
    public boolean exitingTheLoop(String input) {
        if (input == null || input.equals("") || input.equals("bye")) {
            return true;
        }
        return false;
    }


}





