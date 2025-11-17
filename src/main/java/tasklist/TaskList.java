package tasklist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.UI;

public class TaskList {
    protected ArrayList<Task> tasks;

    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_EVENT = "E";
    private static final String MARKED = "1";
    private static final String UNMARKED = "0";
    private static final int TODO_DATA_LENGTH = 3;
    private static final int DEADLINE_DATA_LENGTH = 4;
    private static final int EVENT_DATA_LENGTH = 5;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void loadFromStorage() {
        File file = Storage.getFilePath().toFile();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String curr = sc.nextLine();
                String[] taskData = curr.split("\\s\\|\\s");

                try {
                    loadTask(taskData);
                } catch (IllegalArgumentException e) {
                    System.err.println("Corrupted file: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private void loadTask(String[] taskData) {
        // Validate minimum length first
        if (taskData.length < 2) {  // At least type and status
            throw new IllegalArgumentException("Insufficient data fields: " + Arrays.toString(taskData));
        }

        if (!taskData[1].equals(MARKED) && !taskData[1].equals(UNMARKED)) {
            throw new IllegalArgumentException("Invalid mark status: " + taskData[1]);
        }

        boolean isDone = taskData[1].equals(MARKED);
        String description = taskData[2];

        switch (taskData[0]) {
        case TASK_TYPE_TODO:
            String todoTags = taskData.length > 3 ? taskData[3] : "";
            tasks.add(new Todo(description, isDone, tagsToList(todoTags)));
            break;

        case TASK_TYPE_DEADLINE:
            if (taskData.length < DEADLINE_DATA_LENGTH) {
                throw new IllegalArgumentException("Deadline format error - expected at least " + DEADLINE_DATA_LENGTH + " fields, got " + taskData.length);
            }
            String by = taskData[3].replace('T', ' ');
            String deadlineTags = taskData.length > 4 ? taskData[4] : "";
            tasks.add(new Deadline(description, isDone, tagsToList(deadlineTags), by));
            break;

        case TASK_TYPE_EVENT:
            if (taskData.length < EVENT_DATA_LENGTH) {
                throw new IllegalArgumentException("Event format error - expected at least " + EVENT_DATA_LENGTH + " fields, got " + taskData.length);
            }
            String from = taskData[3].replace('T', ' ');
            String to = taskData[4].replace('T', ' ');
            String eventTags = taskData.length > 5 ? taskData[5] : "";
            tasks.add(new Event(description, isDone, tagsToList(eventTags), from, to));
            break;

        default:
            throw new IllegalArgumentException("Task type invalid: " + taskData[0]);
        }
    }

    public String deleteTag(int index, List<String> tagsToRemove) {
        validateIndex(index);
        Task task = tasks.get(index);

        boolean removed = task.removeTags(tagsToRemove);

        if (removed) {
            Storage.saveTasks(this);
            return UI.showMessage("Noted. I've removed tags from this task:")
                    + UI.showMessage(task.toString());
        } else {
            return UI.showMessage("No matching tags found to remove from this task:");
        }
    }

    private static List<String> tagsToList(String tagString) {
        if (tagString == null || tagString.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String[] tags = tagString.split(" / ");
        return Arrays.stream(tags)
                .filter(tag -> !tag.trim().isEmpty())
                .toList();
    }
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        Storage.saveTasks(this);
    }

    public void markTask(int index) {
        validateIndex(index);
        assert index < tasks.size() && index > 0 : "index out of range in TaskList";
        tasks.get(index).mark();
        Storage.saveTasks(this);
    }

    public void unmarkTask(int index) {
        validateIndex(index);
        assert index < tasks.size() && index > 0 : "index out of range in TaskList";
        tasks.get(index).unMark();
        Storage.saveTasks(this);
    }

    public String deleteTask(int index) {
        validateIndex(index);
        assert index < tasks.size() && index > 0 : "index out of range in TaskList";
        Task removed = tasks.remove(index);
        Storage.saveTasks(this);
        return UI.showMessage("Noted. I've removed this task:")
                + UI.showMessage(removed.toString())
                + UI.showMessage("Now you have " + size() + " tasks in the list.");
    }

    public String printTasks() {
        if (size() == 0) {
            return UI.showMessage("bara-bara thinks you should add some tasks before listing them...");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(UI.showMessage((i + 1) + ". " + tasks.get(i).toString()));
        }
        return sb.toString();
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            if (tasks.isEmpty()) {
                throw new IndexOutOfBoundsException("bara-bara can't see your tasks -- add some tasks now!");
            }
            throw new IndexOutOfBoundsException(
                    String.format("bara-bara can't find this task in the list :(\nrange: %d - %d",
                            1, tasks.size()));
        }
    }

    public List<Task> findTasks(String... searchTerms) {
        return tasks.stream()
                .filter(task -> Arrays.stream(searchTerms)
                        .anyMatch(searchTerm -> task.getDescription().contains(searchTerm)))
                .distinct()
                .toList();
    }

    public String tagTask(List<String> tags, int index) {
        tasks.get(index).addTags(tags);
        Storage.saveTasks(this);
        return UI.showMessage("Noted. I've tagged this task:")
                + UI.showMessage(tasks.get(index).toString());

    }
}
