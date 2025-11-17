package bin.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with the given data.
     *
     * @param tasks list of task objects to be in task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        String result = "    Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int num = i + 1;
            result += "    " + num + "." + task.toString() + "\n";
        }
        return result;
    }

    public String mark(int i) {
        return tasks.get(i - 1).markAsDone();
    }

    public String unmark(int i) {
        return tasks.get(i - 1).markAsNotDone();
    }

    /**
     * returns a String and adds a task into task list.
     *
     * @param task Task to add into list.
     *
     * @return message to display for successful addition of task.
     **/
    public String add(Task task) {
        tasks.add(task);
        return "    Got it. I've added this task:\n      " + task.toString() + "\n"
                + "    Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * returns a message and deletes a task into task list.
     *
     * @param i position of task to remove in task list.
     *
     * @return message to display for successful deletion of task.
     **/
    public String delete(int i) {
        Task task = tasks.get(i);
        tasks.remove(i);
        return "    Noted. I've removed this task:\n      " + task.toString() + "\n"
                + "    Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * returns message for list of tasks with keyword.
     *
     * @param keyword String of keyword.
     * @return list of tasks containing keyword.
     */
    public String search(String keyword) {
        List<Task> filtered = tasks.stream().filter(task -> task.contains(keyword)).toList();
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < filtered.size(); i++) {
            Task task = filtered.get(i);
            int num = i + 1;
            result += "    " + num + "." + task.toString() + "\n";
        }
        return result;
    }

    public boolean checkDuplicate(Task newTask) {
        return tasks.stream().noneMatch(t -> t.equals(newTask));
    }
}
