package yappy.ui;

import java.util.ArrayList;
import java.util.Scanner;

import yappy.task.Task;
/**
 * The <code>UI</code> class handles all interactions with the user.
 * This class ensures a consistent user interface during the program's execution.
 */
public class Ui {

    private final Scanner input;

    /**
     * Constructs a new <code>Ui</code> instance with a <code>Scanner</code>
     * for reading user input.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     *
     */
    public void showLoadingError() {
        System.out.println("\t There was an error loading from the storage file!");
    }

    /**
     * Displays the current tasks in the task list.
     *
     * @param tasks The Arraylist of tasks to be displayed
     */
    public String showTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            String next = ("\n\t" + index + "." + tasks.get(i).toString());
            sb.append(next);
        }
        return sb.toString();
    }
}
