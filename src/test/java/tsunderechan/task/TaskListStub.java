package tsunderechan.task;

/**
 * Represents a stub of a TaskList for better unit testing.
 */
public class TaskListStub extends TaskList {

    /**
     * Returns a TaskList containing a Todo task.
     */
    public TaskList getTodoTask() {
        tasks.add(new Todo("homework"));
        return this;
    }

    /**
     * Returns a TaskList containing a deadline task.
     */
    public TaskList getDeadlineTask() {
        tasks.add(new Deadline("homework", "2025-03-29 23:22"));
        return this;
    }

    /**
     * Returns a TaskList containing an event task.
     */
    public TaskList getEventTask() {
        tasks.add(new Event("CCA", "2025-03-29 23:22", "2025-03-30 00:22"));
        return this;
    }

    /**
     * Returns a TaskList containing 1 of each task.
     */
    public TaskList getMultipleTask() {
        tasks.add(new Event("CCA", "2025-03-29 23:22", "2025-03-30 00:22"));
        tasks.add(new Deadline("homework", "2025-03-29 23:22"));
        tasks.add(new Todo("homework"));
        return this;
    }

    /**
     * Returns a TaskList containing 1 of each task, all marked as completed.
     */
    public TaskList getMarkedTask() {
        tasks.add(new Event("CCA", "2025-03-29 23:22", "2025-03-30 00:22", true));
        tasks.add(new Deadline("homework", "2025-03-29 23:22", true));
        tasks.add(new Todo("homework", true));
        return this;
    }
}
