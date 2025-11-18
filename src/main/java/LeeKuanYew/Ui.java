package LeeKuanYew;

import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    /**
     * Prints custom intro message.
     *
     * Called when the program first starts.
     */
    public String showIntroMessage() {
        return """
                Hello! I'm Lee Kuan Yew, founding father of Singapore.
                How can I help my fellow countryman today?""";
    }

    /**
     * Reads the next line of user input from the scanner.
     *
     * @return the input string entered by the user
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Displays the given message to the standard output.
     *
     * @param message the message to display
     */
    public String showMessage(String message) {
        return message;
    }

}
