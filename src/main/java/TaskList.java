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

    /**
     * Supposed to give a string representation of all the tasks stored in TaskList
     *
     * @return String representation of all tasks
     */
    @Override
    public String toString() {
        StringBuilder textList = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task cur = this.tasks.get(i);

            String curTask = String.format("%d. %s\n", i + 1, cur);
            textList.append(curTask);
        }

        return textList.toString();
    }
}
