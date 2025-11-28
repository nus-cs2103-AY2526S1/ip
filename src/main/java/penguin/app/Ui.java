package penguin.app;

import java.util.List;
import java.util.Scanner;

import penguin.tasks.Task;

/**
 * The class in charge of dealing with user interactions
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input
     * @return User Input
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner
     */
    public void close() {
        scanner.close();
    }

    /**
     * Pretty prints the border lines
     * @return border line
     */
    public String printLine() {
        return "____________________________________________________________";
    }

    /**
     * Greets the user when the app starts
     * @return greeting
     */
    public String sayWelcome() {
        return "Hello! I'm Penguin" + " What can I do for you?";
    }

    /**
     * Says goodbye to the user when the app closes
     * @return goodbye
     */
    public String sayGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Renders list of tasks
     * @return list of tasks
     */
    public String printList(List<Task> tasks, String msg) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append(String.format("%d.%s\n", i + 1, task));
        }
        return msg + "\n" + sb;
    }

    /**
     * Notifies the user that the task has been marked
     * @param task Task to be marked
     * @return String saying which task was marked
     */
    public String markTask(Task task) {
        return "Nice! I've marked this task as done:" + "\n"
                + String.format("%s %s", task.getStatusIcon(), task.getDescription());
    }

    /**
     * Notifies the user that the task has been unmarked
     * @param task Task to be unmarked
     * @return String saying which task was marked
     */
    public String unmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:" + "\n"
                + String.format("%s %s", task.getStatusIcon(), task.getDescription());
    }

    /**
     * Notifies the user that the task has been deleted
     * @param task Task to be deleted
     * @param totalTasks Number of tasks left in the list
     */
    public String deleteTask(int totalTasks, Task task) {
        assert task != null : "task should not be null";
        assert totalTasks >= 0 : "totalTasks should not be negative";

        return "Noted. I've removed this task:" + "\n" + task
                + "Now you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Notifies user that the task has been added
     * @param totalTasks New number of tasks in the list
     * @param task Task to be added
     */
    public String addTask(int totalTasks, Task task) {
        assert task != null : "task should not be null";
        assert totalTasks >= 0 : "totalTasks should not be negative";

        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Notifies user of error
     * @param msg Error messsage to be printed
     */
    public String printErrorMessage(String msg) {
        return msg;
    }
}
