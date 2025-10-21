package geegar.gui;

import geegar.task.Task;
import geegar.task.TaskList;

/**
 *  This code was extracted from the example given during the guide
 *  for building a dialogBox from the cs2103t website
 * GUI Compatible UI  handler that will capture the output as strings
 */
public class Gui {
    private StringBuilder response;

    public Gui() {

        response = new StringBuilder();
    }

    /**
     * Gets the accumulated response String
     */
    public String getResponse() {

        return this.response.toString().trim();
    }

    public void printIntroduction() {

        this.response.append("Hello! I'm Geegar!, what can I do for you today! >:-D");
    }

    public void printGoodbye() {

        this.response.append("Alright Bye ! Stay Geeky! >:-D");
    }

    /**
     * Goes through the entire task list and prints
     * in a formated way and by index
     * @param tasks List of tasks currently saved
     */
    public void printTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            this.response.append("There are currently no tasks found!");
        } else {
            this.response.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                this.response.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
    }

    /**
     * Prints an error message
     * @param message
     */
    public void printError(String message) {

        this.response.append("Oooopsies, ").append(message);
    }

    /**
     * Takes in the current task, and totalTask number, and returns what task has just been added
     * as well as the current state of the total number of tasks
     * @param task
     * @param totalTasks
     */
    public void printTaskAdded(Task task, int totalTasks) {
        this.response.append("Got it, I've added this task: \n");
        this.response.append(task).append("\n");
        this.response.append("Now you have ").append(totalTasks).append(" tasks in the list.");
    }

    /**
     * Takes in the task to mark, and show on the UI that, that
     * specific task has been marked
     * @param task
     */
    public void printTaskMarked(Task task) {
        this.response.append("Nice! I've marked this task as done!\n");
        this.response.append(task).append("\n");
        this.response.append("You are locked in !!");
    }

    /**
     * Takes in the task to unmark, and show on the UI that, that
     * specific task has been unmarked
     * @param task
     */
    public void printTaskUnmarked(Task task) {
        this.response.append("Alright! I've marked this task as NOT done !\n");
        this.response.append(task).append("\n");
        this.response.append("Lock in Harder Man >:(!!!");
    }

    /**
     * Takes in the task that is required to be deleted,
     * and show on the UI that, that specific task has been
     * deleted. Also displays the remaining number of tasks.
     * @param task
     * @param totalTasks
     */
    public void printTaskDeleted(Task task, int totalTasks) {
        this.response.append("Geekity it, I've deleted this task: \n");
        this.response.append(task).append("\n");
        this.response.append("Now you have ").append(totalTasks).append(" tasks in the list.");
    }

    /**
     * Prints out the schedule based on the user's queries
     */
    public void printSchedule() {
        this.response.append("Here are your deadlines/events due or during your requested time:\n");
    }

    /**
     * Prints that specific task
     * @param task
     */
    public void printTask(Task task) {

        this.response.append(task).append("\n");
    }

    /**
     * Based on the user's keyword, prints an introduction for
     * tasks that matches that specific keyword
     */
    public void printFind() {

        this.response.append("Here are your tasks with similiar keywords: \n");
    }

    /**
     * Prints out to notify that the tasklist is empty
     */
    public void printEmpty() {

        this.response.append("There are currently no tasks found! stay geeky and add in more tasks man");
    }
}
