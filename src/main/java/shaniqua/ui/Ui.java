package shaniqua.ui;

import shaniqua.Shaniqua;

import java.util.concurrent.CompletableFuture;

public class Ui {
    private StringBuilder outputString;
    private boolean isExited = false;

    /**
     * Constructs a new Ui object.
     * Initializes the output string builder for capturing display messages.
     */
    public Ui() {
        outputString = new StringBuilder();
    }

    /**
     * Handles user input by processing it through the Shaniqua application
     * and returning the resulting output.
     *
     * @param input the user input string to be processed
     * @param s the Shaniqua application instance to handle the input
     * @return the output string generated from processing the input
     */
    public String handle(String input, Shaniqua s) {
        clearOutput();
        s.handle(input);
        return outputString.toString();
    }

    /**
     * Appends a message to the output string buffer.
     * Each message is followed by a newline character.
     *
     * @param message the message to be added to the output
     */
    public void output(String message) {
        outputString.append(message).append("\n");
    }

    /**
     * Clears the current output buffer by creating a new StringBuilder.
     * This method is typically called before processing new input.
     */
    public void clearOutput() {
        outputString = new StringBuilder();
    }


    public void farewell() {
        output("Bye. Hope to see you again soon!");
        isExited = true;
    }

    /**
     * Displays the farewell message and sets the exit flag.
     * This method should be called when the application is terminating.
     */
    public boolean shouldExit() {
        return isExited;
    }

    /**
     * Displays a confirmation message when a task is successfully added.
     * Shows the task description and the updated total number of tasks.
     *
     * @param taskDescription the string representation of the added task
     * @param totalTasks the total number of tasks in the list after addition
     */
    public void taskAdded(String taskDescription, int totalTasks) {
        output("Got it. I've added this task:");
        output(totalTasks + ". " + taskDescription);
        output("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays a confirmation message when a task is marked as completed.
     *
     * @param taskDescription the string representation of the marked task
     */
    public void taskMarked(String taskDescription) {
        output("Nice! I've marked this task as done:");
        output("  " + taskDescription);
    }

    /**
     * Displays a confirmation message when a task is unmarked (marked as incomplete).
     *
     * @param taskDescription the string representation of the unmarked task
     */
    public void taskUnmarked(String taskDescription) {
        output("OK, I've marked this task as not done yet:");
        output("  " + taskDescription);
    }

    /**
     * Displays a confirmation message when a task is successfully deleted.
     * Shows the updated total number of tasks remaining in the list.
     *
     * @param totalTasks the total number of tasks remaining after deletion
     */
    public void taskDeleted(int totalTasks) {
        output("Task deleted successfully. Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays all tasks in the task list.
     * Shows an appropriate message if the task list is empty.
     *
     * @param taskList the formatted string containing all tasks to be displayed
     */
    public void showTasks(String taskList) {
        output("Here are the tasks in your list:");
        if (taskList.trim().isEmpty()) {
            output("Looks pretty empty now, add something!");
        } else {
            output(taskList);
        }
    }

    /**
     * Displays the results of a task search operation.
     * Shows matching tasks or an appropriate message if no matches are found.
     *
     * @param foundTasks the formatted string containing matching tasks
     * @param count the number of tasks that matched the search criteria
     */
    public void showFoundTasks(String foundTasks, int count) {
        output("Here are the matching tasks in your list:");
        if (count == 0) {
            output("There aren't any tasks that match your search :(");
        } else {
            output(foundTasks);
        }
    }

    /**
     * Displays an error message based on the provided exception.
     * Shows a user-friendly error prefix followed by the exception message.
     *
     * @param e the exception containing the error information to display
     */
    public void error(Exception e) {
        output("Oops, I can't do that because:");
        output(e.getMessage());
    }

    /**
     * Displays a message indicating that the user input was not understood.
     * This method is called when the parser cannot interpret the user's command.
     */
    public void invalidInput() {
        output("I didn't quite get that, could you give it another go?");
    }

    /**
     * Displays a success message when tasks are loaded from storage.
     * Includes a warning about potential duplicate tasks and shows the count of loaded tasks.
     *
     * @param count the number of tasks that were successfully loaded
     */
    public void loadSuccess(int count) {
        output("Warning: Duplicates may be created.");
        output("Successfully loaded " + count + " tasks");
    }

    /**
     * Displays a success message when tasks are saved to storage.
     */
    public void saveSuccess() {
        output("Tasks saved successfully!");
    }

}
