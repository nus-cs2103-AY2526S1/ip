package ming.ui;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import ming.model.Task;

/**
 * Handles interactions with the user via the command line.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays a line separator after a response for better readability.
     */
    public String showLine() {
        return "____________________________________________________________";
    }

    public String showLoadingError() {
        return "Loading error!";
    }

    public String showWelcome() {
        return "Hello! I'm Ming\nWhat can I do for you?";
    }

    /**
     * Reads a command from the user.
     */
    public String readCommand() {
        if (!scanner.hasNextLine()) {
            return "";
        }
        return scanner.nextLine();
    }

    public String showExit() {
        return "Bye!";
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String showList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "There are no tasks to show!";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");

        IntStream.rangeClosed(1, tasks.size())
                .forEach(i -> sb.append(i).append(". ").append(tasks.get(i - 1)).append("\n"));

        return sb.toString();
    }

    public String showMark(Task task) {
        return "I've marked this task as done:\n" + task;
    }

    public String showUnmark(Task task) {
        return "I've unmarked this task:\n" + task;
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The new size of the task list after deletion.
     */
    public String showDelete(Task task, int size) {
        return "I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays the list of tasks that match a search query.
     *
     * @param foundTasks The list of matching tasks.
     */
    public String showFind(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            return "No matching tasks found.";
        }
        System.out.println(foundTasks);
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        IntStream.rangeClosed(1, foundTasks.size())
                .forEach(i -> sb.append(i).append(". ").append(foundTasks.get(i - 1)).append("\n"));

        System.out.println(sb.toString());
        return sb.toString();
    }

    public String showError(String message) {
        return message;
    }

    public String showAdd(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }
}
