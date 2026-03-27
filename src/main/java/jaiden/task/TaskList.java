package jaiden.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Class for task list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for non-empty task list.
     *
     * @param tasks List of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null;
        this.tasks = tasks;
    }

    /**
     * Shows a list of tasks in the task list.
     *
     * @return Message to be shown.
     */
    public String list() {
        StringBuilder msg = new StringBuilder("Here are the tasks in your list:\n");

        IntStream.range(0, this.tasks.size())
                .mapToObj(i -> (i + 1) + "." + this.tasks.get(i) + "\n")
                .forEach(msg::append);

        return msg.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of task to be marked (0-indexed).
     * @return Message to be shown.
     */
    public String mark(int index) {
        assert index >= 0 && index < this.tasks.size();
        this.tasks.get(index).markAsDone();

        return "Nice! I've marked this task as done:\n" + this.tasks.get(index).toString();
    }

    /**
     * Unmarks a task as done.
     *
     * @param index Index of task to be unmarked (0-indexed).
     * @return Message to be shown.
     */
    public String unmark(int index) {
        assert index >= 0 && index < this.tasks.size();
        this.tasks.get(index).markAsNotDone();

        return "OK, I've marked this task as not done yet:\n" + this.tasks.get(index).toString();
    }

    /**
     * Adds task to task list.
     *
     * @param task Task to be added.
     * @return Message to be shown.
     */
    public String add(Task task) {
        assert task != null;
        this.tasks.add(task);

        return "Got it. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    /**
     * Removes task from task list.
     *
     * @param index Index of task to be removed (0-indexed).
     * @return Message to be shown.
     */
    public String remove(int index) {
        assert index >= 0 && index < this.tasks.size();
        Task task = this.tasks.remove(index);

        return "Noted. I've removed this task:\n" + task.toString() + "\n"
                + "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    /**
     * Shows a list of tasks on a date.
     *
     * @param viewDate Date.
     * @return Message to be shown.
     */
    public String view(LocalDate viewDate) {
        assert viewDate != null;
        StringBuilder msg = new StringBuilder("Here are the tasks on "
                + viewDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " in your list:\n");

        for (int i = 0, count = 1; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);

            if (t.getClass() == Deadline.class) {
                Deadline d = (Deadline) t;

                if (!d.isBy(viewDate)) {
                    continue;
                }

                msg.append(count).append(".").append(t.toString()).append("\n");
                count++;
            } else if (t.getClass() == Event.class) {
                Event e = (Event) t;

                if (!e.isBetween(viewDate)) {
                    continue;
                }

                msg.append(count).append(".").append(t.toString()).append("\n");
                count++;
            }
        }

        return msg.toString();
    }

    /**
     * Finds the tasks that contain the text.
     *
     * @param text Text to find for.
     * @return Message to be shown.
     */
    public String find(String text) {
        assert text != null;
        StringBuilder msg = new StringBuilder("Here are the matching tasks in your list:\n");

        IntStream.range(0, this.tasks.size())
                .filter(i -> this.tasks.get(i).hasText(text))
                .forEachOrdered(i -> msg.append(i + 1).append(".").append(this.tasks.get(i)).append("\n"));

        return msg.toString();
    }

    /**
     * Converts task list to string to be saved.
     *
     * @return String representation to be saved.
     */
    public String save() {
        StringBuilder msg = new StringBuilder();

        this.tasks.stream()
                .map(Task::save)
                .forEach(s -> msg.append(s).append("\n"));

        return msg.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TaskList other)) {
            return false;
        }
        return this.tasks.equals(other.tasks);
    }
}
