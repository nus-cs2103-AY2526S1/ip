package tom;

import java.util.ArrayList;

/**
 * Prints messages displayed to the user in the shell.
 */
public class Ui {
    /**
     * Prints initial greeting.
     */
    public static String greet() {
        return "Hello! I'm Tom.\nWhat can I do for you?";
    }

    /**
     * Prints final goodbye message.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints message to be displayed when a new task is added to the TaskList.
     * @param task Task to be added.
     * @param ls Current TaskList.
     */
    public static String add(Task task, TaskList ls) {
        String response = "Got it. I've added this task:";
        response += "\n  " + task.toString();
        return response + "\nNow you have " + ls.getTasks().size() + " tasks in the list.";
    }

    /**
     * Prints the current list of tasks.
     * @param ls Current TaskList.
     */
    public static String list(ArrayList<Task> ls) {
        String response = "Here are the tasks in your list:";
        for (int i = 0; i < ls.size(); i++) {
            response += "\n" + (i + 1) + "." + ls.get(i).toString();
        }
        return response;
    }

    /**
     * Prints message to be displayed when a task is deleted from the TaskList.
     * @param task Task to be deleted.
     * @param ls Current TaskList.
     */
    public static String delete(Task task, ArrayList<Task> ls) {
        String response = "Noted. I've removed this task:";
        response += "\n  " + task.toString();
        return response + "\nNow you have " + ls.size() + " tasks in the list.";
    }

    /**
     * Prints message to be displayed when a task is marked.
     * @param task Task to be marked.
     */
    public static String mark(Task task) {
        String response = "Nice! I've marked this task as done:";
        return response + "\n  [X] " + task.description;
    }

    /**
     * Prints message to be displayed when a task is unmarked.
     * @param task Task to be unmarked.
     */
    public static String unmark(Task task) {
        String response = "OK, I've marked this task as not done yet:";
        return response + "\n  [ ] " + task.description;
    }

    /**
     * Prints message to be displayed when a task is prioritised.
     * @param task Task to be prioritised.
     */
    public static String prioritise(Task task) {
        String response = "OK, I've given this task high priority:";
        return response + "\n  [ ] " + task.description;
    }

    /**
     * Prints message to be displayed when tasks are found.
     * @param ls List of tasks found.
     */
    public static String find(ArrayList<Task> ls) {
        String response = "Here are the matching tasks in your list:";
        for (int i = 0; i < ls.size(); i++) {
            response += "\n" + (i + 1) + "." + ls.get(i).toString();
        }
        return response;
    }
}
