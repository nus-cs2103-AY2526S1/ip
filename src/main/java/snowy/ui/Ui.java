package snowy.ui;

import java.util.Scanner;
import java.util.ArrayList;
import snowy.Parser;
import snowy.SnowyException;
import snowy.TaskList;
import snowy.task.Task;


/**
 * Creates a user interface object which can be used by the chatbot to interact with the user.
 * It handles all output printing and input reading.
 */
public class Ui {
    /**
     * Prints a line to sectioning segments.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints greeting message to the screen.
     */
    public void greeting() {
        System.out.println("Hello! I'm Snowy!");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints goodbye message to the screen.
     */
    public void bye() {
        System.out.println( "Bye! Hope to see you again :D");
    }

    /**
     * Prints all the tasks in the TaskList.
     * @param tasks
     */
    public void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        int count = tasks.getSize();
        for (int i = 0; i < count; i++) {
            Task cur = tasks.getTask(i);
            int num = i + 1;
            System.out.println(num + "." + cur);
        }
    }

    public void listFoundTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        int count = tasks.size();
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }


    public void addTask(TaskList tasks, Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " +
                tasks.getSize() +
                (tasks.getSize() == 1 ? " task" : " tasks") +
                " in the list.");
    }

    /**
     * Prints the error message for invalid input.
     * @param message
     */
    public void handleInvalidInput(String message) {
        System.out.println(message);
    }

    public void handleEmptyTaskList() {
        System.out.println("You don't have upcoming tasks right now! Yeah! :D");
    }

    public void mark(Task task) {
        System.out.println("Sure! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void unmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void delete(TaskList tasks, Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }


}
