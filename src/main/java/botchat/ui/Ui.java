package botchat.ui;

import botchat.task.Task;
import botchat.task.TaskList;

import java.util.Scanner;

/**
 * Handle user interaction.
 */
public class Ui {
    /**
     * Scanner to read user input.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Divider between commands and outputs
     */
    public static final String LINE = "____________________________________";

    /**
     * Name of chatbot
     */
    public static final String NAME = "BotChat";

    private String output;

    /**
     * Adds the welcome message on app start to output.
     */
    public String displayWelcome() {
        output = LINE + System.lineSeparator() + "Hello! I'm " + NAME
                + System.lineSeparator() + "What can I do for you?" + System.lineSeparator()
                + LINE + System.lineSeparator();
        return output;
    }

    /**
     * Moves the scanner to read the next line.
     * @return the string in the file being read.
     */
    public String nextLine() {
        return scanner.nextLine();
    }


    /**
     * Adds the tasks in the list to output.
     * @param tasks list of tasks.
     */
    public void displayList(TaskList tasks) {
        output = LINE + System.lineSeparator() + "Here are the tasks in your list:"
                + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            output = output + (i + 1) + ". " + tasks.get(i).toString() + System.lineSeparator();
        }
        output = output + LINE;
    }

    /**
     * Adds message indicating task that is marked complete to output.
     * @param t the task that is marked as done
     */
    public void displayMark(Task t) {
        output = LINE + System.lineSeparator() + "Nice! I've marked this task as done:"
                + System.lineSeparator() + t + System.lineSeparator() + LINE;
    }

    /**
     * Adds message indicating task is marked as not completed to output.
     * @param t the task that is marked as not completed.
     */
    public void displayUnmark(Task t) {
        output = LINE + System.lineSeparator() + "OK, I've unmarked this task as done yet:"
                 + System.lineSeparator() + t + System.lineSeparator() + LINE;
    }

    /**
     * Adds message indicating which task is added to output.
     * @param t the task that is added.
     * @param tasks the list of tasks to add the task to.
     */
    public void displayAdd(Task t, TaskList tasks) {
        output = LINE + System.lineSeparator() + "Got it. I've added this task:"
                 + System.lineSeparator() + t + System.lineSeparator() + "Now you have "
                 + tasks.size() + " tasks in the list." + System.lineSeparator() + LINE;
    }

    /**
     * Adds message indicating which task is deleted to output.
     * @param t the task that is deleted.
     * @param tasks the list of tasks to delete from.
     */
    public void displayDelete(Task t, TaskList tasks) {
        output = LINE + System.lineSeparator() + "Noted. I've removed this task:"
                  + System.lineSeparator() + t + System.lineSeparator() + "Now you have "
                + tasks.size() + " tasks in the list." + System.lineSeparator() + LINE;
    }

    /**
     * Adds bye message when program ends to output.
     */
    public void displayBye() {
        output = LINE + System.lineSeparator() + "Bye. Hope to see you again soon!"
                 + System.lineSeparator() + LINE;
    }

    /**
     * Adds find command results to output.
     * @param tasks the list containing the find results.
     */
    public void displayFind(TaskList tasks) {
        output = LINE + System.lineSeparator() + "Here are the matching tasks in your list: "
                + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            output = output + (i + 1) + ". " + tasks.get(i).toString() + System.lineSeparator();
        }
        output = output + LINE;
    }

    /**
     * Displays the output
     * @return string output
     */
    public String out() {
        return output;
    }
}
