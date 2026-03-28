package peanutbutter.ui;

import java.util.Scanner;

import peanutbutter.tasks.Task;
import peanutbutter.tasks.TaskList;

/**
 * Handles all user interactions including input and output.
 * <p>
 * Provides methods to display messages for adding, deleting,
 * marking, unmarking tasks, showing lists, and error messages.
 * Tracks the last message shown to the user.
 */
public class Ui {
    private Scanner sc;
    private String lastMessage;

    /**
     * Constructs a new Ui instance and initializes the input scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
        lastMessage = "";
    }

    /**
     * Displays a welcome message to the user.
     *
     * @return the welcome message
     */
    public String welcomeMessage() {
        String msg = "Hello! I'm JUIN. What can I do for you?";
        setLastMessage(msg);
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void byeMessage() {
        String msg = "Bye. Hope to see you again soon!";
        setLastMessage(msg);
        System.out.println(msg);
    }

    /**
     * Reads a command from user input.
     *
     * @return the raw user input as a string
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a message when tasks are added to the task list.
     *
     * @param taskList the current TaskList
     * @param tasks one or more tasks that were added
     */
    public void addTaskMessage(TaskList taskList, Task... tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append("Added: ").append(task).append("\n");
        }
        sb.append("Now you have ").append(taskList.size()).append(" tasks in the list!");
        String msg = sb.toString().trim();
        setLastMessage(msg);
        System.out.println(msg);
    }

    /**
     * Displays a message when a task is deleted from the task list.
     *
     * @param taskList the current TaskList
     * @param task the task that was removed
     */
    public void deleteTaskMessage(TaskList taskList, Task task) {
        String msg = "Removed: " + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list!";
        setLastMessage(msg);
        System.out.println(msg);
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task the task that was marked
     */
    public void markTaskMessage(Task task) {
        String msg = "Marked as done: " + task.toString();
        setLastMessage(msg);
        System.out.println(msg);
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task the task that was unmarked
     */
    public void unmarkTaskMessage(Task task) {
        String msg = "Marked as not done: " + task.toString();
        setLastMessage(msg);
        System.out.println(msg);
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList the TaskList to display
     */
    public void showListMessage(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        if (taskList.size() < 1) {
            sb.append("No tasks found.");
        } else {
            sb.append("Tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                sb.append(i + 1).append(". ").append(taskList.getTasks().get(i)).append("\n");
            }
        }
        String msg = sb.toString().trim();
        setLastMessage(msg);
        System.out.println(msg);
    }

    /**
     * Displays all tasks in the task list that contain a specific keyword.
     *
     * @param taskList the TaskList to search
     * @param key the keyword to filter tasks by
     */
    public void showKeyListMessage(TaskList taskList, String key) {
        StringBuilder sb = new StringBuilder();
        if (taskList.size() < 1) {
            sb.append("No tasks found.");
        } else {
            sb.append("Tasks containing \"").append(key).append("\":\n");
            for (int i = 0; i < taskList.size(); i++) {
                sb.append(i + 1).append(". ").append(taskList.getTasks().get(i)).append("\n");
            }
        }
        String msg = sb.toString().trim();
        setLastMessage(msg);
        System.out.println(msg);
    }

    /**
     * Displays all tasks in the task list that are due soon.
     *
     * @param taskList the TaskList containing due tasks
     * @return the tasks that are due soon.
     */
    public String showDueListMessage(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        if (taskList.size() < 1) {
            sb.append("No tasks due soon.");
        } else {
            sb.append("Tasks due in 1 day").append("\n");
            for (int i = 0; i < taskList.size(); i++) {
                sb.append(i + 1).append(". ").append(taskList.getTasks().get(i)).append("\n");
            }
        }
        String msg = sb.toString().trim();
        setLastMessage(msg);
        System.out.println(msg);

        return msg;
    }

    /**
     * Displays an error message.
     *
     * @param msg the error message to display
     */
    public void errorMessage(String msg) {
        setLastMessage(msg);
        System.out.println(msg);
    }

    /**
     * Returns the last message that was displayed to the user.
     *
     * @return the last message string
     */
    public String getLastMessage() {
        return lastMessage;
    }

    /**
     * Sets the last message internally.
     *
     * @param msg the message to store
     */
    private void setLastMessage(String msg) {
        lastMessage = msg;
    }
}
