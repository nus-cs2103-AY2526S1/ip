package jerome.ui;

import java.util.List;
import java.util.Scanner;

import jerome.task.Task;

/**
 * Handles user interface interactions and displays messages to the user.
 * Responsible for returning the welcome message, task list, errors, and more.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Returns the welcome message when the application starts.
     */
    public static String welcomeText() {
        return "Wassup, I'm Jerome!\nWhat can I do for you?";

    }

    /**
     * Returns the goodbye message when the application exits.
     */
    public String goodbyeText() {
        return "Bye gng. Dap a homie up before you go will ya!\n";
    }

    /**
     * Returns an error message with a custom message.
     */
    public void errorText(String msg) {
        System.out.println(msg);
    }

    /**
     * Returns a success message when a task is added.
     */
    public String successfulAddText(Task t, int size) {
        return "Gotchu mahomes! I added:\n" + t + "\nThere are now " + size + " tasks!\n";
    }

    /**
     * Returns a success message when a task is deleted.
     */
    public String successfulDeleteText(Task t, int size) {
        return "Alright matey! I have removed this task:\n" + t + "\nThere are now " + size + " task(s)!\n";
    }

    /**
     * Returns the task list to GUI.
     */
    public String taskListText(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "You trippin...there aint no tasks";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are your tasks broski:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    public String successfulMarkText(Task task) {
        return "Sheesh what a productive monkey you are."
            + "\n I have marked the following task as complete!\n" + task + "\n";
    }

    public String successfulUnmarkText(Task task) {
        return "How do you un-finish a task? Anyways I've unmarked the following task...\n" + task + "\n";
    }

    /**
     * Reads the user's command from the console.
     */
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        }
        return "bye";
    }

    /**
     * Closes the scanner object used for reading user input.
     */
    public void close() {
        sc.close();
    }

    /**
     * Displays an error message when a specific error occurs.
     */
    public void showError(String msg) {
        errorText(msg);
    }
}
