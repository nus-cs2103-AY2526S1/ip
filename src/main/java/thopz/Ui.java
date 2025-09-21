package thopz;

import java.util.List;
import java.util.Scanner;


/**
 * User interface class for reading and understanding various
 * user inputs and outputs.
 */

public class Ui {

    private final Scanner sc;

    /**
     * Constructs user interface class which takes in user input
     */

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Read user input
     */

    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Welcome message
     */

    public String showGreetings() {
        return "Wassup wassup! Life ok? Vibes ok? Family ok? "
                + "I am thopz your virtual chatbot. "
                + "How may I help you?";
    }

    /**
     * Goodbye message
     */

    public String showGoodbye() {
        return "Chaoz. See you !";
    }


    public String showError(String msg) {
        return ("OOPS!!!!!" + msg);
    }

    /**
     * Message for addition of task
     */

    public String showAddition(Task task, int size) {
        return"Orrite, I have added " + task
                + "\nYou have " + size + " tasks";
    }

    /**
     * Message for removal of task
     */

    public String showRemoval(Task task, int size) {
        return "Solidd! I have deleted this task as done \n" + task
                + "\nYou have " + size + " tasks";
    }

    /**
     * Message to show tasks filtered on keywords
     */


    public String showFind(List<String> matchedTasks) {
        if (matchedTasks.isEmpty()) {
            return "No tasks match your keyword.";
        }

        StringBuilder sb = new StringBuilder("These are the tasks with matching key words:\n");
        for (int i = 0; i < matchedTasks.size(); i++) {
            sb.append(i + 1).append(". ").append(matchedTasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }


    /**
     * Message for marking of task
     */


    public String showMarked(Task task) {
        return ("Solidd! I have marked this task as done \n" + task);
    }

    /**
     * Message for error in loading of saved task
     */


    public String showLoadingError() {
       return ("No saved tasks");
    }

    /**
     * Message for showing the list of tasks
     * Empty list case is also handled
     */

    public String showList(List<Task> ls) {

        if (ls.isEmpty()) {
            return ("You have no tasks in your list");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ls.size(); i++) {
                sb.append(i + 1).append(". ").append(ls.get(i)).append("\n");
            }
            return sb.toString().trim();
        }
    }


}
