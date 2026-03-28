package clare.task;

import java.time.LocalDate;
import java.util.ArrayList;

import clare.exception.StringConvertExceptions;

/**
 * Represents the class to manage the list of task
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task using the index number
     *
     * @param i the index
     * @return the task to be deleted
     * @throws StringConvertExceptions if given index is invalid
     */
    public Task delete(int i) throws StringConvertExceptions {
        Task t;
        try {
            t = tasks.get(i);
            tasks.remove(i);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new StringConvertExceptions("There is no such task");
        }
    }

    /**
     * Marks a task done using an index
     *
     * @param i the index
     * @return the task marked done
     * @throws StringConvertExceptions if the index given is invalid
     */
    public Task markDone(int i) throws StringConvertExceptions {
        Task t;
        t = tasks.get(i);
        t.markDone();
        return t;
    }

    /**
     * Marks a task undone using an index
     *
     * @param i the index
     * @return the task marked undone
     * @throws StringConvertExceptions if the index given is invalid
     */
    public Task markUndone(int i) throws StringConvertExceptions {
        Task t;
        t = tasks.get(i);
        t.markUndone();
        return t;
    }

    /**
     * returns the size of the task list
     *
     * @return the size of task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the string representation of all task
     *
     * @return the string of all task in list
     */
    public String getAllTaskString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i).toString());
            if (i == tasks.size() - 1) {
                break;
            }
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Gets the string of all task for storage
     *
     * @return the string for all task in save format
     */
    public String getAllTaskSaveString() {
        StringBuilder s = new StringBuilder();
        for (Task task : tasks) {
            s.append(task.toSaveString());
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Gets the string of tasks searched by deadline
     *
     * @param deadline the deadline of task
     * @return the string pf tasks within the deadline in list
     */
    public String findTaskByDeadline(LocalDate deadline) {
        StringBuilder s = new StringBuilder();
        for (Task t : tasks) {
            if (t instanceof Deadline) {
                if (((Deadline) t).checkDeadline(deadline)) {
                    if (!s.isEmpty()) {
                        s.append("\n");
                    }
                    s.append(t);
                }
            }
        }
        return s.isEmpty() ? "No task found." : s.toString();
    }

    /**
     * Gets the string of tasks by title
     *
     * @param title the tile of task
     * @return the string of tasks with the title in list
     */
    public String findTaskByTitle(String ... title) {
        StringBuilder s = new StringBuilder();
        for (Task t : tasks) {
            for (String tit : title) {
                if (t.equalsTitle(tit)) {
                    if (!s.isEmpty()) {
                        s.append("\n");
                    }
                    s.append(t);
                    break;
                }
            }
        }
        return s.isEmpty() ? "No task found." : s.toString();
    }

    /**
     * sorts task list by title
     *
     * @return the string of sorted tasks
     */
    public String sortTaskByTitle(boolean isAsc) {
        tasks.sort((a, b) -> a.compareTitle(b) * (isAsc ? 1 : -1));
        return getAllTaskString();
    }

    /**
     * sorts task by deadlines, task without deadline will be sorted by their title
     *
     * @return the string of sorted tasks
     */
    public String sortTaskByDeadline(boolean isAsc) {
        tasks.sort((a, b) -> {
            if (!(a instanceof Deadline) && !(b instanceof Deadline)) {
                return a.compareTitle(b);
            } else if (!(a instanceof Deadline)) {
                return 1;
            } else if (!(b instanceof Deadline)) {
                return -1;
            }
            return ((Deadline) a).compareDeadline((Deadline) b) * (isAsc ? 1 : -1);
        });
        return getAllTaskString();
    }

    /**
     * sorts task by start dates, task without start dates will be sorted by their title
     *
     * @return the string of sorted tasks
     */
    public String sortTaskByStartDate(boolean isAsc) {
        tasks.sort((a, b) -> {
            if (!(a instanceof Event) && !(b instanceof Event)) {
                return a.compareTitle(b);
            } else if (!(a instanceof Event)) {
                return 1;
            } else if (!(b instanceof Event)) {
                return -1;
            }
            return ((Event) a).compareStartTime((Event) b) * (isAsc ? 1 : -1);
        });
        return getAllTaskString();
    }

}
