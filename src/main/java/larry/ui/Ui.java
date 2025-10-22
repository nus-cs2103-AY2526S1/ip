package larry.ui;

import larry.model.Task;

import java.util.List;

/**
 * Handles all user-visible console messages and prompts.
 * Centralizing messages keeps text-UI tests stable.
 */
public class Ui {

    /** Prints the greeting banner shown at program start. */
    public void showGreeting() {
        System.out.println(" Hello! I'm Larry");
        System.out.println(" What can I do for you?");
    }

    /** Prints the exit message shown at program end. */
    public void showExit() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /** Prints the numbered list of tasks in the given order. */
    public void showList(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /** Announces that a task was added and shows the new total size. */
    public void showAdded(Task t, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + size + " task" + (size == 1 ? "" : "s") + " in the list.");
    }

    /** Announces that a task was marked done. */
    public void showMarked(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t);
    }

    /** Announces that a task was marked not done. */
    public void showUnmarked(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
    }

    /** Announces that a task was deleted and shows the new total size. */
    public void showDeleted(Task t, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + size + " task" + (size == 1 ? "" : "s") + " in the list.");
    }

    /** Prints tasks that matched a find query; shows a friendly message if none. */
    public void showFound(java.util.List<larry.model.Task> tasks, String keyword) {
        if (tasks.isEmpty()) {
            System.out.println("No matches for: " + keyword);
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        int i = 1;
        for (larry.model.Task t : tasks) {
            System.out.println(i + "." + t);
            i++;
        }
    }

    /** Prints a short usage guide for available commands (with aliases). */
    public void showHelp() {
        System.out.println("Commands (aliases in brackets):");
        System.out.println("  list (ls)");
        System.out.println("  todo <desc> (t)");
        System.out.println("  deadline <desc> /by <when> (dl)");
        System.out.println("  event <desc> /from <start> /to <end> (ev)");
        System.out.println("  mark <index> (mk)");
        System.out.println("  unmark <index> (um)");
        System.out.println("  delete <index> (del)");
        System.out.println("  find <keyword> (f)");
        System.out.println("  help (h)");
        System.out.println("  bye (q)");
    }

    /** Prints a one-line error message without terminating the app. */
    public void showError(String msg) {
        System.out.println(msg != null ? msg : "An error occurred.");
    }
}
