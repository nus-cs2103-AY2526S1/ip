import java.util.List;
import java.util.Scanner;

/// This class deals with interactions with the user.
///
/// @author Ravichandran Gokul
public class Ui {
    // Declare fields

    /**
     * Prints welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    What's up, what's up! I'm GoksChat");
        System.out.println("    How can I assist you today?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Good Day! Hope to see you again");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Reads user input.
     *
     * @return The user input as a String.
     */
    public String readUserInput() {
        // Create scanner to read input
        Scanner scanner = new Scanner(System.in);

        /* Return user input */
        return scanner.nextLine();
    }

    public void displayList(List<Task> listOfTasks) {
        int len = listOfTasks.size();

        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list!");
        for (int i = 1; i <= len; i++) {
            System.out.println("    " + i + "." + listOfTasks.get(i - 1));
        }
        System.out.println("    ____________________________________________________________");
    }

    public void markTaskMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Amazing! Insane productivity la keep it up. Marked it as done:");
        System.out.println("        " + task);
        System.out.println("    ____________________________________________________________");
    }

    public void unmarkTaskMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Boooo... Do better next time bro. Marked this as not done yet:");
        System.out.println("        " + task);
        System.out.println("    ____________________________________________________________");
    }

    public void deleteTaskMessage(Task task, List<Task> listOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Aights. I have deleted this task:");
        System.out.println("        " + task);
        System.out.println("    Now you have " + listOfTasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void addTaskMessage(Task task, List<Task> listOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Aights. I have added this task:");
        System.out.println("        " + task);
        System.out.println("    Now you have " + listOfTasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void exceptionMessage(Exception e) {
        System.out.println("    ____________________________________________________________");
        System.out.println(e.getMessage());
        System.out.println("    ____________________________________________________________");
    }
}
