package leo.display;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * The Messages class contains generic messages to be used across the application.
 */
public class Messages {
    private static String name = "Leo";
    private static String logo = " __\n"
            + "|  |     _____   _____\n"
            + "|  |    /  __ \\ /  _  \\\n"
            + "|  |___ |  ___/ | |_| |\n"
            + "|______|\\_____| \\_____/\n";

    /**
     * Outputs the initial message to the CLI to prompt for user input.
     */
    public static void greetings() throws IOException {
        String greeting = "Hello! I'm\n"
                + logo
                + "\n"
                + "What can I do for you?";
        System.out.println(greeting);
    }

    /**
     * Outputs the initial message to the GUI to prompt for user input.
     *
     * @return The initial message to prompt for user input.
     */
    public static String greetingsReturnOutput() {
        return "Hello! I'm "
                + name
                + "\n"
                + "What can I do for you?";
    }

    /**
     * Outputs the final message to the user before the application closes.
     */
    public static void goodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Outputs the final message to the user before the application closes.
     *
     * @return The final message.
     */
    public static String goodbyeReturnOutput() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Outputs a line to improve readability between messages.
     */
    public static void messageBreak() {
        StringBuilder msgBreak = new StringBuilder();

        for (int i = 0; i < 72; i++) {
            msgBreak.append("-");
        }

        System.out.println(msgBreak);
    }

    /**
     * Outputs the current number of tasks stored in the list.
     *
     * @param count Number of tasks stored in the list.
     */
    public static void taskCount(int count) {
        System.out.println(
                "Now you have "
                + count
                + " task(s) in the list."
        );
    }

    /**
     * Outputs the current number of tasks stored in the list.
     *
     * @param count Number of tasks stored in the list.
     * @return A formatted string.
     */
    public static String taskCountReturnOutput(int count) {
        return String.format("Now you have "
                + count
                + " task(s) in the list.");
    }

    /**
     * Outputs a confirmation after a task was successfully
     * added to the list.
     */
    public static void addTask() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Outputs a confirmation after a task was successfully
     * added to the list.
     *
     * @return A confirmation message.
     */
    public static String addTaskReturnOutput() {
        return "Got it. I've added this task:";
    }
}
