package ui;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private final String line = "____________________________________________________________\n";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        System.out.println(line + "hi! i'm rainy! :D\nwhat can i do for u?\n" + line);
    }

    public void showBye() {
        System.out.println(line + " bai bai! see u again >_<!\n" + line);
    }

    public void showLine() {
        System.out.println(line);
    }

    public void showError(String message) {
        System.out.println(line + message + "\n" + line);
    }

    public void close() {
        sc.close();
    }
}
