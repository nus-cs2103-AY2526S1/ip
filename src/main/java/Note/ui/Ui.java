package Note.ui;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Note");
        System.out.println("What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks!");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showGoodbye() {
        System.out.println("Bye! Hope to see you again soon!");
    }
}
