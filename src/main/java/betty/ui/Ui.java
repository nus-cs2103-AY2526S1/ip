package betty.ui;


import betty.task.Priority;
import betty.task.Task;
import betty.task.TaskList;

/**
 * Represents an Ui object that prints out messages by the chatbot
 */
public class Ui {

    private static final String DIVIDER_LINE = "-----------------------------------";
    /**
     * Wraps a message between 2 lines, creating a box with message inside
     * @param message the message to be wrapped
     */
    public String printBox(String message) {
        return DIVIDER_LINE + "\n" + message + "\n" + DIVIDER_LINE;
    }
    // Function to print greeting message
    public String greeting() {
        return printBox("Hello! I'm Betty\nWhat can I do for you?");
    }
    // Function to print goodbye message
    public String goodbye() {
        return printBox("Bye. Hope to see you again soon!");
    }
    /**
     * Displays the list of tasks
     * @param taskList the task list to be displayed
     * @return the formatted string of the task list
     */
    // Displays the list of tasks
    public String displayList(TaskList taskList) {
        if (taskList.size() == 0) {
            return printBox("You have no tasks in your list.");
        }
        return printBox("Here are the task in your list: \n" + taskList.toString());
    }

    /**
     * Prints the message for adding a task to the task list
     * @param task task to be added
     * @param taskList task list where task is added
     */
    public String addTask(Task task, TaskList taskList) {
        return printBox("Got it. I've added this task: \n"
                            + "  " + task.toString()
                            + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * Prints the message for deleting a task to the task list
     * @param task task to be deleted
     * @param taskList task list where task is deleted
     */
    public String deleteTask(Task task, TaskList taskList) {
        StringBuilder deleteMessage = new StringBuilder();
        deleteMessage.append("Noted, I've removed this task:\n")
                        .append(task.toString())
                            .append("\nNow you have ")
                                .append(taskList.size()).append(" tasks in the list.");
        return printBox(String.valueOf(deleteMessage));
    }
    // Message for mark done and undone
    public String markDone(TaskList taskList, int number) {
        return printBox("Nice! I've marked this task as done:\n" + taskList.get(number - 1).toString());
    }
    public String markUndone(TaskList taskList, int number) {
        return printBox("OK, I've marked this task as not done yet:\n" + taskList.get(number - 1).toString());
    }
    // Print error message
    public String printError(String message) {
        return printBox(message);
    }

    /**
     * Display the filtered list given a filtered task list
     * @param filtered filtered task list to be displayed
     */
    public String displayFilteredList(TaskList filtered) {
        if (filtered.size() == 0) {
            return printBox("No matching tasks found in your list.");
        }
        String message = "Here are the matching tasks in your list :\n";
        return printBox(message + filtered.toString());
    }

    public String setPriority(Task task, Priority priority) {
        String message = "Set priority of this task as " + priority + "\n" + task.toString();
        return printBox(message);
    }
}
