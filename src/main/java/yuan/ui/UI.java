package yuan.ui;

import java.util.Scanner;

import yuan.task.Task;
import yuan.tasklist.TaskList;

/**
 * Handles user interactions by displaying messages and reading input from the console.
 */
public class UI {
    private final Scanner scanner = new Scanner(System.in);

    public void showLine() {
        System.out.println("    ____________________________________________");
    }

    /**
     * Displays welcome msg when program starts
     */
    public void showWelcome() {
        showLine();
        showMessage("Hello... I'm Yuan");
        showMessage("Why are you bothering me");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String msg) {
        System.out.println("    " + msg);
    }

    /**
     * prints marked task in terminal
     * @param task
     */
    public void showMark(Task task) {
        showLine();
        showMessage("Fine... I've marked this task as done:");
        showMessage(task.toString());
        showLine();
    }

    /**
     * prints unmarked task in terminal
     * @param task
     */
    public void showUnmark(Task task) {
        showLine();
        showMessage("Fine... I've marked this task as not done:");
        showMessage(task.toString());
        showLine();
    }

    /**
     * shows error message
     * @param msg
     */
    public void showError(String msg) {
        showLine();
        showMessage(msg);
        showLine();
    }

    /**
     * prints all tasks in the list
     * @param taskList
     */
    public void showTasks(TaskList taskList) {
        showLine();
        for (int i = 0; i < taskList.getSize(); i++) {
            showMessage((i + 1) + ". " + taskList.get(i));
        }
        showLine();
    }

    /**
     * prints newly added task
     * @param task
     * @param newSize
     */
    public void showAdded(Task task, int newSize) {
        showLine();
        showMessage("Alright, I've added this task:");
        showMessage(task.toString());
        showMessage("Now you have " + newSize + " tasks in the list");
        showLine();
    }

    /**
     * prints removed task
     * @param task
     * @param newSize
     */
    public void showRemoved(Task task, int newSize) {
        showLine();
        showMessage("Fine... I've removed this task:");
        showMessage(task.toString());
        showMessage("Now you have " + newSize + " tasks in the list");
        showLine();
    }

    public void showHelp() {
        showLine();
        showMessage("Yuan Chatbot Help:\n"
                + "- list : Show all tasks\n"
                + "- todo <desc> : Add a todo task\n"
                + "- deadline <desc> /by <d/M/yyyy> : Add a deadline\n"
                + "- event <desc> /from <d/M/yyyy> /to <d/M/yyyy> : Add an event\n"
                + "- mark <num> : Mark task as done\n"
                + "- unmark <num> : Unmark task\n"
                + "- delete <num> : Delete a task\n"
                + "- find <keyword> : Search tasks\n"
                + "- help : Show this help message\n"
                + "- bye : Exit Yuan\n");
        showLine();
    }

    /**
     * prints bye message
     */
    public void showBye() {
        showLine();
        showMessage("Bye. I don't wanna see you again");
        showLine();
    }

    /**
     * Below methods are for gui
     * @param tasks
     * @return
     */
    public String renderTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "Wow, zero tasks. Impressive laziness.";
        }
        StringBuilder sb = new StringBuilder("Here are your useless tasks:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String renderAdded(Task task, int size) {
        String thing;
        if (size == 1) {
            thing = "thing";
        } else {
            thing = "things";
        }
        return "Fine. I added this useless task: " + task
                + "\nNow you're stuck with " + size + " " + thing + " to suffer through.";
    }

    public String renderRemoved(Task task, int size) {
        return "Finally, you got rid of: " + task + "\nOnly " + size + " left to torture you.";
    }

    public String renderMark(Task task) {
        return "Congrats lil bro, you managed to do *something*: " + task;
    }

    public String renderUnmark(Task task) {
        return "Marked as not done: " + task;
    }

    /**
     * method to give all commands available for chatbot
     * @return
     */
    public String renderHelp() {
        return "Yuan Chatbot Help:\n"
                + "- list : Show all tasks\n"
                + "- todo <desc> : Add a todo task\n"
                + "- deadline <desc> /by <d/M/yyyy> : Add a deadline\n"
                + "- event <desc> /from <d/M/yyyy> /to <d/M/yyyy> : Add an event\n"
                + "- mark <num> : Mark task as done\n"
                + "- unmark <num> : Unmark task\n"
                + "- delete <num> : Delete a task\n"
                + "- find <keyword> : Search tasks\n"
                + "- help : Show this help message\n"
                + "- bye : Exit Yuan\n";
    }

    public String renderError(String message) {
        return message;
    }
}
