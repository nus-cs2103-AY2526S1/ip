package haru.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import haru.exception.HaruException;
import haru.exception.InvalidTaskIdException;

/**
 * Represents a list of tasks with persistence support.
 */
public class TaskList implements Serializable {
    private final String filePath;
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with file path and tasks.
     *
     * @param filePath the file path
     * @param tasks    the task list
     */
    private TaskList(String filePath, ArrayList<Task> tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    /**
     * Creates an empty TaskList with file path.
     *
     * @param filePath the file path
     * @return the empty TaskList
     */
    public static TaskList empty(String filePath) {
        return new TaskList(filePath, new ArrayList<>());
    }

    /**
     * Loads a TaskList from a file.
     *
     * @param filePath the file path
     * @return the loaded TaskList
     * @throws IOException            if file read fails
     * @throws ClassNotFoundException if class is not found
     */
    public static TaskList fromFile(String filePath)
            throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            return (TaskList) in.readObject();
        }
    }

    /**
     * Creates a TaskList from an existing list of tasks.
     *
     * @param tasks the task list
     * @return the TaskList
     */
    public static TaskList fromList(ArrayList<Task> tasks) {
        return new TaskList(null, tasks);
    }

    /**
     * Writes the TaskList to file.
     *
     * @throws IOException if file write fails
     */
    public void writeToFile() throws IOException {
        if (this.filePath == null) {
            return;
        }
        try (FileOutputStream fos = new FileOutputStream(this.filePath);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(this);
        }
    }

    /**
     * Parses a string into a task ID index.
     *
     * @param str the string to parse
     * @return the zero-based task index
     * @throws HaruException if parsing fails or index is invalid
     */
    public int parseTaskId(String str) throws HaruException {
        int length = this.tasks.size();
        try {
            int id = Integer.parseInt(str);
            if (1 <= id && id <= length) {
                return id - 1;
            } else {
                throw new InvalidTaskIdException(length);
            }
        } catch (NumberFormatException e) {
            throw new InvalidTaskIdException(length);
        }
    }

    /**
     * Adds a task to the list and writes to file.
     *
     * @param task the task to add
     * @throws IOException if file write fails
     */
    public void add(Task task) throws IOException {
        this.tasks.add(task);
        this.writeToFile();
    }

    /**
     * Removes a task from the list and writes to file.
     *
     * @param index the task index
     * @return the removed task
     * @throws IOException if file write fails
     */
    public Task remove(int index) throws IOException {
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        this.writeToFile();
        return task;
    }

    /**
     * Marks a task as done and writes to file.
     *
     * @param index the task index
     * @return the updated task
     * @throws IOException if file write fails
     */
    public Task mark(int index) throws IOException {
        Task task = this.tasks.get(index);
        task.mark();
        this.writeToFile();
        return task;
    }

    /**
     * Unmarks a task as not done and writes to file.
     *
     * @param index the task index
     * @return the updated task
     * @throws IOException if file write fails
     */
    public Task unmark(int index) throws IOException {
        Task task = this.tasks.get(index);
        task.unmark();
        this.writeToFile();
        return task;
    }

    /**
     * Finds tasks whose names contain the given string.
     *
     * @param str the string to search for
     * @return a TaskList of matching tasks
     */
    public TaskList find(String str) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getName().contains(str)) {
                matches.add(task);
            }
        }
        return TaskList.fromList(matches);
    }

    /**
     * Finds tasks that contain the given tag.
     *
     * @param tag the tag to search for
     * @return a TaskList of matching tasks
     */
    public TaskList filter(String tag) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.hasTag(tag)) {
                matches.add(task);
            }
        }
        return TaskList.fromList(matches);
    }

    /**
     * Adds a tag to the task at the given index and writes to file.
     *
     * @param index the task index
     * @param tag   the tag to add
     * @return the updated task
     * @throws IOException if file write fails
     */
    public Task addTag(int index, String tag) throws IOException {
        Task task = this.tasks.get(index);
        task.addTag(tag);
        this.writeToFile();
        return task;
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i > 0) {
                sb.append('\n');
            }
            sb.append(i + 1).append(". ").append(tasks.get(i));
        }
        return sb.toString();
    }
}
