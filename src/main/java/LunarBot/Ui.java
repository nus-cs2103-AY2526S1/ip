package LunarBot;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "__________________________________________";
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

    /**
     * Displays greeting message
     */
    public void greeting() {
        System.out.println("Hello from LunarBot.LunarBot!\n");
        System.out.println("Nice to meet you! What can I do for you?\n" + LINE);
    }

    /**
     * Displays goodbye message and sets loop termination flag
     */
    public void goodbye() {
        System.out.println("Hope to see you soon!\n");
        this.isBye = true;
    }

    /**
     * Shows message
     * @param message message to be shown
     */
    public void showMessage(String message) {
        System.out.println(message);
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
