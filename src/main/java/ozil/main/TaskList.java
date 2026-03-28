package ozil.main;

import java.util.ArrayList;

import ozil.exception.ErrorMessages;
import ozil.exception.OzilException;
import ozil.task.Task;

/**
 * Class handling the list of tasks in chatbot
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints out current tasklist
     * @return String of all tasks
     */
    public String printlist() {
        if (this.tasks.isEmpty()) {
            return "You have no tasks. You are a free agent :)";
        } else {
            String res = "Here are the tasks in your list:\n";
            for (int i = 0; i < this.tasks.size(); i++) {
                res += (i + 1) + ". " + this.tasks.get(i).toString() + "\n";
            }
            return res;
        }
    }

    /**
     * Adds a task to the tasklist.
     * @param task The task that is to be added to the tasklist.
     */
    public void addTaskToList(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gives the number of tasks.
     * @return An Integer that is the number of tasks.
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    /**
     * Returns the task of that number. Task number must be valid
     * @param taskNumber The number of that task.
     * @return The task that is that task number.
     * @throws OzilException If the tasknumber is invalid.
     */
    public Task getTask(int taskNumber) throws OzilException {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            throw new OzilException(ErrorMessages.errorMessage("Your task number is invalid."));
        }
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Sets a particular task.
     * @param taskNumber Number of that task, not its index.
     * @param task New task that is to replace/update the old one.
     * @throws OzilException If the task number is invalid.
     */
    public void setTask(int taskNumber, Task task) throws OzilException {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            throw new OzilException(ErrorMessages.errorMessage("Your task number is invalid."));
        }
        this.tasks.set(taskNumber - 1, task);
    }

    /**
     * Marks a task as done
     * @param taskNumber The number of the task.
     * @throws OzilException
     */
    public void markTaskAsDone(int taskNumber) throws OzilException {
        Task temp = this.getTask(taskNumber);
        temp.markAsDone();
        this.setTask(taskNumber, temp);
    }

    /**
     * Unmarks a task as incomplete
     * @param taskNumber Number of the task
     * @throws OzilException
     */
    public void markTaskAsUndone(int taskNumber) throws OzilException {
        Task temp = this.getTask(taskNumber);
        temp.markAsUndone();
        this.setTask(taskNumber, temp);
    }

    /**
     * Deletes a particular task.
     * @param taskNumber The number of that task that is to be deleted.
     * @throws OzilException If the task number is invalid.
     */
    public String deleteTask(int taskNumber) throws OzilException {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            throw new OzilException(ErrorMessages.errorMessage("Your task number is invalid."));
        }
        Task deletedTask = this.getTask(taskNumber);
        this.tasks.remove(taskNumber - 1);
        return Messages.printTaskDeleteMessage(deletedTask, this.tasks.size());
    }

    /**
     * Finds task based on description
     * @param keywords Description input by user
     * @return
     */
    public String findTask(String keywords) {
        keywords = keywords.toLowerCase();
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task check = this.tasks.get(i);
            if (check.toString().toLowerCase().contains(keywords)) {
                foundTasks.add(check);
            }
        }
        return Messages.printSearchedTasks(foundTasks);
    }

    /**
     * Lists all tasks that have a date chronologically
     * @return List of tasks in chronological order as a String
     */
    public String listChronological() {
        ArrayList<Task> tasksWithDate = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).hasDate()) {
                tasksWithDate.add(this.tasks.get(i));
            }
        }
        if (tasksWithDate.isEmpty()) {
            return "None of your tasks currently have proper dates bro.\n"
                    + "Events should be event <description> /from <yyyy-MM-dd HHmm> /to <HHmm>\n"
                    + "Deadlines should be deadline <description> /by <yyyy-MM-dd HHmm>, time is optional";
        }
        ArrayList<Task> sortedTasks = sortTasksChronologically(tasksWithDate);
        String res = "Here are your tasks in chronological order\n";
        for (int i = 0; i < sortedTasks.size(); i++) {
            res += (i + 1) + ". " + sortedTasks.get(i).toString() + "\n";
        }
        return res;
    }

    private ArrayList<Task> sortTasksChronologically(ArrayList<Task> tasksWithDate) {
        tasksWithDate.sort((t1, t2) -> t1.getTaskDate().compareTo(t2.getTaskDate()));
        return tasksWithDate;
    }
}

