package chlo.ui;

import chlo.task.Task;
import chlo.task.TaskList;

/**
 * Ui class that handles string outputs
 */
public class Ui {
    public String getError(String message) {
        return message;
    }

    public String getTaskList(TaskList tasks) {
        return (tasks.size() == 0 ? "No current tasks? Chlo is amazed..." : "Here are the tasks in your list:\n" + tasks.getTasks());
    }

    public String getFilteredList(TaskList tasks, String s) {
        return (tasks.size() == 0 ? "No current tasks? Chlo is amazed..." : tasks.getFilteredTasks(s));
    }

    public String getAddTask(Task task, int numTasks) {
        return String.format("Chlo has added this task:\n" + task) + "\n" + String.format("Now you have %d tasks in the list.", numTasks);
    }

    public String getMarkTask(Task task) {
        return "Chlo marks this as done:\n" + task;
    }

    public String getUnmarkTask(Task task) {
        return "Chlo marks this as undone:\n" + task;
    }

    public String getDeleteTask(Task task) { return "Chlo deletes this:\n" + task; }
}
