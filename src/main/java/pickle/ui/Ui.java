package pickle.ui;

import java.util.ArrayList;
import java.util.Scanner;

import pickle.task.Task;

/**
 * Handles all user interface inputs and outputs for Pickle.
 */
public class Ui {
    private static String line = "________________________________________________";
    public final Scanner sc = new Scanner(System.in);

    /**
     * Using scanner to read the command given by the user.
     * @return string that user inputted.
     */
    public String readComment() {
        return sc.nextLine().trim();
    }


    /**
     * Prints a greeting when Pickle is first ran.
     * For CLI
     */
    public void showWelcome() {
        System.out.println(line);
        System.out.println("Good Morning! I'm Pickle");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Prints a greeting when Pickle is first ran.
     * For GUI.
     */
    public String showWelcomeGui() {
        return "Good Morning! I'm Pickle\n" + "What can I do for you?";
    }

    /**
     * Prints a line to separate each command.
     * For CLI.
     */
    public void showLine() {
        System.out.println(line);
    }


    /**
     * Prints a goodbye message when the user terminates Pickle.
     * For CLI.
     */
    public void showBye() {
        System.out.println("Bye. Hope you have a nice day!");

    }

    /**
     * Prints a goodbye message when the user terminates Pickle.
     * For GUI.
     */
    public String showByeGui() {
        return "Bye. Hope you have a nice day!";

    }

    /**
     * Prints a message to show user what they are looking for.
     * For CLI.
     */
    public void showFind() {
        System.out.println("Is this what u are looking for??");
    }

    /**
     * Prints a message to show user what they are looking for.
     * For GUI.
     */
    public String showFindGui() {
        return "Is this what u are looking for??";
    }

    /**
     * Prints a message that is inputted.
     * For CLI.
     * @param msg Inputted message to print.
     */
    public void show(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints a message that is inputted.
     * For GUI.
     * @param msg Inputted message to print.
     */
    public String showGui(String msg) {
        return msg;
    }


    /**
     * Prints out an error message due to invalid input.
     * For CLI.
     * @param msg Specific message tied to each error.
     */
    public void showError(String msg) {
        System.out.println("Yikes!!!" + msg + "  Try Again!!");
    }

    /**
     * Prints out an error message due to invalid input.
     * For GUI.
     * @param msg Specific message tied to each error.
     */
    public String showErrorGui(String msg) {
        return "Yikes!!!" + msg + "  Try Again!!";
    }

    /**
     * Prints the list of tasks.
     * For CLI.
     * @param tasks List of tasks.
     */
    public void showList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("List empty! There is nothing to do....");
            return;
        }
        System.out.println("Here is your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Prints the list of tasks.
     * For GUI
     * @param tasks List of tasks.
     */
    public String showListGui(ArrayList<Task> tasks) {
        String output;
        if (tasks.isEmpty()) {
            output = "List empty! There is nothing to do....";
            return output;
        }
        output = "Here is your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output = output + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return output;
    }

    /**
     * Prints the task that is added.
     * For CLI.
     * @param t Task added.
     * @param size Size of list of task.
     */
    public void showTaskAdded(Task t, int size) {

        System.out.println("Aights. pickle.task.Task added:");
        System.out.println(t.toString());
        System.out.println("You got " + size + " tasks in the list.");

    }

    /**
     * Prints the task that is added.
     * For GUI.
     * @param t Task added.
     * @param size Size of list of task.
     */
    public String showTaskAddedGui(Task t, int size) {
        String output;
        output = "Aights. Task added:\n";
        output = output + t.toString() + "\n";
        output = output + "You got " + size + " tasks in the list.";
        return output;
    }

    /**
     * Prints the task deleted.
     * For CLI.
     * @param t Task deleted.
     * @param size Size of list of tasks.
     */
    public void showTaskDeleted(Task t, int size) {

        System.out.println("Ok! I've removed this task:");
        System.out.println(t.toString());
        System.out.println("You got " + size + " tasks in the list.");

    }

    /**
     * Prints the task deleted.
     * For GUI.
     * @param t Task deleted.
     * @param size Size of list of tasks.
     */
    public String showTaskDeletedGui(Task t, int size) {
        String output;
        output = "Ok! I've removed this task:\n";
        output = output + t.toString() + "\n";
        output = output + "You got " + size + " tasks in the list.";
        return output;
    }

    /**
     * Print task that is marked.
     * For CLI.
     * @param t Task to be marked.
     */
    public void showMarked(Task t) {

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());

    }

    /**
     * Print task that is marked.
     * For GUI.
     * @param t Task to be marked.
     */
    public String showMarkedGui(Task t) {
        String output = "Nice! I've marked this task as done:\n";
        output = output + t.toString();
        return output;
    }

    /**
     * Print task that is unmarked.
     * For CLI.
     * @param t Task to be unmarked.
     */
    public void showUnmarked(Task t) {

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());

    }

    /**
     * Print task that is unmarked.
     * For GUI.
     * @param t Task to be unmarked.
     */
    public String showUnmarkedGui(Task t) {
        String output = "Nice! I've marked this task as not done yet:\n";
        output = output + t.toString();
        return output;
    }
}

