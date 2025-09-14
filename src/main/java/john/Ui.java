package john;

import java.util.ArrayList;

import john.tasks.Task;

/**
 * Class for handling text displays for the chatbot.
 */
public class Ui {
    /**
     * Greets the user on program start.
     */
    public String greet() {
        return "Hi I'm John. How can I help you?";
    }
    /**
     * Says goodbye to the user on program end.
     */
    public String endProgram() {
        return "Bye!";
    }
    /**
     * Displays the output of marking a task as done.
     */
    public String markTask(Task task) {
        return "I've marked this task as done:\n" + task;
    }
    /**
     * Displays the output of unmarking a task as done.
     */
    public String unMarkTask(Task task) {
        return "I've marked this task as not done yet:\n" + task;
    }
    /**
     * Lists the current tasks in the list.
     */
    public String listTasks(TaskList list) {
        if (list.size() == 0) {
            return "You currently have no tasks. Try adding a task.";
        }
        String res = "";
        for (int i = 1; i <= list.size(); i++) {
            res += "   " + i + ". " + list.get(i) + "\n";
        }
        return res;
    }
    /**
     * Displays the output of adding a task to the list.
     */
    public String addTask(Task task, TaskList list) {
        return "I've added:\n" + task + "\nYou now have " + list.size() + " tasks.";
    }
    /**
     * Displays the output of removing a task from the list.
     */
    public String deleteTask(Task task, TaskList list) {
        return "I've removed:\n" + task + "\nYou now have " + list.size() + " tasks.";
    }
    /**
     * Displays the list of tasks that matches the search.
     */
    public String findTasks(ArrayList<Integer> indices, TaskList list) {
        if (list.size() == 0) {
            return "You currently have no tasks. Try adding a task.";
        } else if (indices.size() == 0) {
            return "There are no tasks matching your search.";
        } else {
            String res = "";
            res += "Here are the matching tasks in your list:\n";
            for (int i : indices) {
                res += i + ". " + list.get(i) + "\n";
            }
            return res;
        }
    }
    /**
     * Displays error messages.
     */
    public String displayJohnException(JohnException e) {
        return e.getMessage();
    }
}
