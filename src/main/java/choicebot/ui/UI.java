package choicebot.ui;

import java.util.ArrayList;
import java.util.Scanner;

import choicebot.task.Task;
import choicebot.task.TaskList;

/**
 * Handles all user interactions with ChoiceBot.
 * Provides methods for displaying messages, errors, tasks lists and feedback to the user.
 */
public class UI {
    private final Scanner scanner;

    /**
     * Constructs a UI object with a new scanner instance.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a message to the user.
     */
    public String displayMessage(String message) {
        return message;
    }

    /**
     * Greets the user with a welcome message when ChoiceBot first initiates.
     */
    public static String welcome() {
        return "Hello, Welcome to ChoiceBot!\n"
                + "What would you like to do?\n";
    }

    /**
     * Displays a message after deleting a task.
     */
    public static String deleteTaskMessage(Task task, TaskList tasks) {
        return "Noted. I have removed the following task:\n"
                + ("\t" + task) + "\n" + displayCountMessage(tasks);
    }

    /**
     * Displays the current number of tasks in the task list.
     * @param tasks TaskList used in the current instance
     */
    public static String displayCountMessage(TaskList tasks) {
        int count = tasks.getCount();
        assert count >= 0 : "Task count should be positive";
        if (count == 1) {
            return ("Now you have " + count + " task in the list.");
        } else {
            return ("Now you have " + count + " tasks in the list.");
        }
    }

    /**
     * Displays a message after adding a task.
     *
     * @param task Task that has been added.
     * @param tasks TaskList that is used in current instance.
     */
    public String addTaskMessage(Task task, TaskList tasks) {
        return displayMessage("Got it. I've added this task: \n")
            + displayMessage("\t" + task) + "\n"
            + displayCountMessage(tasks);
    }

    /**
     * Displays a message after unmarking a task.
     */
    public String unmarkTaskMessage(Task task) {
        return displayMessage("Ok, I've unmarked the following task for you: \n")
                + displayMessage("\t" + task) + "\n";
    }

    /**
     * Displays a message after marking a task.
     */
    public String markTaskMessage(Task task) {
        return displayMessage("Nice! I've marked this task as done: \n")
                + displayMessage("\t" + task) + "\n";
    }

    /**
     * Displays the full list of task in order, with the number labelled before each task.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public String displayList(ArrayList<Task> taskList, boolean isMatchingList) {
        StringBuilder sb = new StringBuilder();
        if (isMatchingList) {
            sb.append("Here are the matching tasks in your list:\n");
        } else {
            sb.append("Here are the tasks in your list:\n");
        }
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            sb.append("\t").append(i + 1).append(".").append(currentTask).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays the exit message when the conversation with ChoiceBot ends.
     */
    public String exitMessage() {
        return "Thanks for stopping by! See you again!";
    }

    /**
     * Displays an error message if no tasks match the given keyword.
     */
    public String displayNoMatchingTasks() {
        return displayMessage("Sorry. There are no tasks matching your keyword \n");
    }

    /**
     * Returns the user text input as a String.
     */
    public String readUserInput() {
        return scanner.nextLine();
    }
}
