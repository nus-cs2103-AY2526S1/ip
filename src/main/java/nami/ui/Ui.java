package nami.ui;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("____________________________________________________________\n");
        System.out.println(" Hello! I'm Nami\n");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________________________________________\n");
    }

    public String showGoodbye() {
        return
        "____________________________________________________________\n" +
        " Bye. Hope to see you again soon!\n" +
        "____________________________________________________________";
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String msg) {
        System.out.println("☹ OOPS!!! " + msg);
    }

    public void close() {
        scanner.close();
    }
}
