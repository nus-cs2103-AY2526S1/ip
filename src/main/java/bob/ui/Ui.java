package bob.ui;

import java.util.Scanner;

import bob.task.Task;

/**
 * Class responsible for handling Ui
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor for Ui
     */
    public Ui() {

    }

    /**
     * Method for printing line
     */
    public void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Method for reading user input
     * 
     * @return user input in the form of string
     */
    public String readInput() {
        String ret = "";
        ret = sc.nextLine();
        return ret;
    }

    /**
     * Method for constructing marked task string
     * 
     * @param t Marked task for constructing string
     * @return String to be displayed on GUI
     */
    public String getMarkString(Task t) {
        String constructString = "";
        constructString += "Tasked marked as done: ";
        constructString += "\n";
        constructString += t;
        return constructString;
    }

    /**
     * Method for constructing unmarked task string
     * 
     * @param t Unmarked task for constructing string
     * @return String to be displayed on GUI
     */
    public String getUnmarkString(Task t) {
        String constructString = "";
        constructString += "Tasked unmarked as not done: ";
        constructString += "\n";
        constructString += t;
        return constructString;
    }

    /**
     * Method for constructing task string
     * 
     * @param t Task for constructing string
     * @return String to be displayed on GUI
     */
    public String getAddTaskString(Task t) {
        String constructString = "";
        constructString += "Task added: ";
        constructString += "\n";
        constructString += t;
        return constructString;
    }

    /**
     * Method for returning welcome message
     * 
     * @return Welcome string
     */
    public String getWelcomeString() {
        return "Hello! I'm Bob! What can I do for you?";
    }

    /**
     * Method for returning bye message
     * 
     * @return Bye string
     */
    public String getByeString() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Method for returning bot usage string
     * 
     * @return Usage string
     */
    public String getUsageString() {
        String s = """
                Commands:
                1. todo <task desc> (Add todo task)
                2. deadline <task desc> /by <time> (Add deadline)
                3. event <task desc> /from <start> /to <end> (Add event)
                4. list (list all your task)
                5. mark <index> (Mark task as done)
                6. unmark <index> (Unmark task as not done)
                7. bye (Exit)
                8. find <query>
                """;
        return s;
    }

    /**
     * Method for printing deleted task
     * 
     * @param t Deleted task to be printed
     * 
     * @return String to be printed on GUI
     */
    public String getDeleteString(Task t) {
        String constructString = "";
        constructString += "Deleted task";
        constructString += "\n";
        constructString += t;
        return constructString;
    }

    /**
     * Method for printing attempt to add duplicate task
     * 
     * @param t duplicate task to be printed
     * @return String to be printed on GUI
     */
    public String getDuplicateString(Task t) {
        String construcString = "";
        construcString += "Could not add task: ";
        construcString += t.toString();
        construcString += " due to duplicates";
        return construcString;
    }

}
