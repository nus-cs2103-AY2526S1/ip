package cookie.ui;

import cookie.task.Task;
import cookie.task.TaskList;


/**
 * Handles input and output operations and displaying of relevant messages.
 */
public class Ui {

    public Ui() {

    }

    /**
     * Displays welcome message when user first launches the application in GUI.
     */
    public String showWelcomeGui() {
        return "Hey there! My name is Cookie\n How can I help you?";
    }

    /**
     * Displays goodbye message when the application is terminated in GUI.
     */
    public String showByeGui() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays all the tasks in the current list of tasks in GUI.
     *
     * @param listOfTasks Current list of tasks.
     */
    public String showListGui(TaskList listOfTasks) {
        String output = "Here are your tasks:\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            output += ((i + 1) + ". " + listOfTasks.get(i) + "\n");
        }
        return output;
    }

    /**
     * Displays message indicating the task has been marked as done for GUI.
     */
    public String showMarkGui(Task taskToBeMarked) {
        return "Great! I've marked this task as done:\n" + taskToBeMarked;
    }

    /**
     * Displays message indicating the task has been marked as not done for GUI.
     */
    public String showUnmarkGui(Task taskToBeUnmarked) {
        return "Alright. I've marked this task as not done yet:\n" + taskToBeUnmarked;
    }

    /**
     * Displays message indicating the task has been deleted from the list of tasks for GUI.
     *
     * @param taskToBeDeleted The task to delete.
     * @param taskCounter Number of tasks in the list after deletion.
     */
    public String showDeleteGui(Task taskToBeDeleted, int taskCounter) {

        return "Alright. I've deleted this task:\n" + taskToBeDeleted
                + "\n" + "Now you have " + taskCounter + " tasks in the list.";
    }

    /**
     * Displays message indicating the todo task has been added to the list of tasks for GUI.
     *
     * @param newTodo Todo task to added.
     * @param taskCounter Number of tasks in the list after adding new todo.
     */
    public String showTodoGui(Task newTodo, int taskCounter) {

        return "A todo, got it! I've added this task:\n" + newTodo + "\n"
                + "Now you have " + taskCounter + " tasks in the list.";
    }

    /**
     * Displays message indicating the deadline task has been added to the list of tasks for GUI.
     *
     * @param newDeadline Deadline task to added.
     * @param taskCounter Number of tasks in the list after adding new deadline task.
     */
    public String showDeadlineGui(Task newDeadline, int taskCounter) {
        return "A deadline, got it! I've added this task:\n" + newDeadline + "\n"
                + "Now you have " + taskCounter + " tasks in the list.";
    }

    /**
     * Displays message indicating the event task has been added to the list of tasks for GUI.
     *
     * @param newEvent Event task to added.
     * @param taskCounter Number of tasks in the list after adding new event task.
     */
    public String showEventGui(Task newEvent, int taskCounter) {
        return "An event, got it! I've added this task:\n" + newEvent + "\n"
                + "Now you have " + taskCounter + " tasks in the list.";
    }

    /**
     * Displays error message to user for GUI.
     *
     * @param message Error message to be displayed.
     */
    public String showErrorGui(String message) {
        return "Oh no! " + message;
    }

    /**
     * Displays message indicating an error in loading of tasks for GUI.
     */
    public String showLoadingErrorGui() {
        return "Error in loading tasks.";
    }

    /**
     * Displays the list of matching tasks for GUI.
     *
     * @param listOfMatchingTasks List of matching tasks.
     */
    public String showFindingsGui(TaskList listOfMatchingTasks) {
        int numberOfMatchingTasks = listOfMatchingTasks.size();
        if (numberOfMatchingTasks == 0) {
            return "No matching tasks!";
        } else {
            String output = "Here are the tasks that match!\n";
            for (int i = 0; i < listOfMatchingTasks.size(); i++) {
                output += ((i + 1) + ". " + listOfMatchingTasks.get(i) + "\n");
            }
            return output;
        }
    }

    public String showUpdateGui(Task updatedTask, int taskNumber) {
        return "Great! I have updated Task " + taskNumber + ":\n" + updatedTask;
    }
}
