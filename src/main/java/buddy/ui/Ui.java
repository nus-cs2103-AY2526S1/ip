package buddy.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm Buddy");
        System.out.println(" What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showMessage(String msg) {
        showLine();
        System.out.println(msg);
        showLine();
    }

    public void showError(String msg) {
        showLine();
        System.out.println("Error: " + msg);
        showLine();
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    public void showLoadingError() {
        showError("Failed to load previous tasks. Starting with an empty list.");
    }

    public void showGoodbye() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    public void close() {
        scanner.close();
    }
}
