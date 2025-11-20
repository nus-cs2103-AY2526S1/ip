package ui;

import tasks.Task;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    // Show a welcome message to the user (console)
    public void showWelcome() {
        System.out.println(getWelcomeMessage());
    }

    // Return the welcome message as a String for gui use
    public String getWelcomeMessage() {
        return "Hello! I'm Jack" + System.lineSeparator() + "What can I do for you?";
    }

    // Read user input
    public String readCommand() {
        return scanner.nextLine();
    }

    // Show an error message

    public String showError(String message) {
        return message;
    }

    public String showAdd(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    public String showDelete(Task task, int size) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    public String showMark(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    public String showUnmark(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    public String showList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");

        IntStream.rangeClosed(1, tasks.size())
                .forEach(i -> sb.append(i).append(". ").append(tasks.get(i - 1)).append("\n"));

        return sb.toString();
    }

    public String showFind(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            return "No tasks found matching the keyword.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        IntStream.rangeClosed(1, foundTasks.size())
                .forEach(i -> sb.append(i).append(". ").append(foundTasks.get(i - 1)).append("\n"));

        return sb.toString();
    }

    /**
     * Shows the list of deadlines sorted by due date, in their current positions in the list.
     * Only Deadline tasks are shown, in the order they appear after sorting.
     *
     * @param tasks The list of all tasks (only deadlines are shown).
     * @return The formatted list of sorted deadlines as a string.
     */
    public String showSortedDeadlines(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are your deadlines sorted by due date (in their current positions):\n");
        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof tasks.Deadline) {
                sb.append((i + 1)).append(". ").append(task).append("\n");
                count++;
            }
        }
        if (count == 1) {
            sb.append("No deadlines found.");
        }
        return sb.toString().trim();
    }


    // Show the exit message
    public String showExitMessage() {
        return "Goodbye! Hope to see you again soon!";
    }

}
