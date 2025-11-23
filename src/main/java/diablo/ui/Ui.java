package diablo.ui;

import diablo.exception.DiabloException;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Acts as an interim between the user and the chatbot. Responds to user interactions with inbuilt messages.
 */
public class Ui {
    private final Scanner SCANNER;
    private final String BOT_NAME = "Diablo";
    private final String GREETING = "\tHello! I'm " + BOT_NAME + "\n\tWhat can I do for you?";
    private final String EXIT = "\tBye. Hope to see you again soon!";

    public Ui() {
        this.SCANNER = new Scanner(System.in);
    }

    /**
     * Greets the user with an inbuilt greeting.
     */
    public void greetUser() {
        System.out.println(GREETING);
    }


    /**
     * Says goodbye to the user with an inbuilt message.
     */
    public void sayBye() {
        System.out.println(EXIT);
    }


    /**
     * Prints out all current tasks that are stored in the chatbot currently.
     *
     * @param tasks ArrayList of string representation of tasks.
     */
    public void showList(ArrayList<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\tThere are no list items yet!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + ":" + tasks.get(i));
            }
        }
    }


    /**
     * Prints an error message related to reading data from the storage file.
     */
    public void printFileErrorMessage() {
        System.out.println("\tAn error occured when reading data. Try again!");
    }


    /**
     * Prints an error message in the chatbot given by a DiabloException.
     *
     * @param e An exception containing a message to be delivered to the user.
     */
    public void printErrorMessage(DiabloException e) {
        System.out.println("\t" + e.getMessage());
    }


    /**
     * Prints an error message in the case that the input does not meet chatbot requirements.
     */
    public void printInvalidInputMessage() {
        System.out.println("\t" + "I don't know what that means!!!");
    }


    /**
     * Prints a message to the user telling them that a specific task was added to the task list.
     *
     * @param task String representation of a Task object.
     */
    public void addTask(String task) {
        System.out.println("\tGot it. I've added this task:\n\t\t" + task);
    }


    /**
     * Prints a message to the user telling them a specific task in the task list was marked complete.
     *
     * @param task String representation of a Task object.
     */
    public void markTask(String task) {
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + task);
    }


    /**
     * Prints a message to the user telling them a specific task was deleted from the task list.
     *
     * @param task String representation of a Task object.
     */
    public void deleteTask(String task) {
        System.out.println("\t Alright, I've removed this task:\n\t\t" + task);
    }

}
