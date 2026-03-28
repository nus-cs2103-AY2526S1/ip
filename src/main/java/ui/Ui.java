package ui;

import java.util.ArrayList;
import java.util.Scanner;

import taskstuff.Task;
import taskstuff.TaskList;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner sc = new Scanner(System.in);
    
    /**
     * Shows the welcome banner and greeting message to the user.
     */
    public String showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm DAWAE \uD83D\uDE06\nWhat can I do for you?");
        System.out.println(LINE);
        return "Hello! I'm DAWAE \uD83D\uDE06\nWhat can I do for you?";
    }
    
    /**
     * Shows a confirmation that a task has been added.
     *
     * @param t the task that was added.
     * @param count the total number of tasks after the addition.
     */
    public String showAdded(Task t, int count) {
        System.out.println("Ogi. I've added dis task:\n  " + t);
        System.out.println("Now you have " + count + " tasks in the list.");
        return "Ogi. I've added dis task:\n  " + t + "\nNow you have " + count + " tasks in the list.";
    }
    
    /**
     * Shows a confirmation that a task has been deleted.
     *
     * @param t the task that was deleted.
     * @param count the total number of tasks after the deletion.
     */
    public String showDeleted(Task t, int count) {
        System.out.println("Ogi. I've removed dis task:\n  " + t);
        System.out.println("Now u have " + count + " tasks in dis list.");
        return "Ogi. I've removed dis task:\n  " + t + "\nNow u have " + count + " tasks in dis list.";
    }
    
    /**
     * Shows all tasks in the current list with their indices.
     *
     * @param tasks the task list to display.
     */
    public String showList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("Your task list is empty.");
            return "Your task list is empty.";
        }
        String ret = "";
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
            ret += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return ret;
    }
    
    /**
     * Shows a confirmation that the task  has been marked.
     *
     * @param t the task list to display.
     */
    public String showMarked(Task t, int count) {
        System.out.println("Ogi. I've marked dis task:\n  " + t);
        return "Ogi. I've marked dis task:\n  " + t;
    }
    
    /**
     * Shows a confirmation that the task  has been unmarked.
     *
     * @param t the task list to display.
     */
    public String showUnmarked(Task t, int count) {
        System.out.println("Ogi. I've unmarked dis task:\n  " + t);
        return "Ogi. I've unmarked dis task:\n  " + t;
    }
    /**
     * Shows goodbye message
     */
    public String showBye() {
        sc.close();
        System.out.println("Bye. \uD83D\uDC4B Hope to see you again soon! \uD83D\uDD25\n");
        return "Bye. \uD83D\uDC4B Hope to see you again soon! \uD83D\uDD25\n";
        
    }
    
    /**
     * Prints a horizontal divider line to separate sections of output.
     */
    public void showLine() {
        System.out.println(LINE);
    }
    public String showLoadingError() {
        System.out.println("Loading Error! Having sum issues internally, sad. Restart maybe? " +
                "\uD83D\uDE36\u200D\uD83C\uDF2B\uFE0F");
        return "Loading Error! Having sum issues internally, sad. Restart maybe? " +
                "\uD83D\uDE36\u200D\uD83C\uDF2B\uFE0F";
    }
    
    /**
     * Shows a formatted error message to the user.
     *
     * @param message the error message to display.
     * @return
     */
    public String showError(String message) {
        System.out.println("bruh Error: " + message);
        return "bruh Error: " + message;
    }
    
    /**
     * Shows the tasks that match a search query in their original order.
     * Displays an empty-state message if there are no matches.
     *
     * @param found the list of matching tasks.
     */
    public String showFound(ArrayList<Task> found) {
        if (found == null || found.isEmpty()) {
            System.out.println("UHm. No matching tasks found. \uD83D\uDC40");
            return "UHm. No matching tasks found. \uD83D\uDC40";
        }
        String ret = "Yo. Here are da matching tasks in your list:\n";
        System.out.println("Yo. Here are da matching tasks in your list:");
        for (int i = 0; i < found.size(); i++) {
            System.out.println((i + 1) + ". " + found.get(i));
            ret += (i + 1) + ". " + found.get(i) + "\n";
        }
        return ret;
    }
    
    public String showArchive(String archiveFile) {
        System.out.println("All tasks archived to " + archiveFile + ". Task list now empty. hehe");
        return "All tasks archived to " + archiveFile + ". Task list now empty. hehe";
    }
}
