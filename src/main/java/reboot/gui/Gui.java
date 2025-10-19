package reboot.gui;

import reboot.TaskList;
import reboot.task.Task;

/**
 * Represents a GUI that will be interacting with user.
 */
public class Gui {

    /**
     * Prints the welcome message to the user.
     */
    public String showWelcome() {
        return "Hello! I'm Reboot the best chatbot\n"
                + "What can the best chatbot do for you?";
    }

    /**
     * Prints the specified message to the user.
     * @param message Message to be printed to user.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Prints the current tasklist of the user.
     * @param tasks Tasklist to be displayed to user.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task t = tasks.get(i - 1);
            String taskInfo =  "\n" + Integer.toString(i) + ". " + t.toString();
            sb.append(taskInfo);
        }
        return sb.toString();
    }

    /**
     * Prints the bye message to the user.
     */
    public String showBye() {
        return "Bye. Rate 5 stars.";
    }

    /**
     * Prints the specified error message to the user.
     * @param message Error message to be printed.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays the results of search in ui.
     * @param tasks Tasklist which contains the results of the search.
     */
    public String showSearchResult(TaskList tasks, String keyword) {
        if (tasks.isEmpty()) {
            return "No tasks found that contains the keyword: " + keyword;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the results of searching: ");
        sb.append(keyword);
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task t = tasks.get(i - 1);
            String searchResult = "\n" + i + ". " + t;
            sb.append(searchResult);
        }
        return sb.toString();
    }
}
