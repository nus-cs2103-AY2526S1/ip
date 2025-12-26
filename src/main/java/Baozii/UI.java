package Baozii;

import java.util.Optional;

public class UI {
    private static final String WELCOME = "Hi! I am Baozii. What can I do for you?";
    private static final String GOODBYE = "Bye, have a great day!";

    public String welcome() {
        return WELCOME;
    }

    public String goodbye() {
        return GOODBYE;
    }

    /**
     * Given a nullable task, prints whether it is successfully added.
     * @param task a nullable task object. If it is null, it means the add action was unsuccessful

     */
    public String showAdd(Optional<Task> task) {
        return task.map(value -> "Successfully added task: \n" + value).orElse("Task add unsuccessful");
    }

    /**
     * Given a nullable task, prints whether it is successfully deleted.
     * @param task a nullable task object. If it is null, it means the delete action was unsuccessful

     */
    public String showDelete(Optional<Task> task) {
        return task.map(value -> "Successfully deleted task: \n" + value).orElse("Task delete unsuccessful");
    }

    /**
     * Given a nullable task, prints whether it is successfully marked.
     * @param task a nullable task object. If it is null, it means the mark action was unsuccessful

     */
    public String showMark(Optional<Task> task) {
        return task.map(value -> "Successfully marked task: \n" + value).orElse("Task mark unsuccessful");
    }

    /**
     * Given a nullable task, prints whether it is successfully unmarked.
     * @param task a nullable task object. If it is null, it means the unmark action was unsuccessful

     */
    public String showUnmark(Optional<Task> task) {
        return task.map(value -> "Successfully unmarked task: \n" + value).orElse("Task unmark unsuccessful");
    }

    public String showTag(Optional<Task> task) {
        return task.map(value -> "Successfully tagged task: \n" + value).orElse("Task tag unsuccessful");
    }

    /**
     * Prints out the tasklist
     * @param tasks the given tasklist

     */
    public String showList(TaskList tasks) {
        return tasks.toString();
    }
    public String showException(Exception e) {
        return e.getMessage();
    }
}
