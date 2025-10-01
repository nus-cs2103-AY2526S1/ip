package arn;
import java.util.Scanner;
public class Ui {
    protected Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.print("-> ");
        return sc.nextLine();
    }

    public void displayGreet() {
        System.out.println("Hello! I'm Arn");
        System.out.println("What can I do for you?");
        System.out.print("\n");
    }

    public void displayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void displayMsg(String msg) {
        System.out.println(msg);
    }

    public void close() {
        sc.close();
    }
}
