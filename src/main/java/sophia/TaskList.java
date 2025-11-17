package sophia;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contain a list of tasks
 */
public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public TaskList() {}
    /**
     * Constructor of TaskList
     * <p>
     * @param xs specifies a list of Tasks which are to be added to taskList upon initialisation
     */
    public TaskList(List<Task> xs) throws FileNotFoundException {
        tasks.addAll(xs);
    }

    /**
     * Returns a formatted string of a list of tasks which are overdue/pending
     * @return a formatted string
     */
    public List<String> sendReminder() {
        List<String> reminderList = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof TaskWithDate) {
                TaskWithDate taskWithDate = (TaskWithDate) task;
                if (taskWithDate.isDueWithinThreeDays() && !task.isDone()) {
                    reminderList.add(taskWithDate.sendReminder());
                }
            }
        }
        if (reminderList.isEmpty()) {
            reminderList.add("Woohoo, no task is due soon!");
        }
        //System.out.println(reminderList);
        return reminderList;
    }
    /**
     * Returns all tasks whose descriptions contain the given keyword.
     *
     * @param key the search keyword
     * @return a list of matching tasks
     */
    public List<Task> findTask(String key) {
        String lowerCaseKey = key.toLowerCase();
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(lowerCaseKey)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Writes the serialized form of each task in this list to the provided writer.
     *
     * @param bw the {@link java.io.BufferedWriter} to write to
     */

    public void write(BufferedWriter bw) throws IOException {
        for (Task task : tasks) {
            task.write(bw);
        }
    }

    /**
     * Prints the details of every task in this list to standard output.
     */
    public String printList() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            str.append((i + 1)).append(". ").append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int taskListSize() {
        return this.tasks.size();
    }

    public void setDone(int index, boolean done) {
        this.tasks.get(index).setDone(done);
    }

    /**
     * Retrieve the respective index_th task and print the string representation
     * @param index index of the task to retrieve
     */
    public String getTask(int index) {
        return this.tasks.get(index).toString();
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }
}
