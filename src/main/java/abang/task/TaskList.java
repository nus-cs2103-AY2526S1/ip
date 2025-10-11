package abang.task;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of {@link Task} objects.
 * Provides operations for adding, removing, marking, searching,
 * and converting tasks to and from file formats.
 */

public class TaskList {

    /** The underlying storage of tasks. */
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        assert list != null : "Internal list must be initialized";
        assert list.isEmpty() : "New TaskList should start empty";
    }

    /**
     * Constructs a TaskList from an existing ArrayList of tasks.
     *
     * @param arrayList the ArrayList of tasks to initialize with
     */
    public TaskList(ArrayList<Task> arrayList) {
        assert arrayList != null : "arrayList must not be null";
        this.list = arrayList;
        assert this.list != null : "list must not be null after assignment";
    }

    /**
     * Constructs a TaskList from a generic list of tasks.
     *
     * @param tasks the list of tasks to initialize with
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "tasks must not be null";
        this.list = new ArrayList<>(tasks);
        assert this.list != null : "list must not be null after copy";
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the list contains no tasks, otherwise {@code false}
     */
    public boolean isEmpty() {
        assert list != null : "list must exist";
        return list.isEmpty();
    }

    /**
     * Adds a new task to the list.
     *
     * @param input the task to be added
     */
    public void add(Task input) {
        assert input != null : "Cannot add null Task";
        int before = list.size();
        list.add(input);
        assert list.size() == before + 1 : "Size should increase by one after add";
    }

    /**
     * Tag a task with the corresponding index with its description
     * @param index
     * @param tagDescription
     */

    public void tag(int index, String tagDescription) {
        this.list.get(index -1).tag(tagDescription);
    }


    /**
     * Removes a task from the list by its 1-based index.
     *
     * @param index the 1-based index of the task to remove
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void remove(int index) {
        list.remove(index -1);
    }

    /**
     * Retrieves a task by its 1-based index.
     *
     * @param i the 1-based index of the task
     * @return the task at the given index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public Task getTask(int i) {
        return list.get(i - 1);
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return the number of tasks
     */
    public int numTask() {
        return list.size();
    }

    /**
     * Finds tasks that contain the given word in their description.
     *
     * @param word the search keyword
     * @return a new TaskList containing matching tasks
     */
    public TaskList find(String word) {
        List<Task> matched = list.stream()
                .filter(t -> t.getTaskDescription().contains(word))
                .toList();

        return new TaskList(new ArrayList<>(matched));
    }

    /**
     * Marks a task as done by its 1-based index.
     *
     * @param index the 1-based index of the task
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void mark(int index) {
        Task task = list.get(index -1);
        task.done();
    }

    public void clear() {
        list.clear();
    }

    /**
     * Marks a task as not done by its 1-based index.
     *
     * @param index the 1-based index of the task
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void unmark(int index) {
        Task task = list.get(index -1);
        task.notDone();
    }

    /**
     * Returns a string representation of the task list,
     * with each task numbered starting from 1.
     *
     * @return a string representation of the tasks
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int len = list.size();
        for (int i = 0; i < len; i++) {
            sb.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Converts the list of tasks into a format suitable for file saving.
     *
     * @return an ArrayList of string representations of tasks
     */
    public ArrayList<String> toFileLines() {
        ArrayList<String> fileLines = new ArrayList<String>();
        for (Task t: list) {
            fileLines.add(t.toFileFormat());
        }
        return fileLines;
    }

    /**
     * Constructs a TaskList from saved file lines.
     * Each line is parsed into a corresponding Task type:
     * - "T" for ToDo
     * - "D" for Deadline
     * - "E" for Event
     *
     * @param fileLines the file lines containing task data
     * @return a TaskList constructed from the file
     */
    public static TaskList fromFileLines(ArrayList<String> fileLines) {
        TaskList taskList = new TaskList();
        for (String line : fileLines) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                String tagLine = parts[3];
                Task task;
                switch (type) {
                    case "T":
                        task = new ToDo(description);
                        if (!tagLine.equals("null")) {
                            task.tag(tagLine);
                        }
                        break;
                    case "D":
                        task = new Deadline(description, parts[4]);
                        if (!tagLine.equals("null")) {
                            task.tag(tagLine);
                        }
                        break;
                    case "E":
                        String text = parts[4];
                        text = text.replace("(", "").replace(")", "");
                        String[] components = text.split("to:");
                        String start = components[0].replace("from:", "").trim();
                        String end = components[1].trim();
                        task = new Event(description, start, end);
                        if (!tagLine.equals("null")) {
                            task.tag(tagLine);
                        }
                        break;
                    default:
                        continue;
                }
                if (isDone) {
                    task.done();
                }
                taskList.add(task);
        }
        return taskList;
    }


}
