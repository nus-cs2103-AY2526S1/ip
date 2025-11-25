package hachiware;

/**
 * The {@code Ui} class handles all user interface interactions for Hachiware.
 * It provides methods to display welcome and exit messages, print lines,
 * print error messages, to ensure consistency in display.
 */
public class Ui {

    /**
     * Prints the welcome message along with a cat logo
     */
    public String welcome() {
        String logo =
                " /\\_/\\  \n"
                        + "( o.o )  \n"
                        + " > ^ <   \n";
        return logo;
    }

    /**
     * Prints the exit message when the program terminates.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!\n";

    }

    /**
     * Prints a horizontal line for formatting.
     */
    public void printLine() {
        System.out.println("-----------------------------------------------");
    }

    public String showError(String message) {
        return message + "\n";
    }

    public String showAddTaskMessage(Task task, int totalTasks) {
        return "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + totalTasks + " tasks in the list.";
    }

    public String showDeleteTaskMessage(Task removed, int totalTasks) {
        return "Noted. I've removed this task:\n" + removed + "\n"
                + "Now you have " + totalTasks + " tasks in the list.";
    }
}
