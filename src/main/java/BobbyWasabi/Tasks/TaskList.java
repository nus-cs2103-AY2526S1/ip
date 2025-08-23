package bobbywasabi.tasks;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int indx) {
        return this.tasks.get(indx);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int indx) {
        this.tasks.remove(indx);
    }

    public String findTasksThatMatchKeyword(String keyword) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task cur = this.tasks.get(i);
            if (cur.find(keyword)) {
                tasks.add(cur);
            }
        }

        return this.convertTasksToString(tasks);
    }

    public String convertTaskToString(int index, Task task) {
        return String.format("%d. %s\n",
                index, task);
    }

    public String convertTasksToString(ArrayList<Task> tasks) {
        StringBuilder textList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task cur = tasks.get(i);
            textList.append(convertTaskToString(i + 1, cur));
        }
        return textList.toString();
    }

    /**
     * Supposed to give a string representation of all the tasks stored in BobbyWasabi.BobbyWasabi.Tasks.TaskList
     *
     * @return String representation of all tasks
     */
    @Override
    public String toString() {
        return this.convertTasksToString(this.tasks);
    }
}
