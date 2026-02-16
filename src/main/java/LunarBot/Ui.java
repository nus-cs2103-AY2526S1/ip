package LunarBot;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "_________________________________________________";
    private Scanner scanner;
    private boolean isBye;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the loop termination flag
     * @return returns the loop termination flag
     */
    public boolean isBye() {
        return this.isBye;
    }

    public String greeting() {
        System.out.println("Hello from LunarBot!\n" + 
            "Nice to meet you! What can I do for you?\n" + LINE);
        return "Hello from LunarBot!\n" +
            "Nice to meet you! What can I do for you?\n";
    }

    public String goodbye() {
        this.isBye = true;
        System.out.println("Hope to see you again!");
        return "Hope to see you again!";
    }

    public String showMessage(String message) {
        System.out.println(message);
        return message;
    }

    /**
     * Reads user input
     * @return returns string inputted by user
     */
    public String readCommand() {
        System.out.println("Input: ");
        return scanner.nextLine();
    }
}
