package leo;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(List<Task> lst) {
        this.list = lst;
    }

    public int size() {
        return this.list.size();
    }

    /**
     * Adds task to the ArrayList wrapped by TaskList
     * @param task Task to be added to the TaskList
     */
    public void addTask(Task task) {
        this.list.add(task);
        int before = this.list.size();
        assert this.list.size() == before + 1 : "TaskList size should increase after addTask";
    }

    /**
     * Deletes the task at the corresponding index of the TaskList
     * @param index position of the task to be deleted
     * @throws IndexOutOfBounds If index not within the size of the TaskList
     */
    public void deleteTask(int index) throws IndexOutOfBounds {
        if (index > this.list.size() || index <= 0) {
            throw new IndexOutOfBounds("Index not present");
        }
        int before = this.list.size();
        this.list.remove(index - 1);
        assert this.list.size() == before - 1 : "TaskList size should decrease after deleteTask";
    }

    public void markDone(int index) throws IndexOutOfBounds {
        if (index > this.list.size() || index <= 0) {
            throw new IndexOutOfBounds("Index not present");
        }
        this.list.get(index - 1).markAsDone();
    }

    public void markUndone(int index) throws IndexOutOfBounds {
        if (index > this.list.size() || index <= 0) {
            throw new IndexOutOfBounds("Index not present");
        }
        this.list.get(index - 1).markAsUndone();
    }

    /**
     * Converts the task to the format to be written into the storage file.
     * Writes the string into the storage file.
     * @param fw The FileWriter that belongs to the storage
     */
    public void saveToStorage(FileWriter fw) throws IOException {
        for (Task task : this.list) {
            fw.write(task.toSaveFormat() + System.lineSeparator());
        }
    }

    /**
     * Returns the string of the task at a specific position
     * @param index Position in the ArrayList
     * @return The string of the task
     * @throws IndexOutOfBounds If index not within the size of the TaskList
     */
    public String elem(int index) throws IndexOutOfBounds {
        if (index > this.list.size() || index <= 0) {
            throw new IndexOutOfBounds("Index not present");
        }
        String result = this.list.get(index - 1).toString();
        assert result != null && !result.isBlank() : "elem() should not return a blank string";
        return result;
    }

    /**
     * Converts the TaskList into a string
     * @return The string of all the objects in the TaskList
     */
    public String iterate() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            String index = i + 1 + ". ";
            String content = this.list.get(i).toString();
            sb.append(i + 1 + ". ").append(content).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String find(String str) {
        assert str != null : "Search string must not be null";
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).toString().contains(str)) {
                String idx = count + 1 + ". ";
                String content = this.list.get(i).toString();
                sb.append(idx).append(content).append("\n");
                count++;
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String getUpcoming(int days) {
        LocalDate now = LocalDate.now();
        LocalDate max = now.plusDays(days);
        List<Task> lst = this.list.stream()
                .filter(x -> x.isUpcoming(now, max))
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (Task task : lst) {
            String idx = count + 1 + ". ";
            String content = task.toString();
            sb.append(idx).append(content).append("\n");
            count++;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
