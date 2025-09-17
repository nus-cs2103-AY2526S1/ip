package gokschat;

import gokschat.tasks.Task;

import java.util.List;

/// This class deals with interactions with the user.
///
/// @author Ravichandran Gokul
public class Ui {
    /**
     * Displays the list of items.
     *
     * @param listOfTasks
     * @return The list of items as a String.
     */
    public String displayList(List<Task> listOfTasks) {
        int len = listOfTasks.size();
        StringBuilder s = new StringBuilder("    ____________________________________________________________\n");
        s.append("    Here are the tasks in your list!\n");
        for (int i = 1; i <= len; i++) {
            s.append("    ").append(i).append(".").append(listOfTasks.get(i - 1)).append("\n");
        }
        s.append("    ____________________________________________________________");
        return s.toString();
    }

    /**
     * Returns the Mark gokschat.tasks.Task message.
     *
     * @param task
     * @return The mark task message as a string.
     */
    public String markTaskMessage(Task task) {
        return "    ____________________________________________________________\n" +
                "    Amazing! Insane productivity la keep it up. Marked it as done:\n" +
                "        " + task + "\n" +
                "    ____________________________________________________________";
    }

    /**
     * Returns the Unmark gokschat.tasks.Task message.
     *
     * @param task
     * @return The unmark task message as a string.
     */
    public String unmarkTaskMessage(Task task) {
        return "    ____________________________________________________________\n" +
                "    Boooo... Do better next time bro. Marked this as not done yet:\n" +
                "        " + task + "\n" +
                "    ____________________________________________________________";
    }

    /**
     * Displays the Delete gokschat.tasks.Task message on the UI.
     *
     * @param task
     * @param listOfTasks
     */
    public String deleteTaskMessage(Task task, List<Task> listOfTasks) {
        return "    ____________________________________________________________\n" +
                "    Aights. I have deleted this task:\n" +
                "        " + task + "\n" +
                "    Now you have " + listOfTasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________";
    }

    /**
     * Returns the add task message.
     *
     * @param task
     * @param listOfTasks
     * @return The add task message
     */
    public String addTaskMessage(Task task, List<Task> listOfTasks) {
        return "    ____________________________________________________________\n" +
                "    Aights. I have added this task:\n" +
                "        " + task + "\n" +
                "    Now you have " + listOfTasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________";
    }

    /**
     * Returns the exception message.
     *
     * @param e
     * @return The exception message
     */
    public String exceptionMessage(Exception e) {
        return "    ____________________________________________________________\n" +
                e.getMessage() + "\n" +
                "    ____________________________________________________________";
    }
}
