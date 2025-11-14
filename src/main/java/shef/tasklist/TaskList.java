package shef.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import shef.exception.InvalidArgumentException;
import shef.task.Task;

/**
 * Object that stores the list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task into the list.
     * @param task Task to be added.
     * @return Feedback indicating task added and number of tasks in the list after adding.
     */
    public String add(Task task) {
        tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nYou now have %d task%s in the list.",
                task, tasks.size(), tasks.size() == 1 ? "" : "s");
    }

    /**
     * Marks task with given index as done.
     * @param idx the index of the task to be marked.
     * @throws InvalidArgumentException
     */
    public String markAsDone(int idx) throws InvalidArgumentException {
        if (idx <= 0 || idx > tasks.size()) {
            throw new InvalidArgumentException("Invalid task id!");
        }

        Task task = tasks.get(idx - 1);
        task.markAsDone();
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Unmarks task with given index as done.
     * @param idx the index of the task to be unmarked.
     * @throws InvalidArgumentException
     */
    public String unmarkAsDone(int idx) throws InvalidArgumentException {
        if (idx <= 0 || idx > tasks.size()) {
            throw new InvalidArgumentException("Invalid task id!");
        }

        Task task = tasks.get(idx - 1);
        task.unmarkAsDone();
        return "Ok, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Deletes task with given index.
     * @param idx the index of the task to be deleted.
     * @throws InvalidArgumentException
     */
    public String deleteTask(int idx) throws InvalidArgumentException {
        if (idx <= 0 || idx > tasks.size()) {
            throw new InvalidArgumentException("Invalid task id!");
        }

        Task task = tasks.get(idx - 1);
        tasks.remove(idx - 1);
        return String.format("Noted. I've removed the task:\n  %s\nYou now have %d task%s in the list.",
                task, tasks.size(), tasks.size() == 1 ? "" : "s");
    }

    /**
     * Gets a list of tasks whose description contains keyword.
     * @param keyword String to match task descriptions.
     * @return List of tasks.
     */
    public List<Task> getMatches(String keyword) {
        return tasks.stream()
                .filter(t -> t.getDescription().contains(keyword))
                .toList();
    }

    /**
     * Returns a csv formatted string for saving.
     * @return string in csv format.
     */
    public String toCsvString() {
        return tasks.stream()
                .map(Task::toCsvString)
                .reduce("", (acc, s) -> acc + s + '\n');
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "List is empty!";
        }

        String first = String.format("%d.%s", 1, tasks.get(0));
        String remaining = IntStream.range(1, tasks.size())
                .mapToObj(i -> String.format("\n%d.%s", i + 1, tasks.get(i)))
                .collect(Collectors.joining());

        return first + remaining;
    }
}
