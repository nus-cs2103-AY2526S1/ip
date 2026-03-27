package fish.ui;

import java.util.Scanner;

public class Ui {

    private final Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        System.out.println("Greetings from __><(((º>");
    }

    public void showLoadingError() {
        System.out.println("Loading error");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showExit() {
        System.out.println("Thanks see you next time~");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void printIn(String s) {
        System.out.println(s);
    }
}
