package models;

import java.util.ArrayList;

import exceptions.ApunableException;

/**
 * Stores models added by user.
 */
public class TaskList implements Savable {
    public static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    private Task createTaskFromString(String formattedString) throws ApunableException {
        String[] taskInfos = formattedString.split(" \\| ", 4);
        Task task = null;

        switch (taskInfos[0]) {
            case "T" -> {
                assert taskInfos.length > 2 : "Missing argument: description";
                task = new Todo(taskInfos[2]);
            }
            case "D" -> {
                assert taskInfos.length > 3 : "Missing arguments: description or /by";
                task = new Deadline(taskInfos[2], taskInfos[3]);
            }
            case "E" -> {
                assert taskInfos.length > 3 : "Missing arguments: /from, /to";
                String[] fromTo = taskInfos[3].split(" \\| ");
                assert fromTo.length > 1 : "Failed to extract /from, /to";
                task = new Event(taskInfos[2], fromTo[0], fromTo[1]);
            }
            default -> {
                throw new ApunableException("Invalid task type");
            }
        }

        if (task != null && taskInfos[1].equals("1")) {
            task.markAsDone();
        }

        return task;
    }

    public TaskList(ArrayList<String> taskDesc) throws ApunableException {
        tasks = new ArrayList<>();

        for (int i = 0; i < taskDesc.size(); i++) {
            try {
                tasks.add(createTaskFromString(taskDesc.get(i)));
            } catch (ApunableException e) {
                throw new ApunableException(e.getMessage() + " at line " + (tasks.size() + 1));
            }
        }
    }

    public String getFormattedString() {
        return String.join("\n", tasks.stream().map(t -> t.getFormattedString()).toList());
    }

    /**
     * Returns the task at specific 0-based {@code index}. 
     * 
     * @param index index of the task to be retrieved(0-based). 
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * @return the number of models in this {@code taskList}.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new task into this {@code taskList}. 
     * 
     * @param task task to be added. 
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from this {@code taskList}. 
     * 
     * @param index index of task to be removed. 
     */
    public void remove(int index) {
        tasks.remove(index);
    }
}
