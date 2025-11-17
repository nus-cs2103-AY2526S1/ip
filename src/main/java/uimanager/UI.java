package uimanager;

import listmanager.Task;


public class UI {

    /**
     *Called when the chatbot is started up and returns the starting message.
     * @return a String that corresponds to the starting message.
     */
    public String onStart() {
        return "Hello! My name is bobby,\n"
                + "pleased to meet you.";
    }

    /**
     * Called when the chatbot is closed and returns the closing message.
     * @return a String that corresponds to the closing message.
     */
    public String onEnd() {
        return "Goodbye...";
    }

    /**
     * Is called when a new task is added and displays task name, total number of tasks, number of completed tasks
     * and uncompleted tasks.
     *
     * @param task the <code>Task</code> object that was added.
     * @param taskCount is the total number of tasks in the list.
     * @param completedTasks number of completed tasks in the list.
     * @param uncompletedTasks number of uncompleted tasks in the list.
     * @return message to be displayed as a String.
     */
    public String onNewTask(Task task, int taskCount, int completedTasks, int uncompletedTasks) {
        return "Task Added: " + task.getName()
                + "\nDo remember to take care of your health."
                + "\nYou have " + taskCount + " tasks in list, " + completedTasks + " completed "
                + uncompletedTasks + " uncompleted.";
    }

    /**
     * Is called when a task status is updated. Displays whether the task has been marked as completed as well as
     * the task name.
     *
     * @param isComplete is true if the task is marked complete, false otherwise.
     * @param task the task that was marked/unmarked.
     * @return message to be displayed as a String.
     */
    public String onUpdateTask(boolean isComplete, Task task) {
        return "You have " + (isComplete ? "marked" : "unmarked") + " this task.\n"
                + task.getTaskWithStatus();
    }

    /**
     * Method is called when a task is deleted. Displays what task is deleted.
     * @param task the task to be deleted.
     * @return the message displayed as a String.
     */
    public String onDeleteTask(Task task) {
        return "You have deleted " + task.getTaskWithStatus();
    }

    /**
     * Method is called when a task is tagged. Displays what tag was added to what task.
     *
     * @param tagName is the name of the tag added.
     * @param task the task the tag was added too.
     * @return message to be displayed as a String.
     */
    public String onTagTask(String tagName, Task task) {
        return "Added " + tagName + " tag to " + task.getName()
                + ".\nHave a bobulous day!";
    }

    /**
     * Method is called when a task is untagged. Displays what tag was removed from what task.
     * @param tagName is the name of the tag removed.
     * @param task is the task the tag was removed from.
     * @return the message to be displayed as a String.
     */
    public String onUntagTask(String tagName, Task task) {
        return "Removed " + tagName + " from " + task.getName() + ".";
    }
}
