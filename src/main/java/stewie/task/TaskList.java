package stewie.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Manages a list of tasks with operations to add, remove, mark, and list tasks.
 */
public class TaskList {
    private static final int INDEX_OFFSET = 1;

    private final ArrayList<Task> tasks;

    /**
     * Creates a new empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds one or more tasks to the task list.
     * Returns appropriate feedback message based on the number of tasks added.
     *
     * @param tasks The task(s) to be added to the list. At least one task must be provided.
     * @return A formatted response message indicating the task(s) have been added
     */
    public String addTask(Task... tasks) {
        assert tasks != null : "Tasks array cannot be null";
        assert tasks.length > 0 : "At least one task must be provided";
        this.tasks.addAll(Arrays.asList(tasks));

        if (tasks.length == 1) {
            return "I've scribbled down your little task:\n"
                    + " " + tasks[0].getDescription() + "\n"
                    + "Now, do try to keep up, won't you?\n"
                    + "You have " + this.tasks.size() + " tasks left";
        } else {
            return "I've added " + tasks.length + " tasks to your endless list.\n"
                   + "You have " + this.tasks.size() + " tasks in total.";
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The 1-based index of the task to mark.
     * @return Confirmation message about the marked task.
     */
    public String markTask(int index) {
        assert index > 0 : "Index must be positive (1-based indexing)";
        assert index <= this.tasks.size() : "Index must not exceed task list size";
        Task task = this.tasks.get(index - INDEX_OFFSET);
        task.markAsDone();
        return "Behold! I've declared this paltry task complete.\n"
                + " " + task.getDescription() + "\n"
                + "Don't get cocky. You still have a long way to go.";
    }

    /**
     * Unmarks a task as not done.
     *
     * @param index The 1-based index of the task to unmark.
     * @return Confirmation message about the unmarked task.
     */
    public String unmarkTask(int index) {
        assert index > 0 : "Index must be positive (1-based indexing)";
        assert index <= this.tasks.size() : "Index must not exceed task list size";
        Task task = this.tasks.get(index - INDEX_OFFSET);
        task.unmark();
        return "You're toying with me! I've marked this back as incomplete.\n"
                + " " + task.getDescription() + "\n"
                + "Don't think for a second I'll forget this betrayal.";
    }

    /**
     * Returns a formatted list of all tasks.
     *
     * @return String representation of all tasks in the list.
     */
    public String listTask() {
        if (this.tasks.isEmpty()) {
            return "No tasks? How utterly dreadful!\n";
        }

        String taskList = IntStream.range(0, this.tasks.size())
                .mapToObj(i -> String.format(" %d. %s",
                        i + 1,
                        tasks.get(i).getDescription()))
                .collect(Collectors.joining("\n"));

        return "These, my dear simpleton, are the items on your agenda.\n"
                + taskList + "\n"
                + "Failure is not an option.\n"
                + "You have " + this.tasks.size() + " tasks left";
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The 1-based index of the task to delete.
     * @return Confirmation message about the deleted task.
     */
    public String deleteTask(int index) {
        assert index > 0 : "Index must be positive (1-based indexing)";
        assert index <= this.tasks.size() : "Index must not exceed task list size";
        Task task = this.tasks.remove(index - INDEX_OFFSET);
        return "Poof! Begone with you, you insignificant little undertaking!\n"
               + " " + task.getDescription() + "\n"
               + "Don't get cocky. You still have a long way to go.\n"
               + "You have " + this.tasks.size() + " tasks left.";
    }

    /**
     * Finds tasks containing the specified description keyword.
     *
     * @param description The keyword to search for in task descriptions.
     * @return A formatted string containing all matching tasks, or a message if no matches found.
     */
    public String findTaskByDescription(String description) {
        List<Task> matchingTasks = this.tasks.stream()
                .filter(task -> task.getDescription().contains(description))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            return "You've wasted my time. Absolutely nothing of consequence was found.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Hmph! Here are the pathetic, insignificant plans that you requested.\n");

        IntStream.range(0, matchingTasks.size())
                .forEach(i -> sb.append(" ")
                        .append(i + 1)
                        .append(". ")
                        .append(matchingTasks.get(i).getDescription())
                        .append("\n"));

        sb.append(String.format("Found %d tasks in total.", matchingTasks.size()));
        return sb.toString();
    }

    /**
     * Updates the task at the specified 1-based index with the given new task.
     *
     * @param index The 1-based index of the task to mark.
     * @param newTask The new {@link Task} to replace the existing task.
     * @return Confirmation message about the updated task.
     */
    public String updateTask(int index, Task newTask) {
        assert newTask != null : "Task cannot be null";
        assert index > 0 : "Index must be positive (1-based indexing)";
        assert index <= this.tasks.size() : "Index must not exceed task list size";
        tasks.set(index - INDEX_OFFSET, newTask);
        assert tasks.get(index - INDEX_OFFSET).equals(newTask) : "TaskList is not updated";
        return "Oh, rejoice! I've altered your little task:\n"
                + "  " + newTask.getDescription() + "\n"
                + "Try not to screw it up this time.";
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
}
