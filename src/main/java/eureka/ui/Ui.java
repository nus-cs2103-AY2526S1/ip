package eureka.ui;

import java.util.Scanner;

import eureka.task.TaskList;

/**
 * Handles interactions between the user and the application.
 * <p>
 * The {@code Ui} class is responsible for displaying messages,
 * reading user input, and formatting output to the console.
 * It provides feedback for actions such as loading errors,
 * adding tasks, showing task lists, and exiting the program.
 */
public class Ui {

    private static String input;

    /**
     * Constructs a {@code Ui} instance.
     */
    public Ui() {
    }

    /**
     * Displays an error message when task history cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("There is an error loading the tasks history.");
    }

    /**
     * Displays a horizontal line divider for formatting.
     */
    public void showLine() {
        System.out.println("_____________________________");
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public void showWelcome() {
        String chatbotName = "eureka";
        System.out.println("_____________________________");
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param error the error message to display
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Reads a command from user input.
     *
     * @return the command string entered by the user
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input;
    }

    /**
     * Displays the farewell message when the program exits.
     */
    public void bye() {
        System.out.println("_____________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_____________________________");
    }

    /**
     * Displays a confirmation message after a task has been added,
     * showing the most recently added task and the updated count.
     *
     * @param taskList the task list containing the newly added task
     */
    public void addTaskMessage(TaskList taskList) {
        System.out.println("_____________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getTask(taskList.getCount() - 1).toString());
        System.out.println("Now you have " + taskList.getCount() + " tasks in the list.");
        System.out.println("_____________________________");
    }
}
