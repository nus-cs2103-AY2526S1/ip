package ip.ui;

import ip.tasks.Task;
import ip.tasks.TaskList;

/**
 * Contains all messages shown to the user
 */
public class Ui {

    public Ui() {
    }

    /**
     * Shows welcome message
     *
     * @return The welcome message
     */
    public String showWelcomeMsg() {
        return "I'm Squiddy, forced to be text in your terminal.\n"
                + "Type a task and I'll decide if I want to help you remember it";
    }

    /**
     * Shows exit message
     *
     * @return The exit message
     */
    public String showExitMsg() {
        return "Bye. Please don't bother me again.";
    }

    /**
     * Shows message for UnknownInputException
     *
     * @param msg Error message
     * @return Full message with msg added
     */
    public String showUnknownInputMsg(String msg) {
        return "You can't do that: " + msg;
    }

    /**
     * Shows message for FileNotFoundException
     *
     * @param msg Error message
     * @return Full message with msg added
     */
    public String showFileNotFoundMsg(String msg) {
        return "Oh man: " + msg;
    }

    /**
     * Shows message for FileCorruptedException
     *
     * @param msg Error message
     * @return Full message with msg added
     */
    public String showFileCorruptedMsg(String msg) {
        return "Squiddy is corrupted: Please fix file manually \n" + msg;
    }

    /**
     * Shows message for other exceptions
     *
     * @param msg Error message
     * @return Full message with msg added
     */
    public String showOtherError(String msg) {
        return "Error not supported: " + msg;
    }

    /**
     * Show task details
     *
     * @param task Task to be displayed
     * @return String with task details
     */
    public String showTaskDetails(Task task) {
        return task.toString().indent(8);
    }

    /**
     * Shows message after adding a task into
     * TaskList
     *
     * @param task Task added
     * @return String with full message
     */
    public String showTaskInput(Task task) {
        return "Let me write this down: \n"
                + showTaskDetails(task);
    }

    /**
     * Shows message after deleting a task
     *
     * @param task Task deleted
     * @param size Size of the TaskList after deletion
     * @return String with full message
     */
    public String showDeleteCommand(Task task, int size) {
        String message = "Removing this task: \n" + showTaskDetails(task);
        String taskNumber = String.format("\nYou have %d tasks recorded", size);

        return message + taskNumber;
    }

    /**
     * Shows a task details to be combined into a
     * list to be displayed
     *
     * @param task  Current task
     * @param index Index of task
     * @return String with details of task formatted
     */
    public String showListContent(Task task, int index) {
        String taskString = String.format("%d. %s", index, task.toString()).indent(8);
        return taskString;
    }

    /**
     * Shows the current list of tasks in TaskList
     *
     * @param tasks TaskList to be displayed
     * @return List of all tasks in TaskList
     */
    public String showListCommand(TaskList tasks) {
        String title = "Let's see what you've got:\n";
        StringBuilder message = new StringBuilder(title);

        int max = tasks.size();
        for (int i = 0; i < max; i++) {
            Task curr = tasks.get(i);
            String result = showListContent(curr, i + 1);
            message.append(result);
        }
        return message.toString();
    }

    /**
     * Shows a message after marking task as done
     *
     * @param task Task marked done
     * @return Full message with task details
     */
    public String showMark(Task task) {
        return "OK, you've completed this: \n" + showTaskDetails(task);
    }

    /**
     * Shows a message after marking task as not done
     *
     * @param task Task marked not done
     * @return Full message with task details
     */
    public String showUnmark(Task task) {
        return "Why have you not completed this: \n" + showTaskDetails(task);
    }

    /**
     * Repeats user input when command is not valid
     *
     * @param repeat User input
     * @return Message with user input
     */
    public String showRepeat(String repeat) {
        return String.format("What would you like me to do with '%s'?", repeat);
    }

    /**
     * Shows the results of FindCommand
     *
     * @param results TaskList of tasks that match the user's
     *                search
     * @return List of all the task that match the search
     */
    public String showFindCommand(TaskList results) {
        String title = String.format("I found %d tasks:\n", results.size());
        StringBuilder message = new StringBuilder(title);
        int index = 1;
        for (Task task : results) {
            String result = showListContent(task, index++);
            message.append(result);
        }
        return message.toString();
    }

    /**
     * Shows a message when there are no results for FindCommand
     *
     * @param keyword User's search
     * @return Message asking the user to try again
     */
    public String showNoResult(String keyword) {
        String title = String.format("There are no tasks containing '%s' \n", keyword);
        return title + "Search using a better keyword";
    }

    /**
     * Shows a message after task is snoozed
     *
     * @param task Task snoozed
     * @return Message showing the task snoozed
     */
    public String showTaskSnoozed(Task task) {
        String title = "Alright this task has been snoozed: \n";
        return title + showTaskDetails(task);
    }
}
