package ozil.main;

import java.util.ArrayList;

import ozil.task.Task;
/**
* Static class, containing a lot of the commonly displayed messages in Ozil chatbot
*/
public class Messages {
    /**
     * Prints intro message of chatbot.
     */
    public static String intro() {
        return "Hello! I'm Ozil, your personal assist machine! How may I assist you? "
                + "Here are the commands you can run:\n"
                + "todo <description>: creates a todo task\n"
                + "deadline <description> /by <YYYY-MM-DD HHmm>: creates a deadline task, time is optional\n"
                + "event <description> /from <YYYY-MM-DD HHmm> /to <HHmm>\n"
                + "list :lists all tasks\n"
                + "latest :lists all tasks with proper Datetimes chronologically\n"
                + "mark <tasknumber>: marks a task as complete\n"
                + "unmark <tasknumber>: unmarks a task\n"
                + "delete <tasknumber>: deletes the task\n"
                + "find <task description keywords>: finds task with matching description\n"
                + "bye : exits the club with Ozil";
    }

    /**
     * Outro message.
     * @return Bye message
     */
    public static String outro() {
        return "Bye. Hope to see you again soon!";
    }
    /**
     * Prints out the task added message.
     * @param task Task that just has been added.
     */
    public static String printTaskAddMessage(Task task, int numberOfTasks) {
        return "Your task has been added to my records:\n"
              + " " + task.toString() + "\n" + Messages.printNumberOfTasks(numberOfTasks);
    }

    /**
     * Prints the delete task message
     * @param task Task that is deleted
     * @param numberOfTasks Number of current tasks
     * @return Deleted task message
     */
    public static String printTaskDeleteMessage(Task task, int numberOfTasks) {
        return "Understood. I have deleted the following task:\n" + " " + task.toString()
                + Messages.printNumberOfTasks(numberOfTasks);
    }

    /**
     * Prints the number of tasks in chatbot's list
     * @param numberOfTasks Number of tasks in current chatbot
     */
    public static String printNumberOfTasks(int numberOfTasks) {
        return "My boi Benzi, you now have " + numberOfTasks + " tasks.";
    }

    /**
     * Returns tasks that user was trying to find.
     * @param foundTasks
     * @return String showing message.
     */
    public static String printSearchedTasks(ArrayList<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            return "Sorry bro, I could not find any related tasks.";
        }
        int taskNum = 1;
        String res = "Here are the matching tasks in your list:\n";
        for (Task task: foundTasks) {
            res += taskNum + "." + task.toString() + "\n";
            taskNum++;
        }
        return res;
    }
}
