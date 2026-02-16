package bambam;

import java.util.Scanner;

import bambam.task.Task;

/**
 * Handles the printing of messages when interacting with users.
 */
public class Messages {
    public String CHATBOT_NAME = "bambam.Bambam"; // chatbot name is constant
    Scanner scanner = new Scanner(System.in); // Scanner for inputs from users
    private TaskList taskList;

    public Messages(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints greetings to users.
     */
    public void printGreetings() {
        System.out.println("Hello! I'm " + CHATBOT_NAME + "\n" +
                "What can I do for you?\n");
    }

    /**
     * Prints exit message to users.
     */
    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Gets input from users.
     * @return The input of the user.
     */
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * Prints help message to users.
     */
    public void printHelpMessage(String helpMessage) {
        System.out.println(helpMessage);
    }

    /**
     * Prints error message.
     * @param error The error message.
     */
    public void printErrorMessage(String error) {
        System.out.println("BambamException: " + error);
    }
}
