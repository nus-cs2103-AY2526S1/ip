package sheares;
import java.util.Scanner;

/**
 * User interface that prints error and other messages to user
 */
public class Ui {

    private final Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * helper method to print separator line
     */
    public void showLine() {
        System.out.println("    _____________________________");
    }

    /**
     * prints welcome message
     */
    public void showWelcome() {
        String first = "    Hello! I'm Sheares, your personal chatbot!";
        String second = "    What can i do for you?";
        this.showLine();
        System.out.println(first);
        System.out.println(second);
        this.showLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Shows message to user if no existing data (first time)
     */
    public void showLoadingError() {
        this.showLine();
        System.out.println("    This is your first time using this chatbot");
        this.showLine();
    }


    /**
     * reads next user input from scanner
     * @return
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }
}
